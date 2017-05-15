package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Autoauslesen {
    public static ArrayList Automarkenauslesen(String textdatei) //Alle Automarken
    {
        ArrayList<MyStringids> Automarken = new ArrayList<MyStringids>();
        JSONArray alleAutomarken;
        JSONObject json = new JSONObject(textdatei);
        int anzahlautomarken = json.getInt("makesCount");
        JSONArray makesarray = json.getJSONArray("makes");
        for (int i = 0; i < anzahlautomarken; i++) {
            JSONObject automarke = makesarray.getJSONObject(i);
            String automarkenname = automarke.getString("name");
            Double automarkenid = automarke.getDouble("id");
            MyStringids Myautomarke = new MyStringids();
            Myautomarke.MyStringidMarkenID = automarkenid;
            Myautomarke.MyStringidMarkenName = automarkenname;
            Automarken.add(Myautomarke);
        }
        return Automarken;
    }

    public static ArrayList Automarkenmodelleauslesen(String textdatei, MyStringids Automarke) //Alle Modelle zu einer Automarke
    {
        ArrayList<MyStringids> Automarkenmodelle = new ArrayList<MyStringids>();
        JSONObject json = new JSONObject(textdatei);
        int anzahlautomarken = json.getInt("makesCount");
        JSONArray makesarray = json.getJSONArray("makes");
        for (int i = 0; i < anzahlautomarken; i++) {
            JSONObject automarke = makesarray.getJSONObject(i);
            Double automarkenid = automarke.getDouble("id");
            if (automarkenid == Automarke.MyStringidMarkenID) {
                JSONArray automarkenmodelle = automarke.getJSONArray("models");
                int anzahlmodelle = automarkenmodelle.length();
                for (int j = 0; j < anzahlmodelle; j++) {
                    JSONObject modell = automarkenmodelle.getJSONObject(j);
                    MyStringids Myautomarkenmodell = new MyStringids();
                    Myautomarkenmodell.MyStringidModellID = modell.getString("id");
                    Myautomarkenmodell.MyStringidModellName = modell.getString("name");
                    Myautomarkenmodell.MyStringidModellNiceName = modell.getString("niceName");
                    Myautomarkenmodell.MyStringidMarkenName = automarke.getString("name");
                    Myautomarkenmodell.MyStringidMarkenID = automarke.getDouble("id");
                    Automarkenmodelle.add(Myautomarkenmodell);
                }
            }
        }
        return Automarkenmodelle;
    }

    public static ArrayList Automarkenmodelljahreauslesen(String textdatei, MyStringids Automodell) //Jahre zu einem Automodell
    {
        ArrayList<MyStringids> Automarkenmodelljahre = new ArrayList<MyStringids>();
        JSONObject json = new JSONObject(textdatei);
        int anzahlautomarken = json.getInt("makesCount");
        JSONArray makesarray = json.getJSONArray("makes");
        for (int i = 0; i < anzahlautomarken; i++) {
            JSONObject automarke = makesarray.getJSONObject(i);
            Double automarkenid = automarke.getDouble("id");
            if (automarkenid == Automodell.MyStringidMarkenID) {
                JSONArray automarkenmodelle = automarke.getJSONArray("models");
                int anzahlmodelle = automarkenmodelle.length();
                for (int j = 0; j < anzahlmodelle; j++) {
                    JSONObject modell = automarkenmodelle.getJSONObject(j);
                    String automodellid = modell.getString("id");
                    if (automodellid.equals(Automodell.MyStringidModellID)) {
                        JSONArray automodelljahre = modell.getJSONArray("years");
                        int jahreanzahl = automodelljahre.length();
                        for (int k = 0; k < jahreanzahl; k++) {
                            JSONObject jahr = automodelljahre.getJSONObject(k);
                            MyStringids Myautomarkenmodelljahr = new MyStringids();
                            Myautomarkenmodelljahr.MyStringidModellName = modell.getString("name");
                            Myautomarkenmodelljahr.MyStringidModellID = modell.getString("id");
                            Myautomarkenmodelljahr.MyStringidModellNiceName = modell.getString("niceName");
                            Myautomarkenmodelljahr.MyStringidMarkenName = automarke.getString("name");
                            Myautomarkenmodelljahr.MyStringidMarkenID = automarke.getDouble("id");
                            Myautomarkenmodelljahr.MyStringidJahrid = jahr.getDouble("id");
                            Myautomarkenmodelljahr.MyStringidJahr = jahr.getInt("year");
                            Automarkenmodelljahre.add(Myautomarkenmodelljahr);
                        }
                    }
                }
            }
        }
        return Automarkenmodelljahre;
    }
}


