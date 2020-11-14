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
	private final int PRIMERAPOS = 0; 

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

			for (String nombreAtributo : atributos.keySet()) {

				cartaNueva.addAtributo(nombreAtributo, atributos.getInt(nombreAtributo));
			}
			agregarCarta(cartaNueva);
		}
		reader.close();
	}
	
	private boolean estaVacio() {
		
		if(cartasList.size() != 0) {
			return false;
		}else {
			return true;
		}
	}

	public void agregarCarta(Carta carta) {

		if (!this.estaVacio() && carta.perteneceAlMazo(cartasList.get(PRIMERAPOS))) {
			
			this.cartasList.add(carta);
			
		}else if (this.estaVacio()) { 
		
			this.cartasList.add(carta);
		}
	}

	public void setPocima(Pocima pocima, int indice) {

		this.cartasList.get(indice).setPocima(pocima);
	}

	public Carta getPrimeraCarta() {

		Carta carta = this.cartasList.get(PRIMERAPOS);
		
		this.removerCarta(PRIMERAPOS);
		return carta;
	}

	public void removerCarta(int index) {
		
		this.cartasList.remove(index);
		
	}
	
	public void mezclarMazo() {

		Collections.shuffle(this.cartasList);
	}

	public int getCantidadAtributos() {

		return this.cartasList.get(PRIMERAPOS).getCantidadAtributos();
	}

	public ArrayList<Carta> getCartas() {

		return this.cartasList;
	}
	
	public static void main(String[] args) {

		String mazoPath = "./autos.json";  //cambiar por path local del mazo
		Mazo mazo1 = new Mazo(mazoPath);

		EstrategiaAmbicioso ambicioso = new EstrategiaAmbicioso();
		//EstrategiaObstinado obstinado = new EstrategiaObstinado();  
		EstrategiaTimbero timbero = new EstrategiaTimbero();
		
		Jugador jugador1 = new Jugador("Luis", timbero);
		Jugador jugador2 = new Jugador("Marcelo", ambicioso);
		
		PocimaFortalecedora pocimaF1 = new PocimaFortalecedora("Pócima Fortalecedora", 0.2);
		PocimaFortalecedora pocimaF2 = new PocimaFortalecedora("Pócima Fortalecedora", 0.3);
		
		PocimaFortalecedora pocimaFPlus1 = new PocimaFortalecedora("Pócima Fortalecedora Plus", 0.5);
		PocimaFortalecedora pocimaFPlus2 = new PocimaFortalecedora("Pócima Fortalecedora Plus", 0.6);
		
		PocimaFortalecedora pocimaR1 = new PocimaFortalecedora("Kriptonita", -0.25);
		PocimaFortalecedora pocimaR2 = new PocimaFortalecedora("Kriptonita", -0.30);
		
		PocimaFortalecedora pocimaR3 = new PocimaFortalecedora("Reductor de plomo", -0.55);
		PocimaFortalecedora pocimaR4 = new PocimaFortalecedora("Reductor de plomo", -0.60);
		
		PocimaValorFijo pocimaV1 = new PocimaValorFijo("Quiero vale 4", 4);
		PocimaValorFijo pocimaV2 = new PocimaValorFijo("Número mágico", 25);
		
		PocimaSelectiva pocimaIF1 = new PocimaSelectiva("Pócima Selectiva Fuerza", "fuerza", 0.35);
		PocimaSelectiva pocimaIF2 = new PocimaSelectiva("Pócima Selectiva Fuerza", "fuerza", 0.35);
		
		PocimaSelectiva pocimaIP1 = new PocimaSelectiva("Pócima Selectiva Peso", "peso", 0.43);
		PocimaSelectiva pocimaIP2 = new PocimaSelectiva("Pócima Selectiva Peso", "peso", 0.43);
		
		PocimaValorFijo pocimaPC = new PocimaValorFijo("Pócima Valor Fijo", 15);
		PocimaFortalecedora pocimaRC = new PocimaFortalecedora("Pócima Reductora", -0.15);
		PocimaFortalecedora pocimaFC = new PocimaFortalecedora("Pócima Fortalecedora", 0.2);
		
		PocimaValorFijo pocimaPC2 = new PocimaValorFijo("Pócima Valor Fijo", 10);
		PocimaSelectiva pocimaPI2 = new PocimaSelectiva("Pócima Selectiva Velocidad", "velocidad", 0.5);
		
		PocimaCocktail pocimaC3 = new PocimaCocktail("Pócima cocktail");
		PocimaValorFijo pocimaVF1 = new PocimaValorFijo("Pócima valor fijo", 14);
		PocimaFortalecedora pocimaSV = new PocimaFortalecedora("Pócima Selectiva Velocidad", 0.4);
		
		pocimaC3.agregarPocima(pocimaSV);
		pocimaC3.agregarPocima(pocimaVF1);
		
		PocimaCocktail pocimaC1 = new PocimaCocktail("Pócima Cocktail");
		pocimaC1.agregarPocima(pocimaPC);
		pocimaC1.agregarPocima(pocimaRC);
		pocimaC1.agregarPocima(pocimaFC);
		
		PocimaCocktail pocimaC2 = new PocimaCocktail("Pócima Cocktail");
		pocimaC2.agregarPocima(pocimaPC2);
		pocimaC2.agregarPocima(pocimaPI2);
		pocimaC2.agregarPocima(pocimaC3);
		
		Juego juego1 = new Juego(mazo1, jugador1, jugador2, 100);
		
		juego1.agregarPocima(pocimaF1);
		juego1.agregarPocima(pocimaF2);
		juego1.agregarPocima(pocimaFPlus1);
		juego1.agregarPocima(pocimaFPlus2);
		juego1.agregarPocima(pocimaR1);
		juego1.agregarPocima(pocimaR2);
		juego1.agregarPocima(pocimaR3);
		juego1.agregarPocima(pocimaR4);
		juego1.agregarPocima(pocimaV1);
		juego1.agregarPocima(pocimaV2);
		juego1.agregarPocima(pocimaIF1);
		juego1.agregarPocima(pocimaIF2);
		juego1.agregarPocima(pocimaIP1);
		juego1.agregarPocima(pocimaIP2);
		juego1.agregarPocima(pocimaC1);
		juego1.agregarPocima(pocimaC2);
		
		juego1.jugar();
	}
}
