package sample;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.net.*;
import java.util.Calendar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import javafx.application.Application;

public class MainClass extends Application {
    public static String textdatei;
    public static int Anfragenzähler = 0;
    public static boolean AlleAutosvorhanden;

    private static String Textdateieinlesen(String filename) //Textdatei mit Json Objekten einlesen
    {
        File fi = new File("");
        String verzeichnis = fi.getAbsolutePath();
        String[] parts = null;
        String part = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(verzeichnis + "/" + filename + ".txt")));
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
        //cbbrand.addItem(carbrand);
    }

    public static void modeladd(String carmodel) //Model zur Combobox für Auto-Modelle hinzufügen
    {
        //cbmodel.addItem(carmodel);
    }

    public static void yearadd(String caryear) //Baujahr zur Combobox für Baujahre hinzufügen
    {
        //cbyear.addItem(caryear);
    }

    public static void suchenclick() //Suchen Methode
    {
//        String carbrand = cbbrand.getSelectedItem().toString();
//        String carmodel = cbmodel.getSelectedItem().toString();
//        String caryear = cbyear.getSelectedItem().toString();
//        System.out.println(carbrand + " " + carmodel + " " + caryear);
    }

    public static void main(String[] args) throws Exception {
        Textdateivorhanden("AlleAutos", "https://api.edmunds.com/api/vehicle/v2/makes?state=used&year=2014&view=basic&fmt=json&callback=string&api_key=c95hzyxj92wzfjegtsj2376p");
        launch(args);
    }

    public static void Dateivorhanden(String Filename) {
        File fi = new File("");
        String verz = fi.getAbsolutePath();
        File file = new File(verz + "/" + Filename + ".txt");
        if (file.exists()) {
            AlleAutosvorhanden = true;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("CarSearcher");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setMaximized(true);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static ArrayList Automarkenauslesen() //Alle Automarken
    {
        ArrayList<MyStringids> Automarken = new ArrayList<MyStringids>();
        JSONArray alleAutomarken;
        JSONObject json = new JSONObject(textdatei);
        int anzahlautomarken = json.getInt("makesCount");
        JSONArray makesarray = json.getJSONArray("makes");
        for (int i = 0; i < anzahlautomarken; i++) {
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
        for (int i = 0; i < anzahlautomarken; i++) {
            JSONObject automarke = makesarray.getJSONObject(i);
            Double automarkenid = automarke.getDouble("id");
            if (automarkenid == Automarke.MyStringidMarkenID) {
                JSONArray automarkenmodelle = automarke.getJSONArray("models");
                int anzahlmodelle = automarkenmodelle.length();
                for (int j = 0; j < anzahlmodelle; j++) {
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

    public static ArrayList Automarkenmodelljahreauslesen(MyStringids Automodell) //Jahre zu einem Automodell
    {
        ArrayList<MyStringids> Automarkenmodelljahre = new ArrayList<MyStringids>();
        JSONObject json = new JSONObject(textdatei);
        int anzahlautomarken = json.getInt("makesCount");
        JSONArray makesarray = json.getJSONArray("makes");
        for (int i = 0; i < anzahlautomarken; i++) {
            JSONObject automarke = makesarray.getJSONObject(i);
            Double automarkenid = automarke.getDouble("id");
            if (automarkenid == Automodell.MyStringidMarkenID) {
                JSONArray automarkenmodelle = automarke.getJSONArray("models");
                int anzahlmodelle = automarkenmodelle.length();
                for (int j = 0; j < anzahlmodelle; j++) {
                    JSONObject modell = automarkenmodelle.getJSONObject(j);
                    String automodellid = modell.getString("id");
                    if (automodellid.equals(Automodell.MyStringidModellID)) {
                        JSONArray automodelljahre = modell.getJSONArray("years");
                        int jahreanzahl = automodelljahre.length();
                        for (int k = 0; k < jahreanzahl; k++) {
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

    public static void Internetanfrage(String Internetadresse, String Dateiname) throws IOException //Internetanfrage
    {
        String Textdatei = new String();
        URL edmunds = new URL(Internetadresse);
        URLConnection yc = edmunds.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            Textdatei += inputLine;
        }
        in.close();
        String Textdatei1 = Textdatei.substring(7, Textdatei.length() - 1);
        Textdateischreiben(Dateiname, Textdatei1);
    }

    public static void Textdateivorhanden(String Dateiname, String Internetadresse) throws IOException
    {
        if (Anfragenzähler <= 25) {
            File fi = new File("");
            String verz = fi.getAbsolutePath();
            Object[] options = {"Ja, Daten lokal laden", "Nein, Daten online laden"};
            File file = new File(verz + "/" + Dateiname + ".txt");
            if (file.exists()) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(file.lastModified());
                int n = JOptionPane.showOptionDialog(null, "Die angeforderten Daten wurden lokal gefunden. Sie stammen vom " + cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH) + 1) + "." + cal.get(Calendar.YEAR) + ". Sollen sie geladen werden?", "Lokale Daten", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (n == 0) {
                    textdatei = Textdateieinlesen(Dateiname);
                } else {
                    Internetanfrage(Internetadresse, Dateiname);
                    textdatei = Textdateieinlesen(Dateiname);
                }
            } else {
                Internetanfrage(Internetadresse, Dateiname);
                textdatei = Textdateieinlesen(Dateiname);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Das Maximum an Anfragen pro Tag ist erreicht. Die Daten können nicht online abgerufen werden.", "Anfragenmaximum", JOptionPane.INFORMATION_MESSAGE);
            File fi = new File("");
            String verz = fi.getAbsolutePath();
            Object[] options = {"Ja, Daten lokal laden", "Nein, Programm schließen"};
            File file = new File(verz + "/" + Dateiname + ".txt");
            if (file.exists()) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(file.lastModified());
                int n = JOptionPane.showOptionDialog(null, "Die angeforderten Daten wurden lokal gefunden. Sie stammen vom " + cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH) + 1) + "." + cal.get(Calendar.YEAR) + ". Sollen sie geladen werden?", "Lokale Daten", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (n == 0) {
                    textdatei = Textdateieinlesen(Dateiname);
                } else {
                    System.exit(0);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Die angeforderten Daten konnten nicht gefunden werden. Das Programm beendet sich.", "Beenden", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public static void Textdateischreiben(String filename, String eingabeText) throws IOException //Textdateischreiben
    {
        File fi = new File("");
        String verz = fi.getAbsolutePath();
        FileWriter fw = new FileWriter(verz + "/" + filename + ".txt"); //Datei mit eingegebenem Namen in Ordner des Programms
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(eingabeText);
        bw.close();
    }
}