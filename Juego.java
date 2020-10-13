
public class Juego {
	
	private int rondaActual = 1;
	private Jugador jugador1;
	private Jugador jugador2;
	private Mazo mazo;
	private int maxRondas;
	
	public Juego(Mazo mazo, Jugador jugador1, Jugador jugador2, int maxRondas) {
		
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.mazo = mazo;	
		this.maxRondas = maxRondas;
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
	
	public void jugar() {
		
		int turno = 1;
		int random = 0;
		Carta cartaJ1;
		Carta cartaJ2;
		Atributo atributoJugador1;
		Atributo atributoJugador2;
			
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
		
				random = jugador1.elegirRandom();	
				System.out.print(jugador1);
				
			}else {
				
				random = jugador2.elegirRandom(); //corregido
				System.out.print(jugador2);	
			}
			
			System.out.print(" selecciona competir por el atributo ");
			
			atributoJugador1 = cartaJ1.getAtributo(random);
			atributoJugador2 = cartaJ2.getAtributo(random);
			
			System.out.println(atributoJugador2.getNombre());
		
			System.out.println("La carta de " +jugador1+ " es " + cartaJ1.getNombre() + " con " +atributoJugador1);
			System.out.println("La carta de " +jugador2+ " es " + cartaJ2.getNombre() + " con " +atributoJugador2);
				
			//gana el j1
			if ( atributoJugador1.getValor() > atributoJugador2.getValor()) {//comparar entre cartas pasar atributo
						
				//El ganador agrega al mazo su propia carta y la de su rival (se ubican por defecto al final)
				jugador1.addCartas(cartaJ2);
				jugador1.addCartas(cartaJ1); 
				
				System.out.println("Gana la ronda " + jugador1);
				turno = 1;
				
			//gana j2		
			}else if ( atributoJugador1.getValor() < atributoJugador2.getValor() ) {
								
				jugador2.addCartas(cartaJ2);
				jugador2.addCartas(cartaJ1);
				
				System.out.println("Gana la ronda " + jugador2);
				turno = 2;
				
			//empate
			}else {
					
				jugador1.addCartas(cartaJ1);
				jugador2.addCartas(cartaJ2);
				
				System.out.println("Hubo un empate");
			}	
			rondaActual++;
			
			System.out.println(jugador1 + " posee ahora " + jugador1.getCantidadCartas() + " cartas y " + jugador2 + " posee " + jugador2.getCantidadCartas());		
		}
		System.out.println("Ganó " + getGanador());	
	}

	
		
}
