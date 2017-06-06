package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Controller
{
    @FXML public ComboBox brand;
    @FXML public ComboBox modell;
    @FXML public ComboBox year;
    @FXML public ListView CarListViewer;


    public void fillDropdownBrand() throws IOException //Lädt alle Automarken in die ComboBox "brand"
    {
        ArrayList<MyStringids> AlleAutomarken = new ArrayList<MyStringids>();
        AlleAutomarken = Autoauslesen.Automarkenauslesen();
            for (MyStringids auto : AlleAutomarken)
            {
                brand.getItems().add(auto);
            }
        brand.show();
    }


    public void fillDropdownModell() throws IOException //Lädt alle Automodelle in die ComboBox "modell"
    {
        MyStringids marke = (MyStringids) brand.getItems().get(1);
        MyStringids automarke = new MyStringids();
        automarke.MyStringidMarkenName = marke.toString();
        modell.show();
        if(brand.getValue().toString() != "")
        {
        ArrayList<MyStringids> AlleAutomodelle = new ArrayList<MyStringids>();
        AlleAutomodelle = Autoauslesen.Automarkenmodelleauslesen(automarke);
        for (MyStringids auto : AlleAutomodelle)
        {
            brand.getItems().addAll(auto.MyStringidModellName);
        }
        }
    }

    public void fillDropdownYear() throws IOException {

        year.show();
        //Lädt alle Automodelljahre in die ComboBox "year"
        ArrayList<MyStringids> AlleAutomodelljahre = new ArrayList<MyStringids>();
        Object aktuellesmodell = modell.getItems().get(0);
        AlleAutomodelljahre = Autoauslesen.Automarkenmodelljahreauslesen((MyStringids) aktuellesmodell);
        for (MyStringids auto : AlleAutomodelljahre
                ){
            brand.getItems().addAll(
                   auto.MyStringidJahr
            );
        }

    }

    public void addCellToCarListViewer() throws IOException {

        ObservableList<String> items =FXCollections.observableArrayList (
                "Auto1", "Auto2", "Auto3", "Auto4");

        CarListViewer.setItems(items);

    }


}
