package sample;

import javax.swing.*;
import java.io.IOException;

public class Autodetails
{
    public static String AutoDetails;

    public static String AutoDetails(MyStringids AutoModell) throws IOException //Methode um auf AutoDetails zuzugreifen
    {
        String Dateiname = AutoModell.MyStringidModellName;
        boolean Anfragen = Internetanfragen.Anfragenzähler();
        if(Anfragen)
        {
            boolean dateivorhanden = Datenverwaltung.Dateivorhanden(Dateiname);
            if(dateivorhanden)
            {
                boolean lokalladen = Datenverwaltung.Lokalladen(Dateiname);
                if(lokalladen)
                {
                    AutoDetails = Textdatei.Textdateieinlesen(Dateiname);
                    return AutoDetails;
                }
                else
                {
                    String AutoDetailsInternet = Internetanfragen.Autodetails(AutoModell);
                    Textdatei.Textdateischreiben(Dateiname,AutoDetailsInternet);
                    AutoDetails = Textdatei.Textdateieinlesen(Dateiname);
                    return AutoDetails;
                }
            }
            else
            {
                String AutoDetailsInternet = Internetanfragen.Autodetails(AutoModell);
                Textdatei.Textdateischreiben(Dateiname,AutoDetailsInternet);
                AutoDetails = Textdatei.Textdateieinlesen(Dateiname);
                return AutoDetails;
            }
        }
        else
        {
            boolean dateivorhanden = Datenverwaltung.Dateivorhanden(Dateiname);
            if(dateivorhanden)
            {
                AutoDetails = Textdatei.Textdateieinlesen(Dateiname);
                return AutoDetails;
            }
            else
            {
                Object[] options = {"OK"};
                JOptionPane.showOptionDialog(null, "Die angeforderten Daten konnten nicht geladen werden. Das Programm wird beendet", "Lokale Daten", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
                System.exit( 0 );
                return null;
            }
        }
    }
}
