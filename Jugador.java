import java.util.ArrayList;

public class Jugador {

	private String nombre;
	//corregido
	private Mazo cartas;
	
	public Jugador (String nombre) {
		this.setNombre(nombre);
		cartas = new Mazo();
	}
	
	public void addCartas(Carta carta) {
		
		this.cartas.agregarCarta(carta);
	}
	
	public Mazo getMazo(){
		return this.cartas;
	}
	
	public void removePrimeraCarta() {
		this.cartas.getCartas();
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
