import java.util.ArrayList;

public class Jugador {

	private String nombre;
	private ArrayList<Carta> cartas;
	
	public Jugador (String nombre) {
		this.setNombre(nombre);
		cartas =  new ArrayList<>();
	}
	
	public void addCartas(Carta carta) {
		
		this.cartas.add(carta);
	}
	
	public ArrayList<Carta> getCartas(){
		return this.cartas;
	}

	public int elegirRandom(Carta carta) {
		
		int random = (int) Math.random()*carta.getAtributos().size();
		
		return random;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
