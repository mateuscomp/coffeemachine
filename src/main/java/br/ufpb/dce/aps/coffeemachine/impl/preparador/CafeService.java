package br.ufpb.dce.aps.coffeemachine.impl.preparador;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public interface CafeService {
	
	public void instanciarDispensers(ComponentsFactory factory);
	public void verificarDisponibilidadeDeIgredientes() throws FaltaDeIngredienteException;
	public void adicionarIngredientes();
	public void fazerCafe();
	public void setFactory(ComponentsFactory factory);

}
