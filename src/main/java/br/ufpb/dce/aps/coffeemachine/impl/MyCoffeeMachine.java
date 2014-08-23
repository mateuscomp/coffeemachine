package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.ComporFacade;
import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Recipe;
import br.ufpb.dce.aps.coffeemachine.impl.caixaregistradora.CaixaRegistradoraCoffeeMachineService;
import br.ufpb.dce.aps.coffeemachine.impl.preparador.PreparadorDeDrinkService;

public class MyCoffeeMachine extends ComporFacade implements CoffeeMachine {

	private ComponentsFactory factory;

	@Override
	protected void addComponents() {
		super.addComponents();
		add(new CaixaRegistradoraCoffeeMachineService("displayCoffeeMachine"));
		add(new PreparadorDeDrinkService("fabricadorDeCafe"));
	}

	public void insertCoin(Coin dime) {
		requestService("receberMoedas", dime, this.factory);
	}

	public void cancel() {
		requestService("cancelar", this.factory);
	}

	public void select(Button button) {
		requestService("prepararCafe", button, this.factory);
	}

	public void setFactory(ComponentsFactory factory) {
		this.factory = factory;

		requestService("apresentarCardapio", this.factory);
		requestService("mostrarMensagemDeInserirMoedas", this.factory);
	}

	public void readBadge(int badgeCode) {
		requestService("lerCracha", this.factory, badgeCode);

	}

	public void setPrice(Button button, int priceCents) {
		requestService("mudarPrecoDeItemNoCardapio", button, priceCents);
		requestService("apresentarCardapio", this.factory);

	}

	public void configuteDrink(Button button, Recipe recipe) {
		requestService("mudarRecipienteDoDrink", button, recipe);
		
	}
}