package br.ufpb.dce.aps.coffeemachine.impl.caixaregistradora;

import java.util.ArrayList;
import java.util.List;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CaixaRegistradoraCoffeeMachineService extends Component {

	private int dollar = 0;
	private int cents = 0;
	private List<Coin> coins;

	public CaixaRegistradoraCoffeeMachineService(String name) {
		super(name);
		this.coins = new ArrayList<Coin>();
	}

	@Service
	public void receberMoedas(Coin dime, ComponentsFactory factory) {
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
		factory.getDisplay().info(Messages.INSERT_COINS);
	}

	@Service
	public void removerMoedas(ComponentsFactory factory) {
		for (Coin coinDescrecente : Coin.reverse()) {
			for (int i = 0; i < this.coins.size(); i++) {
				if (this.coins.get(i).equals(coinDescrecente)) {
					factory.getCashBox().release(coins.get(i));
				}
			}
		}
		this.limparCaixaDeMoedas();
	}

	@Service
	public void limparCaixaDeMoedas() {
		this.coins = new ArrayList<Coin>();
	}
}
