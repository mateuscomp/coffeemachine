package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class DisplayCoffeeMachine extends Component {
	
	private int dollar = 0;
	private int cents = 0;

	public DisplayCoffeeMachine(String name) {
		super(name);
	}

	@Service(name = "mostrarValorInserido")
	public void mostrarValorInserido(Coin dime, ComponentsFactory factory) {
		if (dime == null){
			throw new CoffeeMachineException("");
		}
		this.dollar += dime.getValue() / 100;
		this.cents += dime.getValue() % 100;
		
		factory.getDisplay().info("Total: US$ " + dollar + "." + cents);
	}

//	public double getTotal() {
//		double centsWithZero = cents % 10 > 1  ? new Double(cents + "0") : cents; 
//		return dollar + centsWithZero; 
//	}

	
	
	
}
