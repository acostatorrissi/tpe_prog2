import java.util.Random;

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

	public Carta getPrimeraCarta() {
		return this.getMazo().getPrimeraCarta();
	}
	
 	public int getCantidadCartas() {
		return this.cartas.getCartas().size();
	}
	
	public int elegirRandom() {
		
		//corregido
		Random random = new Random();	
		return random.nextInt(this.cartas.getCantidadAtributos()); 

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		
		return this.getNombre();
		
	}
	
	
	
}
