package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CafePretoComAcucarService extends CafePretoService {

	protected static final int QTD_ACUCAR = 1;

	private Dispenser sugarDispenser;

	@Override
	protected void instanciarDispensers(ComponentsFactory factory) {
		super.instanciarDispensers(factory);
		this.sugarDispenser = factory.getSugarDispenser();
	}

	@Override
	public void adicionarIngredientes() {
		this.coffeePowderDispenser.release(QTD_PO_DE_CAFE);
		this.waterDispenser.release(QTD_AGUA);

		this.sugarDispenser.release(QTD_ACUCAR);

		this.display.info(Messages.RELEASING);
		this.cupDispenser.release(QTD_COPOS);

		this.drinkDispenser.release(1);
		this.display.info(Messages.TAKE_DRINK);
	}

	@Override
	public void verificarDisponibilidadeDeIgredientes()
			throws FaltaDeIngredienteException {

		super.verificarDisponibilidadeDeIgredientes();
		boolean temAcucar = this.sugarDispenser.contains(QTD_ACUCAR);
		if (!temAcucar) {
			throw new FaltaDeIngredienteException(Messages.OUT_OF_SUGAR);
		}

	}
}
