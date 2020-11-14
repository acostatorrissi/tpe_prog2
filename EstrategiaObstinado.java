import java.util.ArrayList;
import java.util.Random;

public class EstrategiaObstinado implements Estrategia{
	//elige primero al azar y se obstina con ese valor hasta el final del juego
	
	private boolean flag;
	private String elegido;
	
	public EstrategiaObstinado() {
		this.flag = false;
		this.elegido = "";
	}

	@Override
	public String elegirAtributo(Carta carta) {
		
		if(this.flag == false) {
			
			ArrayList<String> aux = new ArrayList<>(carta.getNombresAtributos());
			
			int i = 0;
			Random random = new Random();	
			i = random.nextInt(aux.size()); 
			this.elegido = aux.get(i);
		}	
		return this.elegido;
	}
}
