
public class PocimaSelectiva extends Pocima{

	private double incremento;
	private String nombreAtributo;
	
	public PocimaSelectiva(String nombre, String nombreAtributo, double incremento) {
		super(nombre);
		this.incremento = incremento;
		this.nombreAtributo = nombreAtributo;
	}

	@Override
	public double aplicar(String atrNombre, double atrValor) {
		
		if (atrNombre.equals(this.nombreAtributo)) {
						
			return ( atrValor + ( atrValor * incremento ));
		}
		return atrValor;
	}
}
