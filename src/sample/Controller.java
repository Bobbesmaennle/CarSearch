package sample;

import javafx.fxml.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import java.io.IOException;
import java.util.ArrayList;
public class Controller
{
    @FXML public ComboBox brand;
    @FXML public ComboBox modell;
    @FXML public ComboBox year;
    @FXML public ListView CarListViewer;

    public static ArrayList<MyStringids> BrandsDropDown = new ArrayList<MyStringids>();
    public static ArrayList<MyStringids> ModelsDropDown = new ArrayList<MyStringids>();
    public static ArrayList<MyStringids> YearsDropDown = new ArrayList<MyStringids>();


    @FXML
    public void initialize() throws IOException
    {
        fillDropdownBrand();
    }


    public void fillDropdownBrand() throws IOException //Lädt alle Automarken in die ComboBox "brand"
    {
        CarListViewer.getItems().clear();
        brand.setDisable(false);
        year.setDisable(true);
        modell.setDisable(true);
        brand.getItems().clear();
        BrandsDropDown = Autoauslesen.Automarkenauslesen();
        for (MyStringids auto : BrandsDropDown)
            {
                brand.getItems().add(auto.MyStringidMarkenName);
                CarListViewer.getItems().add(auto.MyStringidMarkenName);
            }
        brand.show();
    }

    public void fillDropdownModell() throws IOException //Lädt alle Automodelle in die ComboBox "modell"
    {
        Object aktuelles = brand.getValue();
        if(aktuelles != null)
        {
            CarListViewer.getItems().clear();
            brand.setDisable(false);
            year.setDisable(true);
            modell.setDisable(false);
            modell.getItems().clear();
            int index = brand.getItems().indexOf(aktuelles);
            MyStringids Automarke = BrandsDropDown.get(index);
            ModelsDropDown = Autoauslesen.Automarkenmodelleauslesen(Automarke);
            for (MyStringids model : ModelsDropDown)
            {
                modell.getItems().add(model.MyStringidModellName);
                CarListViewer.getItems().add(model.MyStringidModellName);
            }
            modell.show();
        }
        else
            {
                aktuelles = CarListViewer.getSelectionModel().getSelectedItem();
                if(aktuelles != null)
                {
                    brand.setValue(aktuelles);
                }
            }
    }

    public void fillDropdownYear() throws IOException
    {
        Object aktuelles = modell.getValue();
        if(aktuelles != null)
        {
            CarListViewer.getItems().clear();
            brand.setDisable(false);
            year.setDisable(false);
            modell.setDisable(false);
            year.getItems().clear();
            int index = modell.getItems().indexOf(aktuelles);
            MyStringids Automodell = ModelsDropDown.get(index);
            YearsDropDown = Autoauslesen.Automarkenmodelljahreauslesen(Automodell);
            for (MyStringids jahr:YearsDropDown)
            {
                year.getItems().add(jahr.MyStringidJahr);
                CarListViewer.getItems().add(jahr.MyStringidJahr);
            }
            year.show();
        }
        else
            {
                aktuelles = CarListViewer.getSelectionModel().getSelectedItem();
                if(aktuelles != null)
                {
                    modell.setValue(aktuelles);
                }
            }
    }

    public void addCellToCarListViewer() throws IOException
    {
//        ObservableList<String> items =FXCollections.observableArrayList (
//                "Auto1", "Auto2", "Auto3", "Auto4");
//
//        CarListViewer.setItems(items);

    }


}
