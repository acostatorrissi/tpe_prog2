import java.util.Random;

public class EstrategiaTimbero implements Estrategia {

	@Override
	public String elegirAtributo(Carta carta) {
		int i = 0;
		Random random = new Random();	
		i = random.nextInt(carta.getAtributos().size());
		return carta.getAtributos().get(i).getNombre();
	}

}
