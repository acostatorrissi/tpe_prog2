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
	
	public Atributo getCopiaAtributo(String nombreAtributo) {
		
		for (Atributo atributo : this.atributos) {
			if(atributo.getNombre().equals(nombreAtributo)) {
				return new Atributo(atributo.getNombre(), atributo.getValor());
			}
		}
		return null;
	}
	
	public ArrayList<Atributo> getAtributos(){
		return new ArrayList<Atributo>(this.atributos);
	}
	
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
	
}
