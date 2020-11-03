import java.util.ArrayList;
import java.util.Collections;

public class EstrategiaSonso implements Estrategia {

	@Override
	public String elegirAtributo(Carta carta) {
	
		ArrayList<Atributo> aux = new ArrayList<>(carta.getAtributos());
		Collections.sort(aux);
		return aux.get(0).getNombre();
	}

}
