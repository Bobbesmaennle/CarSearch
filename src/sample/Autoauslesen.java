package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Autoauslesen
{
    public static ArrayList Automarkenauslesen() throws IOException //Alle Automarken
    {
        String textdatei = AlleAutos.AlleAutos();
        ArrayList<MyStringids> Automarken = new ArrayList<MyStringids>();
        JSONObject json = new JSONObject(textdatei);
        int anzahlautomarken = json.getInt("makesCount");
        JSONArray makesarray = json.getJSONArray("makes");
        for (int i = 0; i < anzahlautomarken; i++) {
            JSONObject automarke = makesarray.getJSONObject(i);
            String automarkenname = automarke.getString("name");
            Double automarkenid = automarke.getDouble("id");
            String automarkennicename = automarke.getString("niceName");
            MyStringids Myautomarke = new MyStringids();
            Myautomarke.MyStringidMarkenID = automarkenid;
            Myautomarke.MyStringidMarkenName = automarkenname;
            Myautomarke.MyStringidMarkenNiceName = automarkennicename;
            Automarken.add(Myautomarke);
        }
        return Automarken;
    }

    public static ArrayList Automarkenmodelleauslesen(MyStringids Automarke) throws IOException //Alle Modelle zu einer Automarke
    {
        String textdatei = AlleAutos.AlleAutos();
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

    public static ArrayList Automarkenmodelljahreauslesen(MyStringids Automodell) throws IOException //Jahre zu einem Automodell
    {
        String textdatei = AlleAutos.AlleAutos();
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

    public static ArrayList Autodetailsauslesen (MyStringids Automodell) throws IOException //Details zu einem Automodell
    {
        MyStringids Myautomodelldetails = new MyStringids();
        String textdatei = Autodetails.AutoDetails(Automodell);
        ArrayList<MyStringids> Autodetails = new ArrayList<MyStringids>();
        JSONObject json = new JSONObject(textdatei);
        int anzahlautostyles = json.getInt("stylesCount");
        JSONArray stylesarray = json.getJSONArray("styles");
        for (int i = 0; i < anzahlautostyles; i++)
        {
            JSONObject autostyle = stylesarray.getJSONObject(i);
            JSONObject years = autostyle.getJSONObject("year");
            double yearid = years.getDouble("id");
            int year = years.getInt("year");

            JSONObject make = autostyle.getJSONObject("make");
            int makeid = make.getInt("id");
            String  makename = make.getString("name");
            String makenicename = make.getString("niceName");

            JSONObject model = autostyle.getJSONObject("model");
            String modelid = model.getString("id");
            String modelname = model.getString("name");
            String modelnicename = model.getString("niceName");

            JSONObject engine = autostyle.getJSONObject("engine");
            double enginecylinder = engine.getDouble("cylinder");
            double enginehorsepower = engine.getDouble("horsepower");
            double enginetorque = engine.getDouble("torque");

            JSONObject transmission = autostyle.getJSONObject("transmission");
            String transmissionType = transmission.getString("transmissionType");

            String transmissionnumberofSpeeds = transmission.getString("numberOfSpeeds");


            String drivenWheels = autostyle.getString("drivenWheels");

            double numberofdoors = autostyle.getDouble("numOfDoors");

            Myautomodelldetails.MyStringidMarkenID = makeid;
            Myautomodelldetails.MyStringidMarkenName = makename;
            Myautomodelldetails.MyStringidMarkenNiceName = makenicename;
            Myautomodelldetails.MyStringidJahrid = yearid;
            Myautomodelldetails.MyStringidJahr = year;
            Myautomodelldetails.MyStringidModellID = modelid;
            Myautomodelldetails.MyStringidModellName = modelname;
            Myautomodelldetails.MyStringidModellNiceName = modelnicename;

            Myautomodelldetails.MyStringidDrivenWheels = drivenWheels;
            Myautomodelldetails.MyStringidDoors = numberofdoors;
            Myautomodelldetails.MyStringidCylinder = enginecylinder;
            Myautomodelldetails.MyStringidHorsePower = enginehorsepower;
            Myautomodelldetails.MyStringidTorque = enginetorque;
            Myautomodelldetails.MyStringidTransmissiontype = transmissionType;
            Myautomodelldetails.MyStringidShift = transmissionnumberofSpeeds;

            Autodetails.add(Myautomodelldetails);
        }
        return Autodetails;
    }
}