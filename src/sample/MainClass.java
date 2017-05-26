package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class MainClass extends Application
{
    public static void main(String[] args) throws Exception
    {
        Datenverwaltung.Textdateivorhanden("AlleAutos", "https://api.edmunds.com/api/vehicle/v2/makes?state=used&year=2014&view=basic&fmt=json&callback=string&api_key=c95hzyxj92wzfjegtsj2376p");

        System.out.println();//Autoauslesen.Autodetails(auto);
        String textdatei = Datenverwaltung.Textdateieinlesen("ILX");
        ArrayList<MyStringids> autodetails = new ArrayList<MyStringids>();
        autodetails = Autoauslesen.Autodetailsauslesen(textdatei);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("CarSearcher");
        primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.setMaximized(true);
        primaryStage.setHeight(700);
        primaryStage.setWidth(850);
        primaryStage.setResizable(false);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}