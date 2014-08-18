package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Display;
import br.ufpb.dce.aps.coffeemachine.DrinkDispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CafePretoService implements CafeService {
	protected ComponentsFactory factory;

	protected Dispenser cupDispenser;
	protected Dispenser waterDispenser;
	protected Dispenser coffeePowderDispenser;
	protected DrinkDispenser drinkDispenser;

	protected Display display;

	protected static final int QTD_COPOS = 1;
	protected static final int QTD_AGUA = 100;
	protected static final int QTD_PO_DE_CAFE = 15;

	private int valorDoCafe = 35;

	public void adicionarIngredientes() {
		this.coffeePowderDispenser.release(QTD_PO_DE_CAFE);
		this.waterDispenser.release(QTD_AGUA);
	}

	public void instanciarDispensers(ComponentsFactory factory) {
		this.cupDispenser = factory.getCupDispenser();
		this.waterDispenser = factory.getWaterDispenser();
		this.coffeePowderDispenser = factory.getCoffeePowderDispenser();
		this.drinkDispenser = factory.getDrinkDispenser();

		this.display = factory.getDisplay();
	}

	public void verificarDisponibilidadeDeIgredientes()
			throws FaltaDeIngredienteException {

		boolean temCopoDisponivel = cupDispenser.contains(QTD_COPOS);
		if (!temCopoDisponivel) {
			throw new FaltaDeIngredienteException(Messages.OUT_OF_CUP);
		}

		boolean temAguaDisponivel = waterDispenser.contains(QTD_AGUA);
		if (!temAguaDisponivel) {
			throw new FaltaDeIngredienteException(Messages.OUT_OF_WATER);
		}
		boolean temPoDeCafe = this.coffeePowderDispenser
				.contains(QTD_PO_DE_CAFE);
		if (!temPoDeCafe) {
			throw new FaltaDeIngredienteException(Messages.OUT_OF_COFFEE_POWDER);
		}
	}

	public void fazerCafe() {
		this.display.info(Messages.RELEASING);
		this.cupDispenser.release(QTD_COPOS);

		this.drinkDispenser.release(QTD_AGUA);
		this.display.info(Messages.TAKE_DRINK);
	}

	public void setFactory(ComponentsFactory factory) {
		this.factory = factory;
	}

	public int getValorDoCafe() {
		return this.valorDoCafe;
	}
}
