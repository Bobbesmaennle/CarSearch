import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainClass {
    public static String source = new String("C:\\Users\\buergi\\DHBW\\AlleAutos.txt");
    public static JComboBox cbmodel = new JComboBox();
    public static JComboBox cbyear = new JComboBox();
    public static JComboBox cbbrand = new JComboBox();
    public static Button btnsuchen = new Button();
    public static String textdatei;

    private static String Textdateieinlesen(String filename) //Textdatei mit Json Objekten einlesen
    {
        File fi = new File("");
        String verz = fi.getAbsolutePath();
        String[] parts = null;
        String part = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(verz + "/" + filename + ".txt")));
            String line;
            while ((line = br.readLine()) != null) {
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

    public static void yearadd(String caryear) //Baujahr zur Combobox für Baujahre hinzufügen
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
        System.out.println("klappt");
        textdatei = Textdateieinlesen("AlleAutos");
        ArrayList<MyStringids> ergebnis = new ArrayList<MyStringids>();
        MyStringids Myautomarke = new MyStringids();
        Myautomarke.MyStringidMarkenID = 200009788;
        Myautomarke.MyStringidMarkenName = "Dodge";
        Myautomarke.MyStringidModellNiceName = "avenger";
        Myautomarke.MyStringidModellID = "Dodge_Avenger";
        Myautomarke.MyStringidModellName = "Avenger";
        Automarkenauslesen();
        Automarkenmodelleauslesen(Myautomarke);
        ergebnis = Automarkenmodelljahreauslesen(Myautomarke);
        for (MyStringids auto: ergebnis
             ) {
            System.out.println(auto.MyStringidMarkenName + auto.MyStringidMarkenID + auto.MyStringidModellName + auto.MyStringidModellID + auto.MyStringidModellNiceName + auto.MyStringidJahr + auto.MyStringidJahrid);
        }
    }

    public static class MyStringids //Allgemeine Klasse für Objekte mit String, Id
    {
        String MyStringidMarkenName = null;
        double MyStringidMarkenID = 0;
        String MyStringidModellName = null;
        String MyStringidModellNiceName = null;
        String MyStringidModellID = null;
        int MyStringidJahr = 0;
        double MyStringidJahrid = 0;
    }

    public static ArrayList Automarkenauslesen() //Alle Automarken
    {
        ArrayList<MyStringids> Automarken = new ArrayList<MyStringids>();
        JSONArray alleAutomarken;
        JSONObject json = new JSONObject(textdatei);
        int anzahlautomarken = json.getInt("makesCount");
        JSONArray makesarray = json.getJSONArray("makes");
        for(int i = 0; i < anzahlautomarken; i++)
        {
            JSONObject automarke = makesarray.getJSONObject(i);
            String automarkenname = automarke.getString("name");
            Double automarkenid = automarke.getDouble("id");
            MyStringids Myautomarke = new MyStringids();
            Myautomarke.MyStringidMarkenID = automarkenid;
            Myautomarke.MyStringidMarkenName = automarkenname;
            Automarken.add(Myautomarke);
        }
        return Automarken;
    }

    public static ArrayList Automarkenmodelleauslesen(MyStringids Automarke) //Alle Modelle zu einer Automarke
    {
        ArrayList<MyStringids> Automarkenmodelle = new ArrayList<MyStringids>();
        JSONObject json = new JSONObject(textdatei);
        int anzahlautomarken = json.getInt("makesCount");
        JSONArray makesarray = json.getJSONArray("makes");
        for(int i = 0; i < anzahlautomarken; i++)
        {
            JSONObject automarke = makesarray.getJSONObject(i);
            Double automarkenid = automarke.getDouble("id");
            if(automarkenid == Automarke.MyStringidMarkenID)
            {
                JSONArray automarkenmodelle = automarke.getJSONArray("models");
                int anzahlmodelle = automarkenmodelle.length();
                for(int j = 0; j < anzahlmodelle; j++)
                {
                    JSONObject modell = automarkenmodelle.getJSONObject(j);
                    MyStringids Myautomarkenmodell = new MyStringids();
                    Myautomarkenmodell.MyStringidModellID = modell.getString("id");
                    Myautomarkenmodell.MyStringidModellName = modell.getString("name");
                    Myautomarkenmodell.MyStringidModellNiceName = modell.getString("niceName");
                    Myautomarkenmodell.MyStringidMarkenName = automarke.getString("name");
                    Myautomarkenmodell.MyStringidMarkenID = automarke.getDouble("id");
                    Automarkenmodelle.add(Myautomarkenmodell);
                }
            }
        }
        return Automarkenmodelle;
    }

    public static ArrayList Automarkenmodelljahreauslesen (MyStringids Automodell) //Jahre zu einem Automodell
    {
        ArrayList<MyStringids> Automarkenmodelljahre = new ArrayList<MyStringids>();
        JSONObject json = new JSONObject(textdatei);
        int anzahlautomarken = json.getInt("makesCount");
        JSONArray makesarray = json.getJSONArray("makes");
        for(int i = 0; i < anzahlautomarken; i++)
        {
            JSONObject automarke = makesarray.getJSONObject(i);
            Double automarkenid = automarke.getDouble("id");
            if(automarkenid == Automodell.MyStringidMarkenID)
            {
                JSONArray automarkenmodelle = automarke.getJSONArray("models");
                int anzahlmodelle = automarkenmodelle.length();
                for(int j = 0; j < anzahlmodelle; j++)
                {
                    JSONObject modell = automarkenmodelle.getJSONObject(j);
                    String automodellid = modell.getString("id");
                    if(automodellid.equals(Automodell.MyStringidModellID))
                    {
                        JSONArray automodelljahre = modell.getJSONArray("years");
                        int jahreanzahl = automodelljahre.length();
                        for(int k = 0; k < jahreanzahl; k++)
                        {
                            JSONObject jahr = automodelljahre.getJSONObject(k);
                            MyStringids Myautomarkenmodelljahr = new MyStringids();
                            Myautomarkenmodelljahr.MyStringidModellName = modell.getString("name");
                            Myautomarkenmodelljahr.MyStringidModellID = modell.getString("id");
                            Myautomarkenmodelljahr.MyStringidModellNiceName = modell.getString("niceName");
                            Myautomarkenmodelljahr.MyStringidMarkenName = automarke.getString("name");
                            Myautomarkenmodelljahr.MyStringidMarkenID = automarke.getDouble("id");
                            Myautomarkenmodelljahr.MyStringidJahrid = jahr.getDouble("id");
                            Myautomarkenmodelljahr.MyStringidJahr = jahr.getInt("year");
                            Automarkenmodelljahre.add(Myautomarkenmodelljahr);
                        }
                    }
                }
            }
        }
        return Automarkenmodelljahre;
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

    public static void Textdateischreiben(String filename, String eingabeText) throws IOException
    {
        File fi = new File("");
        String verz = fi.getAbsolutePath();
        FileWriter fw = new FileWriter(verz + "/" + filename + ".txt");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(eingabeText);
        bw.close();
    }

}