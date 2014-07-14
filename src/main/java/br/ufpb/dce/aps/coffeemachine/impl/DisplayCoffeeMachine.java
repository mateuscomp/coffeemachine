package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class DisplayCoffeeMachine extends Component {

	public DisplayCoffeeMachine(String name) {
		super(name);
	}

	@Service(name = "mostrarValorInserido")
	public void mostrarValorInserido(Coin dime, ComponentsFactory factory) {
		Double valor = (double) (dime.getValue() % 100);
		factory.getDisplay().info("Total: US$ 0." + dime.getValue());
	}
}
