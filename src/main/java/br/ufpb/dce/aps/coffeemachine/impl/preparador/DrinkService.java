package br.ufpb.dce.aps.coffeemachine.impl.preparador;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Recipe;

public interface DrinkService {
	
	public void instanciarDispensers(ComponentsFactory factory);
	public void verificarDisponibilidadeDeIgredientes() throws FaltaDeIngredienteException;
	public void adicionarIngredientes();
	public void fazerDrink();

	//	public void setFactory(ComponentsFactory factory);
	public int getValorDoDrink();
	
	public void setValorDoDrink(int valor);
	public void setRecipe(Recipe recipe);

}
