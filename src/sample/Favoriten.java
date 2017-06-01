package sample;

import java.io.*;
import java.util.ArrayList;

public class Favoriten
{
    public static void Favorithinzufügen(MyStringids Favorit) throws IOException
    {
        Boolean vorhanden = Datenverwaltung.Dateivorhanden("Favoriten");
        String Dateiname = Favorit.MyStringidModellName;
        if(vorhanden)
        {
            boolean Favoritvorhanden = Favoritvorhanden(Dateiname);
            if(!Favoritvorhanden)
            {
                Textdatei.Textdateierweitern("Favoriten", Dateiname);
            }
        }
        else
            {
                Textdatei.Textdateischreiben("Favoriten", Dateiname);
            }
    }

    public static void Favoritentfernen (MyStringids Favorit) throws IOException
    {
        Boolean vorhanden = Datenverwaltung.Dateivorhanden("Favoriten");
        String Dateiname = Favorit.MyStringidModellName;
        if(vorhanden)
        {
            boolean Favoritvorhanden = Favoritvorhanden(Dateiname);
            if(Favoritvorhanden)
            {
                String Text = Textdatei.Textdateieintraglöschen("Favoriten", Dateiname);
                Textdatei.Textdateineuschreiben("Favoriten",Text);
            }
        }
    }

    private static boolean Favoritvorhanden (String Favorit) throws FileNotFoundException
    {
        boolean Favoritvorhanden = false;
        ArrayList<String> Favoriten = Textdatei.Textdateizeilenauslesen("Favoriten");
        for (String Zeile:Favoriten)
        {
            if(Zeile.equals(Favorit))
            {
                Favoritvorhanden = true;
                return Favoritvorhanden;
            }
        }
        return Favoritvorhanden;
    }

    public static ArrayList<ArrayList<MyStringids>> Favoritenauslesen () throws IOException //Nicht fertig
    {
        boolean vorhanden = Datenverwaltung.Dateivorhanden("Favoriten");
        if(vorhanden)
        {
            ArrayList<ArrayList<MyStringids>> AlleFavoriten = new ArrayList<ArrayList<MyStringids>>();
            ArrayList<MyStringids> Favoriten = new ArrayList<MyStringids>();
            ArrayList<String> Favoritenliste = Textdatei.Textdateizeilenauslesen("Favoriten");
            for (String Favorit:Favoritenliste)
            {
                MyStringids auto = new MyStringids();
                auto.MyStringidModellName = Favorit;
                Favoriten = Autoauslesen.Autodetailsauslesen(auto);
                AlleFavoriten.add(Favoriten);
            }
            return AlleFavoriten;
        }
        return null;
    }
}
