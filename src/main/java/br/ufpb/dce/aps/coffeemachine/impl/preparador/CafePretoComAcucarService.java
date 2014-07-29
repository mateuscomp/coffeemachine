package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CafePretoComAcucarService extends CafePretoService {

	protected static final int QTD_ACUCAR = 1;

	private Dispenser sugarDispenser;

	@Override
	public void preparar(ComponentsFactory factory)
			throws FaltaDeIngredienteException {
		this.sugarDispenser = factory.getSugarDispenser();
		super.preparar(factory);
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
	public boolean verificarDisponibilidadeDeIgredientes()
			throws FaltaDeIngredienteException {
		
		boolean temIngredientesCafePreto = super
				.verificarDisponibilidadeDeIgredientes();
		boolean temAcucar = this.sugarDispenser.contains(QTD_ACUCAR);
		if(! temAcucar){
			throw new FaltaDeIngredienteException(Messages.OUT_OF_SUGAR);
		}

		return temIngredientesCafePreto && temAcucar;
	}
}
