import java.util.ArrayList;
import java.util.Random;

public class Juego {
	
	private int rondaActual;
	//private int indice;
	private String nombreAtributo;
	private int turno;
	private Jugador jugador1;
	private Jugador jugador2;
	private Jugador jugadorTurno;
	private String ganadorRonda;
	private Carta cartaGanadora;
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
		this.rondaActual = 1;
		this.turno = 1;
		this.nombreAtributo = "";
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
		
		if (jugador1.getCantidadCartas() == 0 || jugador1.getCantidadCartas() < jugador2.getCantidadCartas()) {	
			return jugador2;
			
		}else if (jugador1.getCantidadCartas() == jugador2.getCantidadCartas()){
			
			return null;
		}else {
			return jugador1;
		}	
	}
	
	public void agregarPocima(Pocima pocima) {
		this.pocimas.add(pocima);
	}

	public void imprimirRonda() {
		
		String imprimir = "---------Ronda " + rondaActual + "----------\n"+"El jugador " + jugadorTurno + " selecciona competir por el atributo "+ nombreAtributo + 
				"\nLa carta de " + jugador1 + " es " + cartaJ1 + " con " + valorAux1;
		 
		if(pocimaJ1 != null) {
			imprimir = imprimir + ". Se aplicó " + pocimaJ1 + "! -> Nuevo Valor: " + pocimaJ1.aplicar(cartaJ1.getCopiaAtributo(nombreAtributo)); 
		}
		
		imprimir = imprimir + "\nLa carta de " + jugador2 + " es " + cartaJ2 + " con " + valorAux2;
		
		if(pocimaJ2 != null) {
			imprimir = imprimir + ". Se aplicó " + pocimaJ2 + "! -> Nuevo Valor: " + pocimaJ2.aplicar(cartaJ2.getCopiaAtributo(nombreAtributo));  
		}
		
		imprimir = imprimir + "\nRonda finalizada. Ganador: " + ganadorRonda + ".\n" + jugador1 + " tiene ahora " + jugador1.getCantidadCartas() + " cartas y " +
				jugador2 + " tiene " + jugador2.getCantidadCartas() + " cartas.";
		
		System.out.println(imprimir);
	}
	
	public Carta getCartaGanadora(Carta c1, Carta c2, String nombreAtributo) {
		
		Atributo atrJ1 = c1.getCopiaAtributo(nombreAtributo);
		Atributo atrJ2 = c2.getCopiaAtributo(nombreAtributo);
		
		if(c1.getPocima() != null) {
			atrJ1 = c1.getPocima().aplicar(atrJ1);
		}
		if(c2.getPocima() != null) {
			atrJ2 = c2.getPocima().aplicar(atrJ2);
		}
		
		if (atrJ1.compareTo(atrJ2) > 0) {
			return c1;
		}else if(atrJ1.compareTo(atrJ2) < 0) {
			return c2;
		}else {
			return null;
		}	
	}
	
	public void imprimirGanador() {
		System.out.println("Ganó " + getGanador());
	}
	
	public void jugar() {
		
		repartirPocimas();
		repartir();
		
		//Se juega mientras no se supere el max de rondas y ambos jugadores tengan cartas en sus mazos
		while ( (rondaActual <= maxRondas) && ( (jugador1.getCantidadCartas() != 0) && (jugador2.getCantidadCartas() != 0)) ) {
					
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
			
			nombreAtributo = jugadorTurno.elegirAtributo(cartaJ1);
		
			atributoJugador1 = cartaJ1.getCopiaAtributo(nombreAtributo);
			atributoJugador2 = cartaJ2.getCopiaAtributo(nombreAtributo);

			valorAux1 = cartaJ1.getCopiaAtributo(nombreAtributo).getValor();
			valorAux2 = cartaJ2.getCopiaAtributo(nombreAtributo).getValor();
			
			
			if(cartaJ1.getPocima() != null) {	
				pocimaJ1 = cartaJ1.getPocima();
			}			
			if(cartaJ2.getPocima() != null) {
				pocimaJ2 = cartaJ2.getPocima();
			}
			
			cartaGanadora = getCartaGanadora(cartaJ1, cartaJ2, nombreAtributo);
		
			if (cartaGanadora == cartaJ1){
				
				ganadorRonda = jugador1.getNombre();
				jugador1.addCartas(cartaJ2);
				jugador1.addCartas(cartaJ1); 
				turno = 1;			
				
			}else if (cartaGanadora == cartaJ2) {	
				
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
