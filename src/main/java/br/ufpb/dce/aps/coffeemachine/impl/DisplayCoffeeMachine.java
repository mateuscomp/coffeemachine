package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;
import java.util.List;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class DisplayCoffeeMachine extends Component {

	private int dollar = 0;
	private int cents = 0;
	private List<Coin> coins;

	public DisplayCoffeeMachine(String name) {
		super(name);
		this.coins = new ArrayList<Coin>();

	}

	@Service
	public void init(ComponentsFactory factory) {
		factory.getDisplay().info("Insert coins and select a drink!");
	}

	@Service(name = "mostrarValorInserido")
	public void mostrarValorInserido(Coin dime, ComponentsFactory factory) {
		if (dime == null) {
			throw new CoffeeMachineException("");
		}
		this.dollar += dime.getValue() / 100;
		this.cents += dime.getValue() % 100;
		this.coins.add(dime);
		factory.getDisplay().info("Total: US$ " + dollar + "." + cents);
	}

	@Service
	public void cancelar(ComponentsFactory factory) {
		if (dollar == 0 && cents == 0) {
			throw new CoffeeMachineException("");
		}
		factory.getDisplay().warn(Messages.CANCEL_MESSAGE);

		for (Coin coin : this.coins) {
			factory.getCashBox().release(coin);
		}
		this.init(factory);
	}
}
