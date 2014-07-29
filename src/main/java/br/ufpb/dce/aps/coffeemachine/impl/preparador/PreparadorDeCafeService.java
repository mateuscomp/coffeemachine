package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import java.util.HashMap;
import java.util.Map;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class PreparadorDeCafeService extends Component {

	private Map<Drink, CafeAbstractService> servicosDeCafe;

	public PreparadorDeCafeService(String name) {
		super(name);
		this.servicosDeCafe = new HashMap<Drink, CafeAbstractService>();
		this.servicosDeCafe.put(Drink.BLACK, new CafePretoService());
		this.servicosDeCafe.put(Drink.BLACK_SUGAR,
				new CafePretoComAcucarService());
	}

	@Service
	public void prepararCafe(Drink drink, ComponentsFactory factory) {
		try {
			this.servicosDeCafe.get(drink).preparar(factory);
			requestService("limparCaixaDeMoedas");
		} catch (FaltaDePoDeCafeException e) {
			factory.getDisplay().warn(e.getMessage());
			requestService("removerMoedas", factory);
		}
		factory.getDisplay().info(Messages.INSERT_COINS);
	}
}
