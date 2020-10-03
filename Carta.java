import java.util.ArrayList;

public class Carta {

	private String nombre;
	ArrayList<Atributo> atributos;
	
	public Carta() {
	
		atributos = new ArrayList<>();		
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		
		return this.nombre;
		
	}
	
	public void addAtributo(String nombre, int valor) {
		
		Atributo atributo = new Atributo(nombre, valor);
		this.atributos.add(atributo);	
	}
	
	public boolean perteneceAlMazo(Carta carta) {
		
			return this.atributos.size() == carta.atributos.size() && this.atributos.containsAll(carta.atributos);	
	}
		
	public String toString() {
			
		return this.nombre + " " +this.atributos + "\n";
	}
	
	public Atributo devolverAtributo(int i) {
		
		return atributos.get(i);
		
	}
	
}
