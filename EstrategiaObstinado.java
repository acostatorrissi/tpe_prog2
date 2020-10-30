import java.util.Random;

public class EstrategiaObstinado implements Estrategia{

	private boolean flag;
	private int elegido;
	
	public EstrategiaObstinado() {
		this.flag = false;
		this.elegido = 0;
	}

	@Override
	public int elegirAtributo(Carta carta) {
		
		if(this.flag == false) {
			Random random = new Random();	
			this.elegido = random.nextInt(carta.getAtributos().size()); 
			this.flag = true;
		}	
		return this.elegido;
	}
	
	
}
