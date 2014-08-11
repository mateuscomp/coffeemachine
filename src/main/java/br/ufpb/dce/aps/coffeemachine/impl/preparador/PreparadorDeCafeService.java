package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import java.util.HashMap;
import java.util.Map;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class PreparadorDeCafeService extends Component {

	private Map<Drink, CafeService> servicosDeCafe;

	public PreparadorDeCafeService(String name) {
		super(name);
		this.servicosDeCafe = new HashMap<Drink, CafeService>();
		this.servicosDeCafe.put(Drink.BLACK, new CafePretoService());
		this.servicosDeCafe.put(Drink.BLACK_SUGAR,
				new CafePretoComAcucarService());
		this.servicosDeCafe.put(Drink.WHITE, new CafeComCremeService());
		this.servicosDeCafe
				.put(Drink.WHITE_SUGAR, new CafeComCremeEComAcucar());
	}

	@Service
	public void prepararCafe(Drink drink, ComponentsFactory factory) {
		try {
			CafeService service = this.servicosDeCafe.get(drink);
			service.setFactory(factory);
			service.instanciarDispensers(factory);

			service.verificarDisponibilidadeDeIgredientes();
			requestService("planejarTroco", factory);

			factory.getDisplay().info(Messages.MIXING);

			service.adicionarIngredientes();
			service.fazerCafe();

			requestService("entregarTroco", factory);

		} catch (FaltaDeIngredienteException e) {
			factory.getDisplay().warn(e.getMessage());
			requestService("removerMoedas", factory);
		}
		factory.getDisplay().info(Messages.INSERT_COINS);
	}
}
