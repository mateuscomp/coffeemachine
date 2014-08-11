package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Display;
import br.ufpb.dce.aps.coffeemachine.DrinkDispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public abstract class CafeAbstractService {
	protected ComponentsFactory factory;

	protected Dispenser cupDispenser;
	protected Dispenser waterDispenser;
	protected Dispenser coffeePowderDispenser;
	protected DrinkDispenser drinkDispenser;

	protected Display display;

	protected static final int QTD_COPOS = 1;
	protected static final double QTD_AGUA = 0.5;
	protected static final double QTD_PO_DE_CAFE = 1.5;

	public abstract void adicionarIngredientes();

	protected void instanciarDispensers(ComponentsFactory factory) {
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
		
		this.drinkDispenser.release(1);
		this.display.info(Messages.TAKE_DRINK);
	}

	public void setFactory(ComponentsFactory factory) {
		this.factory = factory;
	}
}
