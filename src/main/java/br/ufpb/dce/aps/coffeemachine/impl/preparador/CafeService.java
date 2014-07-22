package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Display;
import br.ufpb.dce.aps.coffeemachine.DrinkDispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public abstract class CafeService {

	protected Dispenser cupDispenser;
	protected Dispenser waterDispenser;
	protected Dispenser coffeePowderDispenser;
	protected DrinkDispenser drinkDispenser;

	protected Display display;

	public void preparar(ComponentsFactory factory) {
		this.instanciarDispensers(factory);
		if (verificarDisponibilidadeDeIngredientes()) {
			display.info(Messages.MIXING);
			adicionarIngredientes();
		}
	}

	public abstract void adicionarIngredientes();

	public void instanciarDispensers(ComponentsFactory factory) {
		this.cupDispenser = factory.getCupDispenser();
		this.waterDispenser = factory.getWaterDispenser();
		this.coffeePowderDispenser = factory.getCoffeePowderDispenser();
		this.drinkDispenser = factory.getDrinkDispenser();
		this.display = factory.getDisplay();
	}

	public abstract boolean verificarDisponibilidadeDeIngredientes();
}
