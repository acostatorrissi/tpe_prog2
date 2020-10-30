import java.util.ArrayList;

public class PocimaValorFijo extends Pocima{

	private int valorFijo;
	
	public PocimaValorFijo(String nombre, int valorFijo) {
		super(nombre);
		this.valorFijo = valorFijo;
	}

	@Override
	public void aplicar(ArrayList<Atributo> atributos) {
		
		for(Atributo atributo : atributos) {
			atributo.setValor(valorFijo);
		}	
	}
}