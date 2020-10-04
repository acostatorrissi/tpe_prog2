
public class Atributo {

	private String nombre;
	private int valor;
	   
	public Atributo(String nombre, int valor) {
		this.nombre = nombre;
		this.valor = valor;
	}

	public int getValor() {
		return valor;
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
}
