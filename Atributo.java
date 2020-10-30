
public class Atributo implements Comparable<Atributo> {

	private String nombre;
	private int valor;
	   
	public Atributo(String nombre, int valor) {
		this.nombre = nombre;
		this.valor = valor;
	}

	public int getValor() {
		
		return valor;
	}
	
	public void setValor(double d) {
		 this.valor = (int) d;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	    
	@Override
	public String toString() {
			
		return this.nombre+" - " +this.valor;	
	}
		
	public String getNombre() {
		return this.nombre;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		try {
			Atributo otra = (Atributo) obj;
			return this.getNombre().equals(otra.getNombre());
		} catch (Exception e){
			return false;
		}
	}

	@Override
	public int compareTo(Atributo a) {
		
		if(this.getValor()>a.getValor()) {
			return 1;
		}else if(this.getValor()<a.getValor()) {
			return -1;
		}else {
			return 0;
		}
		
	}
}
