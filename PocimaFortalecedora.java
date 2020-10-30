import java.util.ArrayList;

public class PocimaFortalecedora extends Pocima{

	private double incremento;
	private String atributo;
	
	public PocimaFortalecedora(String nombre, double incremento) {
		super(nombre);
		this.incremento = incremento;
		this.atributo = null;
	}
	
	public PocimaFortalecedora(String nombre, String atributo, double incremento) {
		
		super(nombre);
		this.atributo = atributo;
		this.incremento = incremento;
		
	}
	
	public void aplicar(ArrayList<Atributo> atributos) {
		
		for(Atributo atributo : atributos) {
		
			if(this.atributo == null) {
				atributo.setValor(atributo.getValor() + (atributo.getValor() * incremento));
			}else if ( this.atributo != null && atributo.getNombre().equals(this.atributo)) {
				atributo.setValor(atributo.getValor() + (atributo.getValor() * incremento));
			}	
		}
		
	}

	
	
	
	
	
}
