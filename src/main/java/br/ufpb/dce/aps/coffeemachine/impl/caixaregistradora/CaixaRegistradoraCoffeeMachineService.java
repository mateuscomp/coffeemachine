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

	private static int valorDoCafe = 35;
	private List<Coin> coins;
	private List<Coin> troco;

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
	public void planejarTroco(ComponentsFactory factory) {
		troco = new ArrayList<Coin>();

		int totalArrecado = 0;

		for (Coin c : this.coins) {
			totalArrecado += c.getValue();
		}
		int troco = totalArrecado - valorDoCafe;
		for (Coin coin : Coin.reverse()) {
			while (coin.getValue() <= troco) {
				factory.getCashBox().count(coin);
				troco -= coin.getValue();
				this.troco.add(coin);
			}
		}
	}

	@Service
	public void entregarTroco(ComponentsFactory factory) {
		for (Coin c : this.troco) {
			factory.getCashBox().release(c);
		}
		this.limparCaixaDeMoedas();
	}

	@Service
	public void cancelar(ComponentsFactory factory) {
		if (dollar == 0 && cents == 0) {
			throw new CoffeeMachineException("");
		}
		factory.getDisplay().warn(Messages.CANCEL);
		this.removerMoedas(factory);
		this.mostrarMensagemDeInserirMoedas(factory);
	}

	@Service
	public void mostrarMensagemDeInserirMoedas(ComponentsFactory factory) {
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

	public void limparCaixaDeMoedas() {
		this.coins = new ArrayList<Coin>();
	}
}
