package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import java.util.HashMap;
import java.util.Map;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;

public class PreparadorDeCafeService extends Component {

	private Map<Drink, CafeService> servicosDeCafe;

	public PreparadorDeCafeService(String name) {
		super(name);
		this.servicosDeCafe = new HashMap<Drink, CafeService>();
		this.servicosDeCafe.put(Drink.BLACK, new CafePretoService());
		this.servicosDeCafe.put(Drink.BLACK_SUGAR, new CafePretoComAcucarService());
	}

	@Service
	public void prepararCafe(Drink drink, ComponentsFactory factory) {
		this.servicosDeCafe.get(drink).preparar(factory);
		requestService("insertCoinsMessageDisplay", factory);
	}
}
