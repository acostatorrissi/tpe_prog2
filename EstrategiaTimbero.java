import java.util.ArrayList;
import java.util.Random;

public class EstrategiaTimbero implements Estrategia {

	@Override
	public String elegirAtributo(Carta carta) {
		
		ArrayList<String> aux = new ArrayList<>(carta.getNombresAtributos());
		
		int i = 0;
		Random random = new Random();	
		i = random.nextInt(aux.size());
		return aux.get(i);
	}
}
