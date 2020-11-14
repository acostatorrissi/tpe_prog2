import java.util.ArrayList;
import java.util.Random;

public class Juego {
	
	private int maxRondas;
	private int rondaActual;
	private String nombreAtributo;
	private Jugador jugador1;
	private Jugador jugador2;
	private Jugador ganadorRonda;
	private Jugador ganadorAnterior;
	private Carta cartaJ1;
	private Carta cartaJ2;
	private Carta cartaGanadora;
	private Mazo mazo;
	private ArrayList<Pocima> pocimas;
	
	public Juego(Mazo mazo, Jugador jugador1, Jugador jugador2, int maxRondas) {
		
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.rondaActual = 1;
		this.nombreAtributo = "";
		this.ganadorRonda = jugador1;
		this.ganadorAnterior = null;
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
	
	private void repartir() {
		
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

	private void imprimirRonda() {
		
		Object p1 = null;
		Object p2 = null;
		
		if ( cartaJ1.getPocima() != null) {
			p1 = cartaJ1.getPocima();
			p1 = p1.toString() + cartaJ1.getValorAtributoPorNombre(nombreAtributo);
		}else {
			p1 = (String) " ";
		}
		
		if ( cartaJ2.getPocima() != null) {
			p2 = cartaJ2.getPocima();
			p2 = p2.toString() + cartaJ2.getValorAtributoPorNombre(nombreAtributo);
		}else {
			p2 = (String) " ";
		}
			
		String imprimir = "---------Ronda " + rondaActual + "----------\n"+ 
				ganadorAnterior + "Selecciona competir por el atributo "+ nombreAtributo + 
				"\n" + jugador1 + cartaJ1 + cartaJ1.getValorPorNombre(nombreAtributo) +p1;
		
		imprimir += "\n" + jugador2 + cartaJ2 + cartaJ2.getValorPorNombre(nombreAtributo)+p2;
	
		imprimir += "\nRonda finalizada. Ganador: " + ganadorRonda + "\n" + jugador1.getInformacionCartas() + jugador2.getInformacionCartas();
		
		System.out.println(imprimir);
	}
	
	private void imprimirGanador() {
		System.out.println(getGanador() + "GANADOR!");
	}
	
	public void jugar() {
		
		repartirPocimas();
		repartir();
	
		while ( (rondaActual <= maxRondas) && ( (jugador1.getCantidadCartas() != 0) && (jugador2.getCantidadCartas() != 0)) ) {

			cartaJ1 = jugador1.getPrimeraCarta();
			cartaJ2 = jugador2.getPrimeraCarta();
			
			if ( ganadorRonda ==  jugador1) {
				nombreAtributo = jugador1.elegirAtributo(cartaJ1);
			}else if ( ganadorRonda == jugador2) {
				nombreAtributo = jugador2.elegirAtributo(cartaJ2);
			}
		
			cartaGanadora = cartaJ1.getCartaGanadora(cartaJ2, nombreAtributo);
			ganadorAnterior = ganadorRonda;
			
			if (cartaGanadora == cartaJ1){  //comparamos con == porque la direccion de memoria va a ser la misma
				
				jugador1.addCartas(cartaJ2);
				jugador1.addCartas(cartaJ1);
				ganadorRonda = jugador1;
				
			}else if (cartaGanadora == cartaJ2) {	
				
				jugador2.addCartas(cartaJ2);
				jugador2.addCartas(cartaJ1);
				ganadorRonda = jugador2;
	
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
