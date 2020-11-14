public class Jugador {

	private String nombre;
	private Mazo cartas;
	private Estrategia estrategia;
	
	public Jugador (String nombre, Estrategia estrategia) {
		this.setNombre(nombre);
		this.estrategia = estrategia;
		cartas = new Mazo();
	}
	
	public void setEstrategia(Estrategia estrategia) {
		this.estrategia = estrategia;
	}
	
	public void addCartas(Carta carta) {
		
		this.cartas.agregarCarta(carta);
	}
	
	private Mazo getMazo(){
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
	
 	public String elegirAtributo(Carta carta) {
 		return this.estrategia.elegirAtributo(carta);
 	}
 	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getInformacionCartas() {
		
		return this.getNombre() + " tiene " + this.getCantidadCartas() + " cartas. ";
	}
	
	@Override
	public String toString() {
		
		return "Jugador " + this.getNombre() + "! ";	
	}
}
