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
	public Atributo aplicar(Atributo atr) {
		
		for(Pocima pocima : this.pocimas) {
			pocima.aplicar(atr);
		}
		
		return atr;
	}

	
	
}
