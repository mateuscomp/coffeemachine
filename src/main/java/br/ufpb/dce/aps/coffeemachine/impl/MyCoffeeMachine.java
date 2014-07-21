package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.ComporFacade;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;

public class MyCoffeeMachine extends ComporFacade implements CoffeeMachine {

	private ComponentsFactory factory;

	public MyCoffeeMachine(ComponentsFactory factory) {
		this.factory = factory;
		requestService("init", factory);
		
	}

	@Override
	protected void addComponents() {
		super.addComponents();
		add(new DisplayCoffeeMachine("displayCoffeeMachine"));
		add(new FabricadorDeCafe("fabricadorDeCafe"));
	}

	public void insertCoin(Coin dime) {
		requestService("mostrarValorInserido", dime, factory);
	}

	public void cancel() {
		requestService("cancelar", factory); 
		
	}

	public void select(Drink drink) {
		requestService("prepararCafePreto", drink, factory);
		requestService("init", factory);
	}

}
