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
		
		if(this.pocima == null) {
			this.pocima = pocima;
		}
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		
		return this.nombre;	
	}
	
	public double getValorAtributoPorNombre(String nombre) {
		
		double valor = this.getValorPorNombre(nombre);
		
		if (pocima != null) {
			
			valor = this.pocima.aplicar(nombre, valor);
		}
		return valor;
	}

	public double getValorPorNombre(String nombre) {
		
		for ( Atributo atributo : this.atributos ) {
			
			if ( atributo.getNombre().equals(nombre) ) {
				return atributo.getValor();
			}
		}
		return 0.0;
	}
	
	public ArrayList<String> getNombresAtributos(){
		
		ArrayList<String> aux = new ArrayList<>();
		
		for(Atributo a : this.atributos) {
			
			aux.add(a.getNombre());
		}
		return aux;	
	}
	
	public Carta getCartaGanadora(Carta cartaRival, String nombreAtributo) {
		
		double valorJ1 = this.getValorAtributoPorNombre(nombreAtributo);
		double valorJ2 = cartaRival.getValorAtributoPorNombre(nombreAtributo);
		
		if(valorJ1 > valorJ2) {
			return this;
		}else if(valorJ2 > valorJ1) {
			return cartaRival;
		}else {
			return null;
		}
	}
	
 	public void addAtributo(String nombre, int valor) {
		
		Atributo atributo = new Atributo(nombre, valor);
		this.atributos.add(atributo);	
	}
	
	public int getCantidadAtributos() {
		return this.atributos.size();
	}
	
	public boolean perteneceAlMazo(Carta carta) {
		
		if(this.atributos.size() == carta.getCantidadAtributos()) {
			
			for (int i = 0 ; i < this.atributos.size() ; i++) {
				
				if (this.getNombresAtributos().contains(carta.getNombresAtributos().get(i))) {
					
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
		
		return "La carta es " + this.nombre + " Su valor es: ";
	}
}
