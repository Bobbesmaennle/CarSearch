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
        String Dateiname = Favorit.MyStringidModellName + ".txt";
        if(vorhanden)
        {
            boolean Favoritvorhanden = Favoritvorhanden(Dateiname);
            if(!Favoritvorhanden)
            {
                String Text = Textdatei.Textdateieintraglöschen("Favoriten", Dateiname);
                Textdatei.Textdateischreiben("Favoriten",Text);
            }
        }
    }

    private static boolean Favoritvorhanden (String Favorit)
    {
        boolean Favoritvorhanden = false;
        String Favoriten = Textdatei.Textdateieinlesen("Favoriten");
        if(Favoriten.contains(Favorit))
        {
            Favoritvorhanden = true;
        }
        return Favoritvorhanden;
    }

    public static ArrayList<ArrayList> Favoritenauslesen () throws IOException //Nicht fertig
    {
        boolean vorhanden = Datenverwaltung.Dateivorhanden("Favoriten");
        if(vorhanden)
        {
            ArrayList<ArrayList> fav = new ArrayList<ArrayList>();
            ArrayList<MyStringids> Favoriten = new ArrayList<MyStringids>();
            ArrayList<String> Favoritenliste = Textdatei.Textdateizeilenauslesen("Favoriten");
            for (String Favorit:Favoritenliste)
            {
                MyStringids auto = new MyStringids();
                auto.MyStringidModellName = Favorit;
                Favoriten = Autoauslesen.Autodetailsauslesen(auto);
                fav.add(Favoriten);
            }
            return fav;
        }
        return null;
    }
}
