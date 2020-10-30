import java.util.ArrayList;

public abstract class Pocima {

	protected String nombre;
	
	protected Pocima(String nombre) {
		this.nombre = nombre;
	}
	
	public String toString() {
		
		return this.nombre;
	}
	
	public abstract void aplicar(ArrayList<Atributo> atributos);
}
