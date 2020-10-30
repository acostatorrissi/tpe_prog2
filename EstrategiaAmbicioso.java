import java.util.ArrayList;
import java.util.Collections;

public class EstrategiaAmbicioso implements Estrategia {

	@Override
	public int elegirAtributo(Carta carta) {
		
		ArrayList<Atributo> aux = new ArrayList<>(carta.getAtributos());
		Collections.sort(aux);
		Collections.reverse(aux);
		return carta.getAtributos().indexOf(aux.get(0));
		
	}
	
}
