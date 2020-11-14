import java.util.ArrayList;

public class PocimaCocktail extends Pocima {

	private ArrayList<Pocima> pocimas;
	
	public PocimaCocktail(String nombre) {
		super(nombre);
		this.pocimas = new ArrayList<>();
	}

	public void agregarPocima(Pocima pocima) {
		this.pocimas.add(pocima);
	}

	@Override
	public double aplicar(String atrNombre, double atrValor) {
				
		for(Pocima pocima : this.pocimas) {
			atrValor = pocima.aplicar(atrNombre, atrValor);
		}	
		return atrValor;
	}	
}
