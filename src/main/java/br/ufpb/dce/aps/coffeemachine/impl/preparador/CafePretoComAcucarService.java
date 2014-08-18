package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CafePretoComAcucarService extends CafePretoService {

	private Dispenser sugarDispenser;
	protected static final int QTD_ACUCAR = 5;

	@Override
	public void instanciarDispensers(ComponentsFactory factory) {
		super.instanciarDispensers(factory);
		this.sugarDispenser = factory.getSugarDispenser();
	}

	@Override
	public void adicionarIngredientes() {
		this.coffeePowderDispenser.release(qtdDePoDeCafe);
		this.waterDispenser.release(qtdDeAgua);
		this.sugarDispenser.release(QTD_ACUCAR);
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
