
public class PocimaValorFijo extends Pocima{

	private int valorFijo;
	
	public PocimaValorFijo(String nombre, int valorFijo) {
		super(nombre);
		this.valorFijo = valorFijo;
	}

	@Override
	public Atributo aplicar(Atributo atr) {
		
		atr.setValor(valorFijo);		
		return atr;
	}
}
