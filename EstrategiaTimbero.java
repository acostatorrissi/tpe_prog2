import java.util.Random;

public class EstrategiaTimbero implements Estrategia {

	@Override
	public int elegirAtributo(Carta carta) {
		Random random = new Random();	
		return random.nextInt(carta.getAtributos().size()); 
	}

}
