import java.util.ArrayList;

public class PocimaReductora extends Pocima{

	private double decremento;
	
	public PocimaReductora(String nombre, double decremento) {
		super(nombre);
		this.decremento = decremento;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void aplicar(ArrayList<Atributo> atributos) {
	
		for(Atributo atributo : atributos) {
			
			atributo.setValor(atributo.getValor() - (atributo.getValor() * decremento));
			
			
		}	
	}

}
