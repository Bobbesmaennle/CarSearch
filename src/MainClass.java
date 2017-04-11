import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import com.google.gson.*;

public class MainClass {
    static String source = new String("C:\\Users\\buergi\\DHBW\\Auto-Marken.txt");
    public static JComboBox cbmodel = new JComboBox();
    public static JComboBox cbyear = new JComboBox();
    public static JComboBox cbbrand = new JComboBox();
    public static Button btnsuchen = new Button();
   /* public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ui.fxml"));
        primaryStage.setTitle("WeatherFX");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }*/



    private static void Textdateieinlesen(String dateiname){

        BufferedReader br = null;
        brandsadd("Hiii Timmie,");
        modeladd("du süßer");
        yearadd("Boy!");
        try {
            br = new BufferedReader(new FileReader(new File(dateiname)));
            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line);
                String[] parts = line.split(",");
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void brandsadd (String carbrand) //Marke zur Combobox für Auto-Marken hinzufügen
    {
        cbbrand.addItem(carbrand);
    }

    public static void modeladd (String carmodel) //Model zur Combobox für Auto-Modelle hinzufügen
    {
        cbmodel.addItem(carmodel);
    }

    public static void yearadd (String caryear) //Baujhar zur Combobox für Baujahre hinzufügen
    {
        cbyear.addItem(caryear);
    }

    public static void suchenclick ()
    {
        String carbrand = cbbrand.getSelectedItem().toString();
        String carmodel = cbmodel.getSelectedItem().toString();
        String caryear = cbyear.getSelectedItem().toString();
        System.out.println(carbrand + " " + carmodel + " " + caryear);
    }

    public static void main(String[] args) throws  Exception {

        Textdateieinlesen(source);
        suchenclick();



        /*btnsuchen.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event)
            {
                suchenclick();
            }
        });*/

            /*URL edmunds = new URL("https://api.edmunds.com/api/vehicle/v2/makes?state=used&year=2014&view=basic&fmt=json&callback=string&api_key=c95hzyxj92wzfjegtsj2376p");
            URLConnection yc = edmunds.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();*/

    }

        /*private static String readAll(Reader rd) throws IOException {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            return sb.toString();
        }

        public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
            InputStream is = new URL(url).openStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String jsonText = readAll(rd);
                JSONObject json = new JSONObject(jsonText);
                return json;
            } finally {
                is.close();
            }
        }*/

}