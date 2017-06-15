package sample;

import javafx.fxml.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import java.io.IOException;
import java.util.ArrayList;
public class Controller {
    @FXML
    public ComboBox brand;
    @FXML
    public ComboBox modell;
    @FXML
    public ComboBox year;
    @FXML
    public ListView CarListViewer;

    public static ArrayList<MyStringids> BrandsDropDown = new ArrayList<MyStringids>();
    public static ArrayList<MyStringids> ModelsDropDown = new ArrayList<MyStringids>();
    public static ArrayList<MyStringids> YearsDropDown = new ArrayList<MyStringids>();
    public static ArrayList<MyStringids> Varianten = new ArrayList<MyStringids>();

    public static String selectedBrand = new String();
    public static String selectedModel = new String();
    public static String selectedYear = new String();

    public static boolean brandSelected = false;
    public static boolean modelSelected = false;
    public static boolean yearSelected = false;
    public static boolean searchingModel = false;
    public static boolean searchingYear = false;


    @FXML
    public void initialize() throws IOException
    {
        AlleMarkeninBrand();
    }

    public void Brandselectedbrandsdropdown() throws IOException
    {
        selectedBrand = brand.getValue().toString();
        Brandchanged();
    }

    public void Modellselectedmodelldropdown() throws IOException
    {
        selectedModel = modell.getValue().toString();
        Modellchanged();
    }

    public void Yearselecteyearsdropdown() throws IOException
    {
        selectedYear = year.getValue().toString();
        Yearchanged();
    }

    public void SelectedListview() throws IOException
    {
        if (!brandSelected && !modelSelected) {
            selectedBrand = CarListViewer.getSelectionModel().getSelectedItem().toString();
            Brandchanged();
        } else if (!modelSelected && brandSelected) {
            selectedModel = CarListViewer.getSelectionModel().getSelectedItem().toString();
            Modellchanged();
        } else {
            selectedYear = CarListViewer.getSelectionModel().getSelectedItem().toString();
            Yearchanged();
        }
    }

    public void Modellchanged() throws IOException
    {
        if (!selectedModel.equals(null)) {
            modelSelected = true;
            yearSelected = false;
            int index = CarListViewer.getItems().indexOf(selectedModel);
            CarListViewer.getSelectionModel().select(index);
            CarListViewer.getFocusModel().focus(index);
            CarListViewer.scrollTo(index);
            CarListViewer.getSelectionModel().select(selectedModel);
            CarListViewer.getItems().clear();
            modell.setValue(selectedModel);
            //modell.getItems().clear();
            year.getItems().clear();
            year.setDisable(false);
            AlleJahreinYear();
            System.out.println();
        } else {
            modelSelected = false;
            yearSelected = false;
            AlleModelleinModell();
        }
    }

    public void Yearchanged() throws IOException
    {

        if (!selectedYear.equals(null)) {
            yearSelected = true;
            int index = CarListViewer.getItems().indexOf(selectedYear);
            CarListViewer.getSelectionModel().select(index);
            CarListViewer.getFocusModel().focus(index);
            CarListViewer.scrollTo(index);
            CarListViewer.getSelectionModel().select(selectedYear);
            CarListViewer.getItems().clear();
            year.setValue(selectedYear);
            AlleVarianten();
        } else {
            yearSelected = false;
            AlleJahreinYear();
        }
    }

    public void Brandchanged() throws IOException
    {
        if (!selectedBrand.equals(null)) {
            brandSelected = true;
            modelSelected = false;
            yearSelected = false;
            int index = CarListViewer.getItems().indexOf(selectedBrand);
            CarListViewer.getSelectionModel().select(index);
            CarListViewer.getFocusModel().focus(index);
            CarListViewer.scrollTo(index);
            CarListViewer.getSelectionModel().select(selectedBrand);
            CarListViewer.getItems().clear();
            brand.setValue(selectedBrand);
            modell.getItems().clear();
            year.getItems().clear();
            modell.setDisable(false);
            year.setDisable(true);
            AlleModelleinModell();
        } else {
            brandSelected = false;
            modelSelected = false;
            yearSelected = false;
            AlleMarkeninBrand();
        }
    }

    public void AlleModelleinModell() throws IOException
    {
        if (selectedBrand != null) {
            brand.setDisable(false);
            modell.setDisable(false);
            year.setDisable(true);
            modell.getItems().clear();
            MyStringids Automarke = new MyStringids();
            for (MyStringids automarke : BrandsDropDown) {
                if (automarke.MyStringidMarkenName == selectedBrand) {
                    Automarke.MyStringidMarkenName = automarke.MyStringidMarkenName;
                    Automarke.MyStringidMarkenID = automarke.MyStringidMarkenID;
                    Automarke.MyStringidMarkenNiceName = automarke.MyStringidMarkenNiceName;
                }
            }
            ModelsDropDown = Autoauslesen.Automarkenmodelleauslesen(Automarke);
            for (MyStringids model : ModelsDropDown) {
                if (model.MyStringidMarkenName.equals(Automarke.MyStringidMarkenName)) {
                    modell.getItems().add(model.MyStringidModellName);
                    CarListViewer.getItems().add(model.MyStringidModellName);
                }
            }
        }
    }

    public void AlleMarkeninBrand() throws IOException
    {
        CarListViewer.getItems().clear();
        brand.setDisable(false);
        year.setDisable(true);
        modell.setDisable(true);
        brand.getItems().clear();
        modell.getItems().clear();
        year.getItems().clear();
        BrandsDropDown = Autoauslesen.Automarkenauslesen();
        for (MyStringids auto : BrandsDropDown) {
            brand.getItems().add(auto.MyStringidMarkenName);
            CarListViewer.getItems().add(auto.MyStringidMarkenName);
        }
    }

    public void AlleJahreinYear() throws IOException
    {
        if (selectedYear != null) {
            brand.setDisable(false);
            modell.setDisable(false);
            year.setDisable(false);
            year.getItems().clear();
            MyStringids Automodell = new MyStringids();
            for (MyStringids automodell : ModelsDropDown) {
                if (automodell.MyStringidModellName == selectedModel) {
                    Automodell.MyStringidMarkenName = automodell.MyStringidMarkenName;
                    Automodell.MyStringidMarkenID = automodell.MyStringidMarkenID;
                    Automodell.MyStringidMarkenNiceName = automodell.MyStringidMarkenNiceName;
                    Automodell.MyStringidModellName = automodell.MyStringidModellName;
                    Automodell.MyStringidModellID = automodell.MyStringidModellID;
                    Automodell.MyStringidModellNiceName = automodell.MyStringidModellNiceName;
                }
            }
            YearsDropDown = Autoauslesen.Automarkenmodelljahreauslesen(Automodell);
            System.out.println(year.getItems());
            for (MyStringids Jahr : YearsDropDown) {
                if (Jahr.MyStringidModellName.equals(Automodell.MyStringidModellName)) {
                    year.getItems().add(Jahr.MyStringidJahr);
                    CarListViewer.getItems().add(Jahr.MyStringidJahr);
                }
            }
            System.out.println(year.getItems());
        }
    }

    public void AlleVarianten() throws IOException
    {
        MyStringids Auto = new MyStringids();
        for (MyStringids auto : YearsDropDown) {
            Auto.MyStringidMarkenName = auto.MyStringidMarkenName;
            Auto.MyStringidMarkenID = auto.MyStringidMarkenID;
            Auto.MyStringidMarkenNiceName = auto.MyStringidMarkenNiceName;
            Auto.MyStringidModellName = auto.MyStringidModellName;
            Auto.MyStringidModellNiceName = auto.MyStringidModellNiceName;
            Auto.MyStringidModellID = auto.MyStringidModellID;
            Auto.MyStringidJahr = auto.MyStringidJahr;
            Auto.MyStringidJahrid = auto.MyStringidJahrid;
        }
        Varianten = Autoauslesen.Autodetailsauslesen(Auto);
        for (MyStringids Variante : Varianten) {
            ListView Anzeige = new ListView();
            Anzeige.getItems().add("Variante: " + Variante.MyStringidStylename);
            Anzeige.getItems().add("Türen: " + Variante.MyStringidDoors);
            Anzeige.getItems().add("PS: " + Variante.MyStringidHorsePower);
            Anzeige.getItems().add("Drehmoment: " + Variante.MyStringidTorque);
            Anzeige.getItems().add("Zylinderanzahl: " + Variante.MyStringidCylinder);
            Anzeige.getItems().add("Antrieb: " + Variante.MyStringidDrivenWheels);
            Anzeige.getItems().add("Gangschaltung: " + Variante.MyStringidShift);
            Anzeige.getItems().add("Getriebe: " + Variante.MyStringidTransmissiontype);
            System.out.println(Anzeige);
        }
    }
}

