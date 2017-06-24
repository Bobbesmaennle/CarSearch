package sample;

import javax.swing.*;
import java.io.IOException;

public class AlleAutos
{
    public static String AlleAutos;

    public static String AlleAutos() throws IOException //Methode um auf AlleAutos zuzugreifen
    {
        boolean immerlokalladen = Datenverwaltung.alleautoslokalladen;
            boolean Anfragen = Internetanfragen.Anfragenzähler();
            if(Anfragen)
            {
                boolean dateivorhanden = Datenverwaltung.Dateivorhanden("AlleAutos");
                if(dateivorhanden)
                {
                    boolean lokalladen = Datenverwaltung.Lokalladen("AlleAutos");
                    if(lokalladen)
                    {
                        AlleAutos = Textdatei.Textdateieinlesen("AlleAutos");
                        return AlleAutos;
                    }
                    else
                    {
                        String AlleAutosInternet = Internetanfragen.AlleAutos();
                        Textdatei.Textdateischreiben("AlleAutos",AlleAutosInternet);
                        AlleAutos = Textdatei.Textdateieinlesen("AlleAutos");
                        return AlleAutos;
                    }
                }
                else
                {
                    String AlleAutosInternet = Internetanfragen.AlleAutos();
                    Textdatei.Textdateischreiben("AlleAutos",AlleAutosInternet);
                    AlleAutos = Textdatei.Textdateieinlesen("AlleAutos");
                    return AlleAutos;
                }
            }
            else
            {
                boolean dateivorhanden = Datenverwaltung.Dateivorhanden("AlleAutos");
                if(dateivorhanden)
                {
                    AlleAutos = Textdatei.Textdateieinlesen("AlleAutos");
                    return AlleAutos;
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
