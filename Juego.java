import java.util.ArrayList;
import java.util.Random;

public class Juego {
	
	private int rondaActual = 1;
	private Jugador jugador1;
	private Jugador jugador2;
	private Jugador jugadorDeTurno;
	private Jugador ganadorRonda;
	private Atributo atributoJ1DeTurno;
	private Atributo atributoDeTurno;
	private Pocima pocimaj1 = null; 
	private Pocima pocimaj2;
	private Carta cartaJ1DeTurno;
	private Carta cartaJ2DeTurno;
	private Mazo mazo;
	private int maxRondas;
	private ArrayList<Pocima> pocimas;
	
	public Juego(Mazo mazo, Jugador jugador1, Jugador jugador2, int maxRondas) {
		
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.mazo = mazo;	
		this.maxRondas = maxRondas;
		this.pocimas = new ArrayList<>();
	}
	
	private void repartirPocimas() {
		
		for (Pocima pocima : this.pocimas) {
			Random random = new Random();	
			mazo.setPocima(pocima, random.nextInt(this.mazo.getCartas().size())); 
		}
	}
	
	public void repartir() {
		
		mazo.mezclarMazo();
		
		
		
		for (int i = 0 ; i < mazo.getCartas().size() ; i++) {		
			if ( i % 2 == 0  || i == 0 ) {			
				jugador1.addCartas(mazo.getCartas().get(i));
			}else {
				jugador2.addCartas(mazo.getCartas().get(i));
			}		
		}
	}
	
	private Jugador getGanador() {
		
		if (jugador1.getMazo().getCartas().size() == 0 || jugador1.getMazo().getCartas().size() < jugador2.getMazo().getCartas().size()) {	
			return jugador2;
			
		}else if (jugador1.getMazo().getCartas().size() == jugador2.getMazo().getCartas().size()){
			
			return null;
		}else {
			return jugador1;
		}
		
	}
	
	public void agregarPocima(Pocima pocima) {
		this.pocimas.add(pocima);
	}
	
	public String verificarPocima(Pocima pocimaj) {
		
		if(pocimaj != null ) {
			
			return " se le aplicó " + pocimaj + " - valor resultante ";
		}else {
			return "";
		}
		
	}
	
	public String toString() {
		
		String retorno = "";
		
		retorno = retorno + "------ RONDA " + rondaActual + " -------- \n" + "El jugador " + jugadorDeTurno + " selecciona competir por el atributo " +
				atributoDeTurno.getNombre() + "\nLa carta de " + jugador1 + " es " + cartaJ1DeTurno + " con " + atributoJ1DeTurno +"\n"+
				 this.verificarPocima(pocimaj1)+"\nLa carta de " + jugador2 + " es " + cartaJ2DeTurno + " con " + atributoDeTurno +"\nGana la ronda " + ganadorRonda;
		
		
		
		
		return retorno;
				
			
	}
	
	public void jugar() {
		
		int turno = 1;
		int indice = 0;
		Carta cartaJ1;
		Carta cartaJ2;
		Atributo atributoJugador1;
		Atributo atributoJugador2;
		ArrayList<String> imprimir = new ArrayList<>();
		
		repartirPocimas();
		repartir();
		
		//Se juega mientras no se supere el max de rondas y ambos jugadores tengan cartas en sus mazos
		while ( (rondaActual <= maxRondas) && ( (jugador1.getCantidadCartas() != 0) && (jugador2.getCantidadCartas() != 0)) ) {
			
			//Ambos jugadores toman la primer carta de sus respectivos mazos y la remueven del mazo
			//corregido
			cartaJ1 = jugador1.getPrimeraCarta();
			cartaJ2 = jugador2.getPrimeraCarta();
			
			System.out.println("------ RONDA " + rondaActual + " --------");
			System.out.print("El jugador ");
			
			if(turno ==1) {
				jugadorDeTurno = jugador1; //----
				indice = jugador1.elegirAtributo(cartaJ1);	
				System.out.print(jugador1);
				
			}else {
				jugadorDeTurno = jugador2; //----
				indice = jugador2.elegirAtributo(cartaJ2); //corregido
				System.out.print(jugador2);	
			}
			
			System.out.print(" selecciona competir por el atributo ");
			
			atributoJugador1 = cartaJ1.getAtributo(indice);
			atributoJugador2 = cartaJ2.getAtributo(indice);
			
			atributoDeTurno = atributoJugador2; //----------
			System.out.println(atributoJugador2.getNombre());
		
			cartaJ1DeTurno = cartaJ1; //-------
			atributoJ1DeTurno = atributoJugador1; //-------
			System.out.println("La carta de " +jugador1+ " es " + cartaJ1 + " con " +atributoJugador1);
			
			if(cartaJ1.getPocima() != null) {
				pocimaj1 = cartaJ1.getPocima();
				System.out.print("se le aplicó " + cartaJ1.getPocima()+ " - valor resultante: ");
				cartaJ1.aplicarPocima();
				
				System.out.println(atributoJugador1.getValor());
			}
			
			System.out.println("La carta de " +jugador2+ " es " + cartaJ2 + " con " +atributoJugador2);
			
			if(cartaJ2.getPocima() != null) {
				System.out.print("se le aplicó " + cartaJ2.getPocima() + " - valor resultante: ");
				cartaJ2.aplicarPocima();
				System.out.println(atributoJugador2.getValor());
			}
				
			//gana el j1
			if ( atributoJugador1.getValor() > atributoJugador2.getValor()) {//comparar entre cartas pasar atributo
					
				//El ganador agrega al mazo su propia carta y la de su rival (se ubican por defecto al final)
				jugador1.addCartas(cartaJ2);
				jugador1.addCartas(cartaJ1); 
				ganadorRonda = jugador1; //----------
				System.out.println("Gana la ronda " + jugador1);
				turno = 1;
				
			//gana j2		
			}else if ( atributoJugador1.getValor() < atributoJugador2.getValor() ) {
								
				jugador2.addCartas(cartaJ2);
				jugador2.addCartas(cartaJ1);
				
				ganadorRonda = jugador2; //----------
				System.out.println("Gana la ronda " + jugador2);
				turno = 2;
				
			//empate
			}else {
					
				jugador1.addCartas(cartaJ1);
				jugador2.addCartas(cartaJ2);
				
				System.out.println("Hubo un empate");
			}	
			
			
			System.out.println(jugador1 + " posee ahora " + jugador1.getCantidadCartas() + " cartas y " + jugador2 + " posee " + jugador2.getCantidadCartas());	
			System.out.println(this);
			rondaActual++;
		}
		System.out.println("Ganó " + getGanador());	
	}

	
		
}
