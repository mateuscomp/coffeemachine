package br.ufpb.dce.aps.coffeemachine.impl.preparador;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Display;
import br.ufpb.dce.aps.coffeemachine.DrinkDispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;
import br.ufpb.dce.aps.coffeemachine.Recipe;

public class CafePretoService implements DrinkService {
//	protected ComponentsFactory factory;

	protected Dispenser cupDispenser;
	protected Dispenser waterDispenser;
	protected Dispenser coffeePowderDispenser;
	protected DrinkDispenser drinkDispenser;

	protected Display display;

	protected int qtdDeCopos = 1;
//	protected double qtdDeAgua = 100;
//	protected double qtdDePoDeCafe = 15;

//	private int valorDoCafe = 35;

	private int qtdDoDrink = 100;
	protected Recipe recipe;

	public void adicionarIngredientes() {
		this.coffeePowderDispenser.release(recipe.getIngredientQuantity(Recipe.COFFEE_POWDER));
		this.waterDispenser.release(recipe.getIngredientQuantity(Recipe.WATER));
	}

	public void instanciarDispensers(ComponentsFactory factory) {
		this.cupDispenser = factory.getCupDispenser();
		this.waterDispenser = factory.getWaterDispenser();
		this.coffeePowderDispenser = factory.getCoffeePowderDispenser();
		this.drinkDispenser = factory.getDrinkDispenser();

		this.display = factory.getDisplay();
	}

	public void verificarDisponibilidadeDeIgredientes()
			throws FaltaDeIngredienteException {

		boolean temCopoDisponivel = cupDispenser.contains(qtdDeCopos);
		if (!temCopoDisponivel) {
			throw new FaltaDeIngredienteException(Messages.OUT_OF_CUP);
		}

		boolean temAguaDisponivel = waterDispenser.contains(recipe.getIngredientQuantity(Recipe.WATER));
		if (!temAguaDisponivel) {
			throw new FaltaDeIngredienteException(Messages.OUT_OF_WATER);
		}
		boolean temPoDeCafe = this.coffeePowderDispenser
				.contains(recipe.getIngredientQuantity(Recipe.COFFEE_POWDER));
		if (!temPoDeCafe) {
			throw new FaltaDeIngredienteException(Messages.OUT_OF_COFFEE_POWDER);
		}
	}

	public void fazerDrink() {
		this.display.info(Messages.RELEASING);
		this.cupDispenser.release(qtdDeCopos);

		this.drinkDispenser.release(qtdDoDrink);
		this.display.info(Messages.TAKE_DRINK);
	}

	public int getValorDoDrink() {
		return this.recipe.getPriceCents();
	}

	public void setValorDoDrink(int valor) {
		this.recipe.setPriceCents(valor);		
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
}
