package sample;

import java.io.*;
import java.util.ArrayList;

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

    public static void Textdateierweitern (String Dateiname, String Erweiterungstext)
    {
        try
        {
            File fi = new File("");
            String verz = fi.getAbsolutePath();
            File file = new File(verz + "/" + Dateiname + ".txt");
            PrintWriter writer = new PrintWriter(file);
            writer.append("\n" + Erweiterungstext);
            writer.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static String Textdateieintraglöschen (String Dateiname, String Löschen)
    {
        File fi = new File("");
        String verzeichnis = fi.getAbsolutePath();
        String[] parts = null;
        String part = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(verzeichnis + "/" + Dateiname + ".txt")));
            String line;
            while ((line = br.readLine()) != null) {
                parts = line.split("\r");
                for (String _part : parts) {
                    if(part != Löschen)
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

    public static ArrayList<String> Textdateizeilenauslesen (String Dateiname) throws FileNotFoundException
    {
        File fi = new File("");
        String verzeichnis = fi.getAbsolutePath();
        ArrayList<String> parts = new ArrayList<String>();
        BufferedReader br = null;
        int i = 0;
        try
        {
            br = new BufferedReader(new FileReader(new File(verzeichnis + "/" + Dateiname + ".txt")));
            String line;
            while ((line = br.readLine()) != null)
            {
                parts.add(line);
            }
            return parts;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
          }
            finally
            {
                if (br != null)
                {
                    try
                    {
                        br.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
          return null;
    }

}
