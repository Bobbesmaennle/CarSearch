package sample;

import java.io.*;

public class Textdatei
{
    public static void Textdateischreiben(String filename, String eingabeText) throws IOException //Textdateischreiben
    {
        String Text = Textdateiaufbereiten(eingabeText);
        File fi = new File("");
        String verz = fi.getAbsolutePath();
        FileWriter fw = new FileWriter(verz + "/" + filename + ".txt"); //Datei mit eingegebenem Namen in Ordner des Programms
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(Text);
        bw.close();
    }

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

    private static String Textdateiaufbereiten (String Eingabetext) //Textdatei um unbenötigte Worte kürzen
    {
        String Textdatei = new String();
        if(Eingabetext.contains("string"))
        {
            Textdatei = Eingabetext.substring(7, Eingabetext.length() - 1);
        }
        else
        {
            Textdatei = Eingabetext;
        }
        return Textdatei;
    }
}
