package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Internetanfragen
{
    public static void Internetanfrage (String Dateiname, String Internetadresse) throws IOException
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
        if(JSONText.contains("string"))
        {
            String Textdatei = JSONText.substring(7, JSONText.length() - 1);
            Datenverwaltung.Textdateischreiben(Dateiname, Textdatei);
        }
        else
        {
            Datenverwaltung.Textdateischreiben(Dateiname, JSONText);
        }
    }

    public static void AlleAutos() throws IOException
    {
        String Internetadresse = "https://api.edmunds.com/api/vehicle/v2/makes?state=used&year=2014&view=basic&fmt=json&callback=string&api_key=c95hzyxj92wzfjegtsj2376p";
        String Dateiname = "AlleAutos";
        Internetanfrage(Dateiname, Internetadresse);
    }

    public static void Autodetails (MyStringids auto) throws IOException
    {
        String Dateiname = auto.MyStringidModellName;
        String Internetadresse = "https://api.edmunds.com/api/vehicle/v2/" + auto.MyStringidMarkenNiceName + "/" + auto.MyStringidModellNiceName + "/" + auto.MyStringidJahr + "/styles?state=used&view=full&fmt=json&api_key=c95hzyxj92wzfjegtsj2376p";
        Internetanfrage(Dateiname, Internetadresse);
    }
}
