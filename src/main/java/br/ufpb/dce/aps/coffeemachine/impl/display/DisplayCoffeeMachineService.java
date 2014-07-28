package br.ufpb.dce.aps.coffeemachine.impl.display;

import java.util.ArrayList;
import java.util.List;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class DisplayCoffeeMachineService extends Component {

	private int dollar = 0;
	private int cents = 0;
	private List<Coin> coins;

	public DisplayCoffeeMachineService(String name) {
		super(name);
		this.coins = new ArrayList<Coin>();
	}

	@Service
	public void insertCoinsMessageDisplay(ComponentsFactory factory) {
		factory.getDisplay().info(Messages.INSERT_COINS);
	}

	@Service
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
		factory.getDisplay().warn(Messages.CANCEL);

		this.removerMoedas(factory);
		this.insertCoinsMessageDisplay(factory);
	}

	private void removerMoedas(ComponentsFactory factory) {
		for (Coin coinDescrecente : Coin.reverse()) {
			for (int i = 0; i < this.coins.size(); i++) {
				if (this.coins.get(i).equals(coinDescrecente)) {
					factory.getCashBox().release(coins.get(i));
				}
			}
		}
		this.consumirMoedas();
	}
	@Service
	public void consumirMoedas(){
		this.coins = new ArrayList<Coin>();
	}
}
