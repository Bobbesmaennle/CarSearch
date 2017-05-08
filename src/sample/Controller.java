/**
 * Created by buergi on 03.05.2017.
 */
package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Observable;

public class Controller
{
    @FXML public ComboBox brand;
    @FXML public ComboBox modell;
    @FXML public ComboBox year;
    @FXML public ListView CarListViewer;

    public void fillDropdownBrand(){

        brand.show();

        ArrayList<MyStringids> AlleAutomarken = new ArrayList<MyStringids>();
            AlleAutomarken = MainClass.Automarkenauslesen();

            for (MyStringids auto : AlleAutomarken
                    ){
                brand.getItems().addAll(
                        auto.MyStringidMarkenName
                    );
            }




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

    public void fillDropdownModell(){



        //MainClass.modeladd(modell.getValue().toString());

    }

    public void fillDropdownYear(){


        //MainClass.yearadd(year.getValue().toString());

    }

    public void addCellToCarListViewer(){

        ObservableList<String> items =FXCollections.observableArrayList (
                "Auto1", "Auto2", "Auto3", "Auto4");

        CarListViewer.setItems(items);




    }
}
