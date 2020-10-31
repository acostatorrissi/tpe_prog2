import java.util.ArrayList;

public class Carta {

	private String nombre;
	private ArrayList<Atributo> atributos;
	private Pocima pocima;
	
	public Carta() {
		this.pocima = null;
		atributos = new ArrayList<>();		
	}
	
	public Pocima getPocima() {
		return this.pocima;
	}
	
	public void aplicarPocima() {
		
		this.pocima.aplicar(this.atributos);
	}
	
	public void setPocima(Pocima pocima) {
		this.pocima = pocima;
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
	
	public Atributo getAtributo(int index) {
		
		return this.atributos.get(index);	
	}
	
	public ArrayList<Atributo> getAtributos(){
		return new ArrayList<Atributo>(this.atributos);
	}
	
	//corregido
	public boolean perteneceAlMazo(Carta carta) {
		
		if(this.atributos.size() == carta.getAtributos().size()) {
			
			for (int i = 0 ; i < this.atributos.size() ; i++) {
				
				if (this.atributos.contains(carta.getAtributos().get(i))) {
					
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
			
		return this.nombre;
	}
	
	public Atributo devolverAtributo(int i) {
		
		return atributos.get(i);	
	}
}