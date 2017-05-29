package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Internetanfragen
{
    private static int Anfragenzähler = 0;
    private static Boolean Anfrage = false;

    private static String Internetanfrage (String Dateiname, String Internetadresse) throws IOException //Anfrage an Internet API
    {
        String JSONText = new String();
        URL edmunds = new URL(Internetadresse);
        URLConnection yc = edmunds.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
        {
            JSONText += inputLine;
        }
        in.close();
        Anfragenzähler++;
        return JSONText;
    }

    public static boolean Anfragenzähler () //Zähler für geleistete Anfragen
    {
        if (Anfragenzähler <= 25)
        {
            Anfrage = true;
        }
        else
        {
            Anfrage = false;
        }
        return Anfrage;
    }

    public static String AlleAutos() throws IOException //Anfragevorbereitung für AlleAutos
    {
        String Internetadresse = "https://api.edmunds.com/api/vehicle/v2/makes?state=used&year=2014&view=basic&fmt=json&callback=string&api_key=c95hzyxj92wzfjegtsj2376p";
        String Dateiname = "AlleAutos";
        String AlleAutos = Internetanfrage(Dateiname, Internetadresse);
        return AlleAutos;
    }

    public static String Autodetails (MyStringids auto) throws IOException //Anfragevorbereitung für AutoDetails
    {
        String Dateiname = auto.MyStringidModellName;
        String Internetadresse = "https://api.edmunds.com/api/vehicle/v2/" + auto.MyStringidMarkenNiceName + "/" + auto.MyStringidModellNiceName + "/" + auto.MyStringidJahr + "/styles?state=used&view=full&fmt=json&api_key=c95hzyxj92wzfjegtsj2376p";
        String AutoDetails = Internetanfrage(Dateiname, Internetadresse);
        return AutoDetails;
    }
}
