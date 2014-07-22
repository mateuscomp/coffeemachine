package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.ComporFacade;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.impl.display.DisplayCoffeeMachineService;
import br.ufpb.dce.aps.coffeemachine.impl.preparador.PreparadorDeCafeService;

public class MyCoffeeMachine extends ComporFacade implements CoffeeMachine {

	private ComponentsFactory factory;

	public MyCoffeeMachine(ComponentsFactory factory) {
		this.factory = factory;
		requestService("insertCoinsMessageDisplay", this.factory);
	}

	@Override
	protected void addComponents() {
		super.addComponents();
		add(new DisplayCoffeeMachineService("displayCoffeeMachine"));
		add(new PreparadorDeCafeService("fabricadorDeCafe"));
	}

	public void insertCoin(Coin dime) {
		requestService("mostrarValorInserido", dime, this.factory);
	}

	public void cancel() {
		requestService("cancelar", this.factory);
	}

	public void select(Drink drink) {
		requestService("prepararCafe", drink, this.factory);
	}
}