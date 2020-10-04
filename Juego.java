
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
		
		mazo.getCartas().clear();
		
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
		while ( (rondaActual <= maxRondas) && ( (jugador1.getCartas().size() != 0) && (jugador2.getCartas().size() != 0)) ) {
			
			//Ambos jugadores toman la primer carta de sus respectivos mazos
			//preguntar si esta bien no haber puesto el mazo del tipo Mazo
			cartaJ1 = jugador1.getCartas().get(0);
			cartaJ2 = jugador2.getCartas().get(0);
			
			System.out.println("------ RONDA " + rondaActual + " --------");
			System.out.print("El jugador ");
			
			if(turno ==1) {
		
				random = jugador1.elegirRandom(cartaJ1);
				
				System.out.print(jugador1.getNombre());
				
			}else {
				
				random = jugador2.elegirRandom(cartaJ2);
				
				System.out.print(jugador2.getNombre());	
			}
			
			System.out.print(" selecciona competir por el atributo ");
			
			atributoJugador1 = cartaJ1.getAtributos().get(random);
			atributoJugador2 = cartaJ2.getAtributos().get(random);
			
			System.out.println(atributoJugador2.getNombre());
		
			System.out.println("La carta de " +jugador1.getNombre()+ " es " + cartaJ1.getNombre() + " con " +atributoJugador1.getNombre() + " - " +atributoJugador1.getValor());
			System.out.println("La carta de " +jugador2.getNombre()+ " es " + cartaJ2.getNombre() + " con " +atributoJugador2.getNombre() + " - " +atributoJugador2.getValor());
				
			//gana el j1
			if ( atributoJugador1.getValor() > atributoJugador2.getValor()) {
						
				//El ganador agrega al mazo su propia carta y la de su rival (se ubican por defecto al final)
				jugador1.addCartas(cartaJ2);
				jugador1.addCartas(cartaJ1);
				
				System.out.println("Gana la ronda " + jugador1.getNombre());
				turno = 1;
				
			//gana j2		
			}else if ( atributoJugador1.getValor() < atributoJugador2.getValor() ) {
								
				jugador2.addCartas(cartaJ2);
				jugador2.addCartas(cartaJ1);
				
				System.out.println("Gana la ronda " + jugador2.getNombre());
				turno = 2;
				
			//empate
			}else {
				
				System.out.println("Hubo un empate");
				
				jugador1.addCartas(cartaJ1);
				jugador2.addCartas(cartaJ2);		
			}
				
			//Pase lo que pase las cartas con las que jugaron se irán del principio del mazo
			jugador1.getCartas().remove(0);
			jugador2.getCartas().remove(0);	
			
			rondaActual++;
			
			System.out.println(jugador1.getNombre() + " posee ahora " + jugador1.getCartas().size() + " cartas y " + jugador2.getNombre() + " posee " + jugador2.getCartas().size());		
		}
		
		if (jugador1.getCartas().size() == 0 || jugador1.getCartas().size() < jugador2.getCartas().size()) {	
			System.out.println("Ganó " + jugador2.getNombre() );
			
		}else {
			System.out.println("Ganó " + jugador1.getNombre());
		}	
	}
		
}
