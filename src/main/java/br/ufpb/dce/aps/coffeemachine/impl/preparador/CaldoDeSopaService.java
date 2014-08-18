package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Display;
import br.ufpb.dce.aps.coffeemachine.DrinkDispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CaldoDeSopaService implements CafeService {

	private Display display;

	protected Dispenser cupDispenser;
	protected Dispenser waterDispenser;
	protected Dispenser bouillonDispenser;
	protected DrinkDispenser drinkDispenser;

	private int qtdDeCopos = 1;
	private int qtdDeAgua = 100;
	private int qtdDePoDeCaldo = 10;
	private int qtdDoDrink = 100;

	public void instanciarDispensers(ComponentsFactory factory) {
		this.cupDispenser = factory.getCupDispenser();
		this.waterDispenser = factory.getWaterDispenser();
		this.bouillonDispenser = factory.getBouillonDispenser();
		this.drinkDispenser =  factory.getDrinkDispenser();

		this.display = factory.getDisplay();

	}

	public void verificarDisponibilidadeDeIgredientes()
			throws FaltaDeIngredienteException {

		boolean temCopoDisponivel = cupDispenser.contains(qtdDeCopos);
		if (!temCopoDisponivel) {
			throw new FaltaDeIngredienteException(Messages.OUT_OF_CUP);
		}

		boolean temAguaDisponivel = waterDispenser.contains(qtdDeAgua);
		if (!temAguaDisponivel) {
			throw new FaltaDeIngredienteException(Messages.OUT_OF_WATER);
		}

		boolean temPoDeCaldo = this.bouillonDispenser.contains(qtdDePoDeCaldo);
		if (!temPoDeCaldo) {
			throw new FaltaDeIngredienteException("");
		}

	}

	public void adicionarIngredientes() {
		this.bouillonDispenser.release(qtdDePoDeCaldo);
		this.waterDispenser.release(qtdDeAgua);

	}

	public void fazerCafe() {
		this.display.info(Messages.RELEASING);
		this.cupDispenser.release(qtdDeCopos);
		this.drinkDispenser.release(qtdDoDrink);
		this.display.info(Messages.TAKE_DRINK);

	}

	public int getValorDoCafe() {
		return 25;
	}

}
