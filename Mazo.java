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

			for (String nombreAtributo : atributos.keySet()) {

				cartaNueva.addAtributo(nombreAtributo, atributos.getInt(nombreAtributo));
			}

			// verificar si la carta cumple los requisitos
			if (cartasList.size() != 0) {

				if (cartaNueva.perteneceAlMazo(cartasList.get(0))) {

					agregarCarta(cartaNueva);
				}

			} else {

				agregarCarta(cartaNueva);
			}
		}

		reader.close();
	}

	public void agregarCarta(Carta carta) {

		this.cartasList.add(carta);
	}

	public void setPocima(Pocima pocima, int indice) {

		this.cartasList.get(indice).setPocima(pocima);

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

	public ArrayList<Carta> getCartas() {

		return this.cartasList;
	}
	
	public static void main(String[] args) {

		String mazoPath = "./superheroes.json";
		Mazo mazo1 = new Mazo(mazoPath);

		EstrategiaAmbicioso ambicioso = new EstrategiaAmbicioso();
		EstrategiaObstinado obstinado = new EstrategiaObstinado();
		EstrategiaTimbero timbero = new EstrategiaTimbero();

		PocimaFortalecedora pocimaF1 = new PocimaFortalecedora("Pócima fortalecedora", 0.2);
		PocimaFortalecedora pocimaF2 = new PocimaFortalecedora("Pócima fortalecedora", 0.3);
		
		PocimaFortalecedora pocimaFPlus1 = new PocimaFortalecedora("Pócima fortalecedora Plus", 0.5);
		PocimaFortalecedora pocimaFPlus2 = new PocimaFortalecedora("Pócima fortalecedora Plus", 0.6);
		
		PocimaReductora pocimaR1 = new PocimaReductora("Kriptonita", 0.25);
		PocimaReductora pocimaR2 = new PocimaReductora("Kriptonita", 0.30);
		
		PocimaReductora pocimaR3 = new PocimaReductora("Reductor de plomo", 0.55);
		PocimaReductora pocimaR4 = new PocimaReductora("Reductor de plomo", 0.60);
		
		PocimaValorFijo pocimaV1 = new PocimaValorFijo("Quiero vale 4", 4);
		PocimaValorFijo pocimaV2 = new PocimaValorFijo("Número mágico", 25);
		
		PocimaFortalecedora pocimaIF1 = new PocimaFortalecedora("Pócima selectiva fuerza", "fuerza", 0.35);
		PocimaFortalecedora pocimaIF2 = new PocimaFortalecedora("Pócima selectiva fuerza", "fuerza", 0.35);
		
		PocimaFortalecedora pocimaIP1 = new PocimaFortalecedora("Pócima selectiva peso", "peso", 0.43);
		PocimaFortalecedora pocimaIP2 = new PocimaFortalecedora("Pócima selectiva peso", "peso", 0.43);
		
		PocimaValorFijo pocimaPC = new PocimaValorFijo("Pócima valor fijo", 15);
		PocimaReductora pocimaRC = new PocimaReductora("Pócima reductora", 0.15);
		PocimaFortalecedora pocimaFC = new PocimaFortalecedora("Pócima fortalecedora", 0.2);
		
		PocimaValorFijo pocimaPC2 = new PocimaValorFijo("Pócima valor fijo", 10);
		PocimaFortalecedora pocimaPI2 = new PocimaFortalecedora("Pócima selectiva velocidad", "velocidad", 0.5);
			
		PocimaCocktail pocimaC1 = new PocimaCocktail("Pócima Cocktail");
		pocimaC1.agregarPocima(pocimaPC);
		pocimaC1.agregarPocima(pocimaRC);
		pocimaC1.agregarPocima(pocimaFC);
		
		PocimaCocktail pocimaC2 = new PocimaCocktail("Pócima Cocktail");
		pocimaC2.agregarPocima(pocimaPC2);
		pocimaC2.agregarPocima(pocimaPI2);
		
		Jugador jugador1 = new Jugador("Luis", obstinado);
		Jugador jugador2 = new Jugador("Marcelo", timbero);

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
