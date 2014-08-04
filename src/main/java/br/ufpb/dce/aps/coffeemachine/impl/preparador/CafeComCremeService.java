package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import static org.mockito.Matchers.anyDouble;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CafeComCremeService extends CafePretoService {

	protected Dispenser creamerDispenser;

	private static final double QTD_CREME = 1;

	@Override
	protected void instanciarDispensers(ComponentsFactory factory) {
		super.instanciarDispensers(factory);
		this.creamerDispenser = factory.getCreamerDispenser();
	}

	public void adicionarIngredientes() {
		this.coffeePowderDispenser.release(QTD_PO_DE_CAFE);
		this.waterDispenser.release(QTD_AGUA);
		this.creamerDispenser.release(QTD_CREME);

		this.display.info(Messages.RELEASING);
		this.cupDispenser.release(1);
		this.drinkDispenser.release(anyDouble());
		this.display.info(Messages.TAKE_DRINK);
	}

	public void verificarDisponibilidadeDeIgredientes()
			throws FaltaDeIngredienteException {

		super.verificarDisponibilidadeDeIgredientes();
		boolean temCreme = this.creamerDispenser.contains(QTD_CREME);
		if (!temCreme) {
			throw new FaltaDeIngredienteException("");
		}
	}

}
