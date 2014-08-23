package br.ufpb.dce.aps.coffeemachine.impl.preparador;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public interface DrinkService {
	
	public void instanciarDispensers(ComponentsFactory factory);
	public void verificarDisponibilidadeDeIgredientes() throws FaltaDeIngredienteException;
	public void adicionarIngredientes();
	public void fazerDrink();

	//	public void setFactory(ComponentsFactory factory);
	public int getValorDoDrink();
	
	public void setValorDoDrink(int valor);

}
