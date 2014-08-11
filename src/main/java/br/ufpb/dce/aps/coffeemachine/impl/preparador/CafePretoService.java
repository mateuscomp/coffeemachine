package br.ufpb.dce.aps.coffeemachine.impl.preparador;

public class CafePretoService extends CafeAbstractService {

	@Override
	public void adicionarIngredientes() {
		this.coffeePowderDispenser.release(QTD_PO_DE_CAFE);
		this.waterDispenser.release(QTD_AGUA);
	}
}
