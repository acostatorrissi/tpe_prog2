
public class PocimaFortalecedora extends Pocima{

	private double incremento;
	
	public PocimaFortalecedora(String nombre, double incremento) {
		super(nombre);
		this.incremento = incremento;
	}
	
	@Override
	public double aplicar(String nombreAtr, double valorAtr) {
		
		return (valorAtr + ( valorAtr * incremento ));
	}
}
