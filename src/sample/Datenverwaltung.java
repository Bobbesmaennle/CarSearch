package sample;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

public class Datenverwaltung {
    public static boolean alleautoslokalladen = false;
    public static String[] abgefragteDateien = new String[500];
    public static int index = 0;

    public static Boolean Dateivorhanden(String Dateiname) //Überprüfen ob Datei vorhanden ist
    {
        Boolean vorhanden = false;
        File fi = new File("");
        String verz = fi.getAbsolutePath();
        File file = new File(verz + "/" + Dateiname + ".txt");
        if (file.exists()) {
            vorhanden = true;
        } else {
            vorhanden = false;
        }
        return vorhanden;
    }

    public static Boolean Lokalladen(String Dateiname) //Benutzer fragen ob die Datei lokal oder aus dem Internet geladen werden soll
    {
        File fi = new File("");
        String verzeichnis = fi.getAbsolutePath();
        File file = new File(verzeichnis + "/" + Dateiname + ".txt");
        Boolean lokalladen = false;
        Boolean nichtlokal = false;
        Object[] options = {"Ja, Daten lokal laden", "Nein, Daten online laden"};
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(file.lastModified());
        for (String Datei : abgefragteDateien) {
            if (Datei != null) {
            if (Datei.equals(Dateiname)) {
                lokalladen = true;
                return lokalladen;
            } else {
                nichtlokal = true;
                }
            } else {
                nichtlokal = true;
            }
            }
            if(nichtlokal)
            {
                lokalladen = Lokalanfrage(Dateiname);
            }
            return lokalladen;
        }

        public static Boolean Lokalanfrage (String Dateiname)
        {
            File fi = new File("");
            String verzeichnis = fi.getAbsolutePath();
            File file = new File(verzeichnis + "/" + Dateiname + ".txt");
            Object[] options = {"Ja, Daten lokal laden", "Nein, Daten online laden"};
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(file.lastModified());
            Boolean lokalladen = false;
            int n = JOptionPane.showOptionDialog(null, "Die angeforderten Daten " + Dateiname + " wurden lokal gefunden. Sie stammen vom " + cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH) + 1) + "." + cal.get(Calendar.YEAR) + ". Sollen sie geladen werden?", "Lokale Daten", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (n == 0) {
                abgefragteDateien[index] = Dateiname;
                lokalladen = true;
                index++;
            } else if(n == 1) {
                lokalladen = false;
                return lokalladen;
            }
            else
            {
                abgefragteDateien[index] = Dateiname;
                lokalladen = true;
                return lokalladen;
            }
            return lokalladen;
        }
    }
