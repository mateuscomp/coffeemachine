package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CafePretoService extends CafeService {
	
	protected static final int QTD_COPOS = 1;
	protected static final double QTD_AGUA = 0.5;
	protected static final double QTD_PO_DE_CAFE = 1.5;
		
	public void preparar(ComponentsFactory factory) {
		super.preparar(factory);
	}	
	
	@Override
	public boolean verificarDisponibilidadeDeIngredientes(){
		boolean temCopoDisponivel = cupDispenser.contains(QTD_COPOS);
		boolean temAguaDisponivel = waterDispenser.contains(QTD_AGUA);
		boolean temPoDeCafe = coffeePowderDispenser.contains(QTD_PO_DE_CAFE);
		
		return temCopoDisponivel && temAguaDisponivel && temPoDeCafe;
	}

	@Override
	public void adicionarIngredientes() {
		this.coffeePowderDispenser.release(QTD_PO_DE_CAFE);
		this.waterDispenser.release(QTD_AGUA);

		this.display.info(Messages.RELEASING);
		this.cupDispenser.release(QTD_COPOS);

		this.drinkDispenser.release(1);
		this.display.info(Messages.TAKE_DRINK);
	}
}
