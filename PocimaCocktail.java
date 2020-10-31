import java.util.ArrayList;

public class PocimaCocktail extends Pocima {

	private ArrayList<Pocima> pocimas;
	
	public PocimaCocktail(String nombre) {
		super(nombre);
		this.pocimas = new ArrayList<>();
	}

	@Override
	public void aplicar(ArrayList<Atributo> atributos) {
		
		for(Pocima pocima : this.pocimas) {
			pocima.aplicar(atributos);	
		}
	}
	
	public void agregarPocima(Pocima pocima) {
		this.pocimas.add(pocima);
	}

	
	
}
