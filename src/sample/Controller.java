package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import java.util.ArrayList;

public class Controller
{
    @FXML public ComboBox brand;
    @FXML public ComboBox modell;
    @FXML public ComboBox year;
    @FXML public ListView CarListViewer;

    private String selectedCar;

    public void fillDropdownBrand()
    {
        /*
        brand.show();
        //Lädt alle Automarken in die ComboBox "brand"
        ArrayList<MyStringids> AlleAutomarken = new ArrayList<MyStringids>();
        String textdatei = Datenverwaltung.Textdateieinlesen("AlleAutos");
        AlleAutomarken = Autoauslesen.Automarkenauslesen(textdatei);

            for (MyStringids auto : AlleAutomarken
                    ){
                brand.getItems().addAll(
                        auto.MyStringidMarkenName
                    );
            } */
//        if(brand.getItems().size() > 0){
//
//                brand.getItems().clear();
//
//            ArrayList<MyStringids> AlleAutomarken = new ArrayList<MyStringids>();
//            AlleAutomarken = MainClass.Automarkenauslesen();
//
//            for (MyStringids auto : AlleAutomarken
//                    ) {
//                //Nur die Autos werden hinzugefügt, die dem Wert der Eingabe entsprechen.
//                if(auto.indexOf(brand.getValue().toString()) != -1){
//
//                    brand.getItems().addAll(
//                            auto
//                    );
//                }
//            }
//        }
//        else {
//            //Hier müssen dann alle verfügbaren Automarken in
//            //der ComboBox "brand" angezeigt werden.
//            ArrayList<String> AlleAutomarken = new ArrayList<String>();
//            AlleAutomarken = MainClass.Automarkenauslesen();
//
//            for (String auto : AlleAutomarken
//                 ) {
//                brand.getItems().addAll(
//                        auto
//                );
//            }
//
//
//    }

    }

    public void fillDropdownModell()
    { /*
        modell.show();

        if(brand.getValue().toString() != ""){


        //Lädt alle Automodelle in die ComboBox "modell"
        ArrayList<MyStringids> AlleAutomodelle = new ArrayList<MyStringids>();
        String textdatei = Datenverwaltung.Textdateieinlesen("AlleAutos");
        Object aktuellemarke = brand.getValue();
        AlleAutomodelle = Autoauslesen.Automarkenmodelleauslesen(textdatei,(MyStringids) aktuellemarke /*Automarke hinzufügen );
        for (MyStringids auto : AlleAutomodelle
                ){
            brand.getItems().addAll(
                    auto.MyStringidModellName
            );
        }




        } */
    }

    public void fillDropdownYear()
    {
        /*
        year.show();
        //Lädt alle Automodelljahre in die ComboBox "year"
        ArrayList<MyStringids> AlleAutomodelljahre = new ArrayList<MyStringids>();
        String textdatei = Datenverwaltung.Textdateieinlesen("AlleAutos");
        Object aktuellesmodell = modell.getItems().get(0);
        AlleAutomodelljahre = Autoauslesen.Automarkenmodelljahreauslesen(textdatei,(MyStringids) aktuellesmodell /*Automodell hinzufügen );
        for (MyStringids auto : AlleAutomodelljahre
                ){
            brand.getItems().addAll(
                   auto.MyStringidJahr
            );
        }
        */
    }

    public void addCellToCarListViewer(){

        ObservableList<String> items =FXCollections.observableArrayList (
                "Auto1", "Auto2", "Auto3", "Auto4");

        CarListViewer.setItems(items);

    }


}
