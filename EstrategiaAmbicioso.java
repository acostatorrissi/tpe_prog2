import java.util.ArrayList;

public class EstrategiaAmbicioso implements Estrategia {

	@Override
	public String elegirAtributo(Carta carta) {
		
		String mayor = "";
		
		ArrayList<String> aux = new ArrayList<>(carta.getNombresAtributos());
		
		for (String nombre : aux) {
			
			double valorAtributo = carta.getValorAtributoPorNombre(nombre);
			
			if(mayor == "" || valorAtributo > carta.getValorAtributoPorNombre(mayor)) {
				mayor = nombre;
			}	
		}
		return mayor;	
	}
}
