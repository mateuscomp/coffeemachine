package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CafeComCremeService extends CafePretoService {

	protected Dispenser creamerDispenser;

	protected double qtdDeCreme = 20;

	public CafeComCremeService() {
		super();
		this.qtdDeCopos = 1;
		this.qtdDeAgua = 80;
		this.qtdDePoDeCafe = 15;

		this.recipe.setName("White");
		this.recipe.setPriceCents(35);
	}

	@Override
	public void instanciarDispensers(ComponentsFactory factory) {
		super.instanciarDispensers(factory);
		this.creamerDispenser = factory.getCreamerDispenser();
	}

	public void adicionarIngredientes() {
		this.coffeePowderDispenser.release(qtdDePoDeCafe);
		this.waterDispenser.release(qtdDeAgua);
		this.creamerDispenser.release(qtdDeCreme);
	}

	public void verificarDisponibilidadeDeIgredientes()
			throws FaltaDeIngredienteException {

		super.verificarDisponibilidadeDeIgredientes();
		boolean temCreme = this.creamerDispenser.contains(qtdDeCreme);
		if (!temCreme) {
			throw new FaltaDeIngredienteException(Messages.OUT_OF_CREAMER);
		}
	}

}
