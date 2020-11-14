public abstract class Pocima {

	protected String nombre;
	
	protected Pocima(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		
		return " Se aplicó "+ this.nombre + "! Valor Modificado: ";
	}
	
	public abstract double aplicar(String nombreAtr, double valorAtr);
}
