public abstract class Pocima {

	protected String nombre;
	
	protected Pocima(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		
		return this.nombre;
	}
	
	public abstract Atributo aplicar(Atributo atr);
	
}
