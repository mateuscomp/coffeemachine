package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Display;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.DrinkDispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class FabricadorDeCafe extends Component {

	public FabricadorDeCafe(String name) {
		super(name);
	}

	@Service
	public void prepararCafePreto(Drink drink, ComponentsFactory factory) {
		int qtdCopos = 1;
		double qtdAgua = 0.5;
		double qtdPoDeCafe = 1.5;

		Dispenser cupDispenser = factory.getCupDispenser();
		Dispenser waterDispenser = factory.getWaterDispenser();
		Dispenser coffeePowderDispenser = factory.getCoffeePowderDispenser();

		boolean temCopoDisponivel = cupDispenser.contains(qtdCopos);
		boolean temAguaDisponivel = waterDispenser.contains(qtdAgua);
		boolean temPoDeCafe = coffeePowderDispenser.contains(qtdPoDeCafe);

		if (temCopoDisponivel && temAguaDisponivel && temPoDeCafe) {
			Display display = factory.getDisplay();
			display.info(Messages.MIXING);

			coffeePowderDispenser.release(qtdPoDeCafe);
			waterDispenser.release(qtdAgua);

			factory.getDisplay().info(Messages.RELEASING);
			cupDispenser.release(qtdCopos);

			DrinkDispenser drinkDispenser = factory.getDrinkDispenser();
			drinkDispenser.release(1);
			factory.getDisplay().info(Messages.TAKE_DRINK);
		}
	}
}
