import java.util.Random;

public class EstrategiaObstinado implements Estrategia{

	private boolean flag;
	private String elegido;
	
	public EstrategiaObstinado() {
		this.flag = false;
		this.elegido = "";
	}

	@Override
	public String elegirAtributo(Carta carta) {
		
		if(this.flag == false) {
			int i = 0;
			Random random = new Random();	
			i = random.nextInt(carta.getAtributos().size()); 
			this.elegido = carta.getAtributos().get(i).getNombre();
		}	
		return this.elegido;
	}
}
