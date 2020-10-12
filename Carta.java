import java.util.ArrayList;

public class Carta {

	private String nombre;
	private ArrayList<Atributo> atributos;
	
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
	
	public ArrayList<Atributo> getAtributos(){
		return this.atributos;
	}
	
	//corregido
	public boolean perteneceAlMazo(Carta carta) {
		
		if(this.atributos.size() == carta.atributos.size()) {
			
			for (int i = 0 ; i < this.atributos.size() ; i++) {
				
				if (this.atributos.contains(carta.atributos.get(i))) {
					
				}else {
					return false;
				}
			}
			
		}else {
			return false;
		}	
		return true;
	}
		
	@Override
	public String toString() {
			
		return this.nombre + " " +this.atributos + "\n";
	}
	
	public Atributo devolverAtributo(int i) {
		
		return atributos.get(i);	
	}
}