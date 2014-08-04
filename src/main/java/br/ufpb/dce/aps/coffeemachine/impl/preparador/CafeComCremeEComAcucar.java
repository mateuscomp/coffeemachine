package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import static org.mockito.Matchers.anyDouble;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CafeComCremeEComAcucar extends CafeComCremeService {

	private Dispenser sugarDispenser;
	protected static final int QTD_ACUCAR = 1;

	@Override
	protected void instanciarDispensers(ComponentsFactory factory) {
		super.instanciarDispensers(factory);
		this.sugarDispenser = factory.getSugarDispenser();
	}

	public void adicionarIngredientes() {
		this.coffeePowderDispenser.release(anyDouble());
		this.waterDispenser.release(anyDouble());
		this.creamerDispenser.release(anyDouble());
		this.sugarDispenser.release(anyDouble());

		this.display.info(Messages.RELEASING);
		this.cupDispenser.release(1);
		this.drinkDispenser.release(anyDouble());
		this.display.info(Messages.TAKE_DRINK);
	}

	@Override
	public void verificarDisponibilidadeDeIgredientes()
			throws FaltaDeIngredienteException {

		super.verificarDisponibilidadeDeIgredientes();
		this.sugarDispenser.contains(QTD_ACUCAR);
	}
}
