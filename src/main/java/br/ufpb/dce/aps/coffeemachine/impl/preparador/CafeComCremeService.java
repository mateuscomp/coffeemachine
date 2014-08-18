package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CafeComCremeService extends CafePretoService {

	protected Dispenser creamerDispenser;

	protected static final double QTD_CREME = 1;

	@Override
	public void instanciarDispensers(ComponentsFactory factory) {
		super.instanciarDispensers(factory);
		this.creamerDispenser = factory.getCreamerDispenser();
	}

	public void adicionarIngredientes() {
		this.coffeePowderDispenser.release(QTD_PO_DE_CAFE);
		this.waterDispenser.release(QTD_AGUA);
		this.creamerDispenser.release(QTD_CREME);
	}

	public void verificarDisponibilidadeDeIgredientes()
			throws FaltaDeIngredienteException {

		super.verificarDisponibilidadeDeIgredientes();
		boolean temCreme = this.creamerDispenser.contains(QTD_CREME);
		if (!temCreme) {
			throw new FaltaDeIngredienteException(Messages.OUT_OF_CREAMER);
		}
	}

}
