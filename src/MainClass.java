import javax.swing.*;
import java.awt.*;
import java.io.*;
import com.google.gson.*;

public class MainClass {
    public static String source = new String("C:\\Users\\buergi\\DHBW\\Auto.txt");
    public static JComboBox cbmodel = new JComboBox();
    public static JComboBox cbyear = new JComboBox();
    public static JComboBox cbbrand = new JComboBox();
    public static Button btnsuchen = new Button();
    public static String textdatei;

    private static String Textdateieinlesen(String filename) //Textdatei mit Json Objekten einlesen
    {
        String[] parts = null;
        String part = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(filename)));
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                parts = line.split("\r");
                for (String _part : parts) {
                    part += _part;
                }
            }
            return part;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void brandsadd(String carbrand) //Marke zur Combobox für Auto-Marken hinzufügen
    {
        cbbrand.addItem(carbrand);
    }

    public static void modeladd(String carmodel) //Model zur Combobox für Auto-Modelle hinzufügen
    {
        cbmodel.addItem(carmodel);
    }

    public static void yearadd(String caryear) //Baujhar zur Combobox für Baujahre hinzufügen
    {
        cbyear.addItem(caryear);
    }

    public static void suchenclick() //Suchen Methode
    {
        String carbrand = cbbrand.getSelectedItem().toString();
        String carmodel = cbmodel.getSelectedItem().toString();
        String caryear = cbyear.getSelectedItem().toString();
        System.out.println(carbrand + " " + carmodel + " " + caryear);
    }

    public static class Autos //Alle Automarken
    {
        java.util.List<Automarke> makes;
        int makesCount;
    }

    public static class Automarke //Automarken
    {
        int id;
        String name;
        String niceName;
        java.util.List<Automodell> models;
    }

    public static class Automodell //Automodelle der Marke
    {
        String id;
        String name;
        String niceName;
        java.util.List<Years> years;
    }

    public class Years //Jahre des Automodells
    {
        int id;
        int year;
    }

    public static void createjsonobjects() //Json Objekte aus Textdatei erstellen
    {
        textdatei = Textdateieinlesen(source); //Textdateinlesen
        Gson gson = new Gson();
        Autos userObject = gson.fromJson(textdatei, Autos.class); //Jsonobjekte erzeugen mit Auto-Klasse
    }

    public static void main(String[] args) throws Exception
    {
        createjsonobjects();
        //test();
    }

    public static void test() //Nicht fertig
    {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(textdatei);
        if (element.isJsonObject()) {
            JsonObject makes = element.getAsJsonObject();
            System.out.println();
            JsonObject models = makes.getAsJsonObject();
            System.out.println(models.get("name").getAsString());
            System.out.println(makes.get("makes").getAsString());
        }
    }

    public static void Internetanfrage() //Nicht fertig
    { /*
        URL edmunds = new URL("https://api.edmunds.com/api/vehicle/v2/makes?state=used&year=2014&view=basic&fmt=json&callback=string&api_key=c95hzyxj92wzfjegtsj2376p");
        URLConnection yc = edmunds.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
        {
                System.out.println(inputLine);
        }
        in.close();*/

    }
}
