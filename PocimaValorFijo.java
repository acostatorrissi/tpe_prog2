
public class PocimaValorFijo extends Pocima{

	private double valorFijo;
	
	public PocimaValorFijo(String nombre, double valorFijo) {
		super(nombre);
		this.valorFijo = valorFijo;
	}

	@Override
	public double aplicar(String atrNombre, double atrValor) {
		
		return valorFijo;
	}
}
