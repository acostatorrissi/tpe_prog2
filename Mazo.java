import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Mazo {
	
	InputStream is;
	ArrayList<Carta> cartasList;
	
	public Mazo() {
		cartasList = new ArrayList<>();
	}
	
	public void importarCartas(File jsonFile) {
		
		try {
			is = new FileInputStream(jsonFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JsonReader reader = Json.createReader(is);
		JsonArray cartas = (JsonArray) reader.readObject().getJsonArray("cartas");
			
		 for (JsonObject carta : cartas.getValuesAs(JsonObject.class)) {
			 
			 Carta carta1 = new Carta();	
             String nombreCarta = carta.getString("nombre");
             carta1.setNombre(nombreCarta);
             
             JsonObject atributos = (JsonObject) carta.getJsonObject("atributos");
             

             for (String nombreAtributo:atributos.keySet()) {
                
            	 carta1.addAtributo(nombreAtributo, atributos.getInt(nombreAtributo));
             }
           
             //verificar si la carta cumple los requisitos
            
             
            	 
        	 if (cartasList.size() != 0) {
            	 
            	 if ( carta1.perteneceAlMazo(cartasList.get(0))  ) {
            		           
            		 agregarCarta(carta1);
            	 }
            	 
             }else {
            	 
            	 agregarCarta(carta1);
            	 
             }         
		 }
		 reader.close();
	}
	
	public void agregarCarta(Carta carta) {
		
		this.cartasList.add(carta);	
	}
	
	public void mezclarMazo() {
		
		ArrayList<Carta> cartasMezcladas = new ArrayList<>();		
		
		int random = 0;
		
		while (0 < cartasList.size()) {
			
			random = (int) (Math.random()*cartasList.size());		
			
			cartasMezcladas.add(cartasList.get(random));
			cartasList.remove(random);
		}
		this.cartasList = cartasMezcladas;	
	}
	
	public static void main(String[] args) {
		
		String mazoPath = "./superheroes.json";
		File jsonInputFile = new File(mazoPath);
		
		Mazo mazo1 = new Mazo();
		Jugador jugador1 = new Jugador("Pepe");
		Jugador jugador2 = new Jugador ("Pepa");
		
		mazo1.importarCartas(jsonInputFile);
		
		Juego juego1 = new Juego(mazo1, jugador1, jugador2);
		
		juego1.jugar();
		
		
		
		
	}

}
