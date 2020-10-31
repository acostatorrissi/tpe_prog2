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

		PocimaPorcentaje pocimaF1 = new PocimaPorcentaje("Pócima Fortalecedora", 0.2);
		PocimaPorcentaje pocimaF2 = new PocimaPorcentaje("Pócima Fortalecedora", 0.3);
		
		PocimaPorcentaje pocimaFPlus1 = new PocimaPorcentaje("Pócima Fortalecedora Plus", 0.5);
		PocimaPorcentaje pocimaFPlus2 = new PocimaPorcentaje("Pócima Fortalecedora Plus", 0.6);
		
		PocimaPorcentaje pocimaR1 = new PocimaPorcentaje("Kriptonita", -0.25);
		PocimaPorcentaje pocimaR2 = new PocimaPorcentaje("Kriptonita", -0.30);
		
		PocimaPorcentaje pocimaR3 = new PocimaPorcentaje("Reductor de plomo", -0.55);
		PocimaPorcentaje pocimaR4 = new PocimaPorcentaje("Reductor de plomo", -0.60);
		
		PocimaValorFijo pocimaV1 = new PocimaValorFijo("Quiero vale 4", 4);
		PocimaValorFijo pocimaV2 = new PocimaValorFijo("Número mágico", 25);
		
		PocimaPorcentaje pocimaIF1 = new PocimaPorcentaje("Pócima Selectiva Fuerza", "fuerza", 0.35);
		PocimaPorcentaje pocimaIF2 = new PocimaPorcentaje("Pócima Selectiva Fuerza", "fuerza", 0.35);
		
		PocimaPorcentaje pocimaIP1 = new PocimaPorcentaje("Pócima Selectiva Peso", "peso", 0.43);
		PocimaPorcentaje pocimaIP2 = new PocimaPorcentaje("Pócima Selectiva Peso", "peso", 0.43);
		
		PocimaValorFijo pocimaPC = new PocimaValorFijo("Pócima Valor Fijo", 15);
		PocimaPorcentaje pocimaRC = new PocimaPorcentaje("Pócima Reductora", -0.15);
		PocimaPorcentaje pocimaFC = new PocimaPorcentaje("Pócima Fortalecedora", 0.2);
		
		PocimaValorFijo pocimaPC2 = new PocimaValorFijo("Pócima Valor Fijo", 10);
		PocimaPorcentaje pocimaPI2 = new PocimaPorcentaje("Pócima Selectiva Velocidad", "velocidad", 0.5);
			
		PocimaCocktail pocimaC1 = new PocimaCocktail("Pócima Cocktail");
		pocimaC1.agregarPocima(pocimaPC);
		pocimaC1.agregarPocima(pocimaRC);
		pocimaC1.agregarPocima(pocimaFC);
		
		PocimaCocktail pocimaC2 = new PocimaCocktail("Pócima Cocktail");
		pocimaC2.agregarPocima(pocimaPC2);
		pocimaC2.agregarPocima(pocimaPI2);
		
		Jugador jugador1 = new Jugador("Luis", timbero);
		Jugador jugador2 = new Jugador("Marcelo", ambicioso);

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
