import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import com.google.gson.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainClass {
    public static String source = new String("C:\\Users\\buergi\\DHBW\\AlleAutos.txt");
    public static JComboBox cbmodel = new JComboBox();
    public static JComboBox cbyear = new JComboBox();
    public static JComboBox cbbrand = new JComboBox();
    public static Button btnsuchen = new Button();
    public static String textdatei;
    //public static Autos AllCars;

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

    public static void main(String[] args) throws Exception
    {
        textdatei = Textdateieinlesen(source);
        Automarkenauslesen();
        //Objekteauslesen();
    }

    public static ArrayList Automarkenauslesen() //Alle Automarken
    {
        ArrayList<String> Automarken = new ArrayList<String>();
        JSONArray alleAutomarken;
        JSONObject json = new JSONObject(textdatei);
        int anzahlautomarken = json.getInt("makesCount");
        JSONArray makesarray = json.getJSONArray("makes");
        for(int i = 0; i < anzahlautomarken; i++)
        {
            JSONObject automarke = makesarray.getJSONObject(i);
            String automarkenname = automarke.getString("name");
            Automarken.add(automarkenname);
        }
        return Automarken;
    }

    public static void Objekteauslesen() //Nicht fertig
    {
        JSONObject json = new JSONObject(textdatei);

        JSONArray makesarray = json.getJSONArray("makes");
        System.out.println("makesarray: " + makesarray);

        JSONObject modelle = makesarray.getJSONObject(9);
        System.out.println("modelle: " + modelle);

        JSONArray dodgemodelle = modelle.getJSONArray("models");
        System.out.println("dodgemodelle: "+ dodgemodelle);

        JSONObject modell1 = dodgemodelle.getJSONObject(0);
        System.out.println(modell1);

        JSONArray jahr = modell1.getJSONArray("years");
        System.out.println(jahr);

        JSONObject jahr1 = jahr.getJSONObject(0);
        System.out.println(jahr1);

        int id1 = jahr1.getInt("id");
        System.out.println(id1);
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