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

    public static String selectedBrand = "";
    public static String selectedModel = "";
    public static String selectedYear = "";

    public static boolean brandSelected = false;
    public static boolean modelSelected = false;
    public static boolean searchingModel = false;
    public static boolean searchingYear = false;

    public static Object chosenModelObj;
    @FXML
    public void initialize() throws IOException
    {
        //fillDropdownBrand();
    }

    public void fillDropdownBrand() throws IOException //Lädt alle Automarken in die ComboBox "brand"
    {

        //System.out.println("Hier der Inhalt: " + brand.getSelectionModel().getSelectedItem().toString());
        if (brandSelected == false) {
            CarListViewer.getItems().clear();
            brand.setDisable(false);
            year.setDisable(true);
            modell.setDisable(true);
            brand.getItems().clear();
            BrandsDropDown = Autoauslesen.Automarkenauslesen();


            if (brand.getValue() == null) {

                for (MyStringids auto : BrandsDropDown) {
                    brand.getItems().add(auto.MyStringidMarkenName);
                    CarListViewer.getItems().add(auto.MyStringidMarkenName);
                }

            } else {

                for (MyStringids auto : BrandsDropDown) {
                    String autoLowerCase = auto.MyStringidMarkenName.toLowerCase();
                    if (autoLowerCase.indexOf(brand.getValue().toString()) != -1) {
                        brand.getItems().add(auto.MyStringidMarkenName);
                        CarListViewer.getItems().add(auto.MyStringidMarkenName);
                    }
                }
            }
        }
        else {

            brand.setValue(selectedBrand);
        }
        //brand.show();
    }

    public void fillDropdownModell() throws IOException //Lädt alle Automodelle in die ComboBox "modell"
    {
        searchingModel = true;

        //brand.setValue(CarListViewer.getSelectionModel().getSelectedItem());
        //Object aktuelles = brand.getValue();
        Object aktuelles = CarListViewer.getSelectionModel().getSelectedItem();

        //Object aktuelles = brand.getSelectionModel().getSelectedItem();

        if(aktuelles != null)
        {
            CarListViewer.getItems().clear();
            brand.setDisable(false);
            modell.setDisable(false);
            year.setDisable(true);
            modell.getItems().clear();
            int index = brand.getItems().indexOf(aktuelles);
            MyStringids Automarke = BrandsDropDown.get(index);
            ModelsDropDown = Autoauslesen.Automarkenmodelleauslesen(Automarke);
            for (MyStringids model : ModelsDropDown)
            {
                modell.getItems().add(model.MyStringidModellName);
                CarListViewer.getItems().add(model.MyStringidModellName);
            }
           //modell.show();
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
        searchingYear = true;

        //Object aktuelles = modell.getValue();
        Object aktuelles = CarListViewer.getSelectionModel().getSelectedItem();
        //Object aktuelles = modell.getSelectionModel().getSelectedItem();

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
            //year.show();
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


    public void listViewSelection() throws IOException
    {
        //System.out.println(CarListViewer.getSelectionModel().getSelectedItem().toString());

        if(searchingModel == false && searchingYear == false)
        {
//Marke schreiben.
            brandSelected = true;
            selectedBrand = CarListViewer.getSelectionModel().getSelectedItem().toString();
            brand.setValue(CarListViewer.getSelectionModel().getSelectedItem());

//            selectedBrand = brand.getSelectionModel().getSelectedItem().toString();
//            brand.setValue(brand.getSelectionModel().getSelectedItem());

                    fillDropdownModell();

        }
        else if (searchingModel == true && searchingYear == false){
//Modell schreiben.

            modelSelected = true;
            selectedModel = CarListViewer.getSelectionModel().getSelectedItem().toString();
            chosenModelObj = CarListViewer.getSelectionModel().getSelectedItem();
            modell.setValue(CarListViewer.getSelectionModel().getSelectedItem());

            selectedModel = modell.getSelectionModel().getSelectedItem().toString();
            modell.setValue(modell.getSelectionModel().getSelectedItem());

            fillDropdownYear();

        }
        else if (searchingYear == true) {
//Jahr schreiben.

            selectedYear = CarListViewer.getSelectionModel().getSelectedItem().toString();
            year.setValue(CarListViewer.getSelectionModel().getSelectedItem());

            // Suchergebnisse anzeigen!!!







          //  Autodetails.AutoDetails();
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
