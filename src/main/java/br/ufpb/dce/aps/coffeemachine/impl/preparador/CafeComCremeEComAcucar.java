package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CafeComCremeEComAcucar extends CafeComCremeService {

	private Dispenser sugarDispenser;
//	protected static final double QTD_ACUCAR = 5;

	@Override
	public void instanciarDispensers(ComponentsFactory factory) {
		super.instanciarDispensers(factory);
		this.sugarDispenser = factory.getSugarDispenser();
	}

	public void adicionarIngredientes() {
		this.coffeePowderDispenser.release(this.recipe.COFFEE_POWDER);
		this.waterDispenser.release(this.recipe.WATER);
		this.creamerDispenser.release(qtdDeCreme);
		this.sugarDispenser.release(this.recipe.SUGAR);
	}

	@Override
	public void verificarDisponibilidadeDeIgredientes()
			throws FaltaDeIngredienteException {

		super.verificarDisponibilidadeDeIgredientes();
		boolean temAcucar = this.sugarDispenser.contains(this.recipe.SUGAR);
		if (!temAcucar) {
			throw new FaltaDeIngredienteException(Messages.OUT_OF_SUGAR);
		}
	}
}
