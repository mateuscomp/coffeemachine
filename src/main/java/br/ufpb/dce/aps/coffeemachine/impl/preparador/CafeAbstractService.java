package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Display;
import br.ufpb.dce.aps.coffeemachine.DrinkDispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public abstract class CafeAbstractService {

	protected Dispenser cupDispenser;
	protected Dispenser waterDispenser;
	protected Dispenser coffeePowderDispenser;
	protected DrinkDispenser drinkDispenser;

	protected Display display;

	protected static final int QTD_COPOS = 1;
	protected static final double QTD_AGUA = 0.5;
	protected static final double QTD_PO_DE_CAFE = 1.5;

	public void preparar(ComponentsFactory factory)
			throws FaltaDeIngredienteException {

		this.instanciarDispensers(factory);
		this.display = factory.getDisplay();

		if (verificarDisponibilidadeDeIgredientes()) {
			this.display.info(Messages.MIXING);
			adicionarIngredientes();
		}
	}

	public abstract void adicionarIngredientes();

	private void instanciarDispensers(ComponentsFactory factory) {
		this.cupDispenser = factory.getCupDispenser();
		this.waterDispenser = factory.getWaterDispenser();
		this.coffeePowderDispenser = factory.getCoffeePowderDispenser();
		this.drinkDispenser = factory.getDrinkDispenser();
	}

	public boolean verificarDisponibilidadeDeIgredientes()
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

		return temCopoDisponivel && temAguaDisponivel && temPoDeCafe;
	}
}
