package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import java.util.HashMap;
import java.util.Map;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class PreparadorDeDrinkService extends Component {

	private Map<Button, CafeService> servicosDeDrink;

	public PreparadorDeDrinkService(String name) {
		super(name);
		this.servicosDeDrink = new HashMap<Button, CafeService>();
		this.servicosDeDrink.put(Button.BUTTON_1, new CafePretoService());
		this.servicosDeDrink.put(Button.BUTTON_2, new CafeComCremeService());
		this.servicosDeDrink.put(Button.BUTTON_3,
				new CafePretoComAcucarService());
		this.servicosDeDrink.put(Button.BUTTON_4, new CafeComCremeEComAcucar());
		this.servicosDeDrink.put(Button.BUTTON_5, new CaldoDeSopaService());
	}

	@Service
	public void prepararCafe(Button button, ComponentsFactory factory) {
		try {
			CafeService service = this.servicosDeDrink.get(button);
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
