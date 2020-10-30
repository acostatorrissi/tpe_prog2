import java.util.ArrayList;
import java.util.Random;

public class Juego {
	
	private int rondaActual = 1;
	
	private Jugador jugador1;
	private Jugador jugador2;
	private Jugador jugadorTurno;
	private String ganadorRonda;
	private int valorAux1;
	private int valorAux2;
	private Atributo atributoJugador1;
	private Atributo atributoJugador2;
	private Pocima pocimaJ1; 
	private Pocima pocimaJ2;
	private Carta cartaJ1;
	private Carta cartaJ2;
	private Mazo mazo;
	private int maxRondas;
	private ArrayList<Pocima> pocimas;
	
	public Juego(Mazo mazo, Jugador jugador1, Jugador jugador2, int maxRondas) {
		
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.jugadorTurno = null;
		this.ganadorRonda = " empate! ";
		this.valorAux1 = 0;
		this.valorAux2 = 0;
		this.atributoJugador1 = null;
		this.atributoJugador2 = null;
		this.pocimaJ1 = null;
		this.pocimaJ2 = null;
		this.cartaJ1 = null;
		this.cartaJ2 = null;
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
	
	public void imprimirRonda() {
		
		String imprimir = "---------Ronda " + rondaActual + "----------\n"+"El jugador " + jugadorTurno + " selecciona competir por el atributo "+ atributoJugador1.getNombre() + 
				"\nLa carta de " + jugador1 + " es " + cartaJ1 + " con " + valorAux1;
		 
		if(pocimaJ1 != null) {
			imprimir = imprimir + ". Se aplicó " + pocimaJ1 + " - valor resultante: " + atributoJugador1.getValor(); 
		}
		
		imprimir = imprimir + "\nLa carta de " + jugador2 + " es " + cartaJ2 + " con " + valorAux2;
		
		if(pocimaJ2 != null) {
			imprimir = imprimir + ". Se aplicó " + pocimaJ2 + " - valor resultante: " + atributoJugador2.getValor();  
		}
		
		imprimir = imprimir + "\nRonda finalizada. Ganador: " + ganadorRonda + ".\n" + jugador1 + " tiene ahora " + jugador1.getCantidadCartas() + " y " +
				jugador2 + " tiene " + jugador2.getCantidadCartas();
		
		System.out.println(imprimir);
	}
	
	public void imprimirGanador() {
		System.out.println("Ganó " + getGanador());
	}
	
	public void jugar() {
		
		int turno = 1;
		int indice = 0;
		
		repartirPocimas();
		repartir();
		
		//Se juega mientras no se supere el max de rondas y ambos jugadores tengan cartas en sus mazos
		while ( (rondaActual <= maxRondas) && ( (jugador1.getCantidadCartas() != 0) && (jugador2.getCantidadCartas() != 0)) ) {
					
			//Ambos jugadores toman la primer carta de sus respectivos mazos y la remueven del mazo
			//corregido
			this.ganadorRonda = " empate! ";
			pocimaJ1 = null;
			pocimaJ2 = null;
			
			cartaJ1 = jugador1.getPrimeraCarta();
			cartaJ2 = jugador2.getPrimeraCarta();
						
			if(turno ==1) {
				jugadorTurno = jugador1;	
			}else {
				jugadorTurno = jugador2;
			}
			
			indice = jugadorTurno.elegirAtributo(cartaJ1);
		
			atributoJugador1 = cartaJ1.getAtributo(indice);
			atributoJugador2 = cartaJ2.getAtributo(indice);

			valorAux1 = cartaJ1.getAtributo(indice).getValor();
			//System.out.println(atributoAux1);
			valorAux2 = cartaJ2.getAtributo(indice).getValor();
			//System.out.println(atributoAux2);
			
			if(cartaJ1.getPocima() != null) {	
				pocimaJ1 = cartaJ1.getPocima();
				cartaJ1.aplicarPocima();
			}
					
			if(cartaJ2.getPocima() != null) {
				pocimaJ2 = cartaJ2.getPocima();
				cartaJ2.aplicarPocima();
			}
		
			if ( atributoJugador1.getValor() > atributoJugador2.getValor()) {//comparar entre cartas pasar atributo
				
				ganadorRonda = jugador1.getNombre();
				//El ganador agrega al mazo su propia carta y la de su rival (se ubican por defecto al final)
				jugador1.addCartas(cartaJ2);
				jugador1.addCartas(cartaJ1); 
				turno = 1;
				
			}else if ( atributoJugador1.getValor() < atributoJugador2.getValor() ) {
								
				ganadorRonda = jugador2.getNombre();
				jugador2.addCartas(cartaJ2);
				jugador2.addCartas(cartaJ1);
				turno = 2;
		
			}else {
				jugador1.addCartas(cartaJ1);
				jugador2.addCartas(cartaJ2);
			
			}	
			this.imprimirRonda();
			rondaActual++;
		}
		imprimirGanador();
	}

	
		
}
