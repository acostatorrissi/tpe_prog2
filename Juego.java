
public class Juego {

	
	private Jugador jugador1;
	private Jugador jugador2;
	private Mazo mazo;
	
	public Juego(Mazo mazo, Jugador jugador1, Jugador jugador2) {
		
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.mazo = mazo;
		
	}
	
	public void repartir() {
		
		mazo.mezclarMazo();
		
		for ( int i = 0 ; i < mazo.cartasList.size() ; i++) {
			
			if ( i % 2 == 0  || i == 0 ) {
				
				jugador1.addCartas(mazo.cartasList.get(i));
				
			}else {
				jugador2.addCartas(mazo.cartasList.get(i));
			}		
		}
		
		mazo.cartasList.clear();
		
	}
	
	
	public void jugar() {
			
		int limiteRondas = 0;
		int turno = 1;
		int random = 0;
		Carta cartaJ1;
		Carta cartaJ2;
			
		repartir();
		
		while ( (limiteRondas < 100) && ( (jugador1.getCartas().size() != 0) && (jugador2.getCartas().size() != 0)) ) {
			
			//jugador1.getCartas().size() != 0 && jugador2.getCartas().size() != 0 || 
			
			cartaJ1 = jugador1.getCartas().get(0);
			cartaJ2 = jugador2.getCartas().get(0);
			
			System.out.println("------ RONDA " + limiteRondas + " --------");
			System.out.print("El jugador ");
			
			
			if(turno ==1) {
				
				random = jugador1.elegirRandom(cartaJ1);
				
				System.out.print(jugador1.getNombre());
				
			}else {
				
				random = jugador2.elegirRandom(cartaJ2);
				
				System.out.print(jugador2.getNombre());
				
			}
			
			System.out.print(" selecciona competir por el atributo ");
			
			Atributo atributoJugador1 = cartaJ1.atributos.get(random);
			Atributo atributoJugador2 = cartaJ2.atributos.get(random);
			
			System.out.println(atributoJugador2.getNombre());
		
			System.out.println("La carta de " +jugador1.getNombre()+ " es " + cartaJ1.getNombre() + " con " +atributoJugador1.getNombre() + " - " +atributoJugador1.getValor());
			System.out.println("La carta de " +jugador2.getNombre()+ " es " + cartaJ2.getNombre() + " con " +atributoJugador2.getNombre() + " - " +atributoJugador2.getValor());
					
			if ( atributoJugador1.getValor() > atributoJugador2.getValor()) {
				//gana el j1
				
				jugador1.addCartas(cartaJ2);
				jugador1.addCartas(cartaJ1);
				
				System.out.println("Gana la ronda " + jugador1.getNombre());
				turno = 1;
					
			}else if ( atributoJugador1.getValor() < atributoJugador2.getValor() ) {
				//gana j2
				
				jugador2.addCartas(cartaJ2);
				jugador2.addCartas(cartaJ1);
				
				System.out.println("Gana la ronda " + jugador2.getNombre());
				turno = 2;
		
			}else {
				//empate
				
				System.out.println("Hubo un empate");
				
				jugador1.addCartas(cartaJ1);
				jugador2.addCartas(cartaJ2);
				
			}
				
			jugador1.getCartas().remove(0);
			jugador2.getCartas().remove(0);	
			limiteRondas++;
			
			System.out.println(jugador1.getNombre() + " posee ahora " + jugador1.getCartas().size() + " cartas y " + jugador2.getNombre() + " posee " + jugador2.getCartas().size());
			
			
			
		}
		
		if (jugador1.getCartas().size() == 0 || jugador1.getCartas().size() < jugador2.getCartas().size()) {	
			System.out.println("Ganó " + jugador2.getNombre() );
			
		}else {
			System.out.println("Ganó " + jugador1.getNombre());
		}
		
		
		
	}
	
	
}
