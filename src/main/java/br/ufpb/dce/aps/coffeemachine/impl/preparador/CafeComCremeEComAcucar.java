package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import static org.mockito.Matchers.anyDouble;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;

public class CafeComCremeEComAcucar extends CafeComCremeService {

	private Dispenser sugarDispenser;
	protected static final int QTD_ACUCAR = 1;

	@Override
	public void instanciarDispensers(ComponentsFactory factory) {
		super.instanciarDispensers(factory);
		this.sugarDispenser = factory.getSugarDispenser();
	}

	public void adicionarIngredientes() {
		this.coffeePowderDispenser.release(anyDouble());
		this.waterDispenser.release(anyDouble());
		this.creamerDispenser.release(anyDouble());
		this.sugarDispenser.release(anyDouble());
	}

	@Override
	public void verificarDisponibilidadeDeIgredientes()
			throws FaltaDeIngredienteException {

		super.verificarDisponibilidadeDeIgredientes();
		this.sugarDispenser.contains(QTD_ACUCAR);
	}
}
