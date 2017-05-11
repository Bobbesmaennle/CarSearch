package sample;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

public class Datenverwaltung
{
    public static String textdatei;
    public static int Anfragenzähler = 0;

    public static String Textdateieinlesen(String filename) //Textdatei mit Json Objekten einlesen
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
