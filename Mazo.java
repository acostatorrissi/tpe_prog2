import java.util.ArrayList;
import java.util.Collections;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Mazo {
	
	private InputStream is;
	private JsonReader reader;
	private JsonArray cartas;
	private File jsonInputFile;
	private ArrayList<Carta> cartasList;
	
	public Mazo(String mazoPath) {
		cartasList = new ArrayList<>();
		this.importarCartas(mazoPath);
	}
	
	public Mazo() {
		
		cartasList = new ArrayList<>();
		
	}
	
	public void importarCartas(String mazoPath) {
		
		jsonInputFile = new File(mazoPath);
		
		try {
			is = new FileInputStream(jsonInputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		reader = Json.createReader(is);
		cartas = (JsonArray) reader.readObject().getJsonArray("cartas");
			
		 for (JsonObject carta : cartas.getValuesAs(JsonObject.class)) {
			 
			 Carta cartaNueva = new Carta();	
             String nombreCarta = carta.getString("nombre");
             cartaNueva.setNombre(nombreCarta);
             
             JsonObject atributos = (JsonObject) carta.getJsonObject("atributos");
             
             for (String nombreAtributo:atributos.keySet()) {
                
            	 cartaNueva.addAtributo(nombreAtributo, atributos.getInt(nombreAtributo));
             }
           
             //verificar si la carta cumple los requisitos	 
        	 if (cartasList.size() != 0) {
            	 
            	 if (cartaNueva.perteneceAlMazo(cartasList.get(0))) {
            		           
            		 agregarCarta(cartaNueva);
            	 }
            	 
             }else {
            	 
            	 agregarCarta(cartaNueva);    	 
             }         
		 }
		 
		 reader.close();
	}
	
	public void agregarCarta(Carta carta) {
		
		this.cartasList.add(carta);	
	}
	
	public Carta getPrimeraCarta() {
		
		Carta carta = this.cartasList.get(0);
		this.cartasList.remove(0);
		
		return carta;
	}
	
	public void mezclarMazo() {
		
		Collections.shuffle(this.cartasList);
	}

	public int getCantidadAtributos() {
		
		return this.cartasList.get(0).getAtributos().size();
		
	}
	
	public ArrayList<Carta> getCartas(){
		
		return this.cartasList;		
	}
	
	public static void main(String[] args) {
		
		String mazoPath = "./superheroes.json";	
		Mazo mazo1 = new Mazo(mazoPath);
		
		Jugador jugador1 = new Jugador("Luis");
		Jugador jugador2 = new Jugador ("Marcelo");
		
		Juego juego1 = new Juego(mazo1, jugador1, jugador2, 100);
		
		juego1.jugar();
	}

}
