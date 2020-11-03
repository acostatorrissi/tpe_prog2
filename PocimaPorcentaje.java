
public class PocimaPorcentaje extends Pocima{

	private double incremento;
	private String nombreAtributo;
	
	public PocimaPorcentaje(String nombre, double incremento) {
		super(nombre);
		this.incremento = incremento;
		this.nombreAtributo = null;
	}
	
	public PocimaPorcentaje(String nombre, String nombreAtributo, double incremento) {
		
		super(nombre);
		this.nombreAtributo = nombreAtributo;
		this.incremento = incremento;
	}
	
	@Override
	public Atributo aplicar(Atributo atr) {
		
		if( (this.nombreAtributo == null) || (atr.getNombre().equals(this.nombreAtributo)) ) {
			atr.setValor(atr.getValor() + ( atr.getValor() * incremento ));
		}
		return atr;
	}
}
