import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class VisorMazo {

    public static void mostrarMazo(String jsonFile) {
        
        File jsonInputFile = new File(jsonFile);
        InputStream is;
        try {
            is = new FileInputStream(jsonInputFile);          
            JsonReader reader = Json.createReader(is);
            JsonArray cartas = (JsonArray) reader.readObject().getJsonArray("cartas");
            
            for (JsonObject carta : cartas.getValuesAs(JsonObject.class)) {
            	
                String nombreCarta = carta.getString("nombre");  
                JsonObject atributos = (JsonObject) carta.getJsonObject("atributos");  
                String atributosStr = "";
                
                for (String nombreAtributo:atributos.keySet())
                    atributosStr = atributosStr + nombreAtributo + ": " +
                            atributos.getInt(nombreAtributo) + "; ";
                System.out.println(nombreCarta+"\t\t\t"+atributosStr);
            }
            reader.close();

        }catch (FileNotFoundException e) {
            e.printStackTrace();      
        }
    }
}

