package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import java.util.HashMap;
import java.util.Map;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class PreparadorDeDrinkService extends Component {

	private Map<Drink, CafeService> servicosDeDrink;

	public PreparadorDeDrinkService(String name) {
		super(name);
		this.servicosDeDrink = new HashMap<Drink, CafeService>();
		this.servicosDeDrink.put(Drink.BLACK, new CafePretoService());
		this.servicosDeDrink.put(Drink.BLACK_SUGAR,
				new CafePretoComAcucarService());
		this.servicosDeDrink.put(Drink.WHITE, new CafeComCremeService());
		this.servicosDeDrink.put(Drink.WHITE_SUGAR,
				new CafeComCremeEComAcucar());
		this.servicosDeDrink.put(Drink.BOUILLON, new CaldoDeSopaService());
	}

	@Service
	public void prepararCafe(Drink drink, ComponentsFactory factory) {
		try {
			CafeService service = this.servicosDeDrink.get(drink);
			service.instanciarDispensers(factory);
			boolean temDinheiro = (Boolean) requestService(
					"verificarValorInserido", factory, service.getValorDoCafe());
			if (temDinheiro) {
				service.verificarDisponibilidadeDeIgredientes();
				boolean temTroco = (Boolean) requestService("planejarTroco",
						factory, service.getValorDoCafe());
				if (temTroco) {
					factory.getDisplay().info(Messages.MIXING);

					service.adicionarIngredientes();
					service.fazerCafe();

					requestService("entregarTroco", factory);
				}
			}

		} catch (FaltaDeIngredienteException e) {
			factory.getDisplay().warn(e.getMessage());
			requestService("removerMoedas", factory);
		}
		factory.getDisplay().info(Messages.INSERT_COINS);
	}
}
