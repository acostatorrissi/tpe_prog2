public class Jugador {

	private String nombre;
	protected Mazo cartas;
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
	
 	public int elegirAtributo(Carta carta) {
 	
 		return this.estrategia.elegirAtributo(carta);
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
