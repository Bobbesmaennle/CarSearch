package sample;

import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Controller {
    @FXML
    public ComboBox brand;
    @FXML
    public ComboBox modell;
    @FXML
    public ComboBox year;
    @FXML
    public ListView CarListViewer;
    @FXML
    public ListView CarDetails;
    @FXML
    public Label WelcomeLabel;
    @FXML
    public Label InstructionLabel;
    @FXML
    public Button AddToFavourites;

    public static ArrayList<MyStringids> BrandsDropDown = new ArrayList<MyStringids>();
    public static ArrayList<MyStringids> ModelsDropDown = new ArrayList<MyStringids>();
    public static ArrayList<MyStringids> YearsDropDown = new ArrayList<MyStringids>();
    public static ArrayList<MyStringids> Varianten = new ArrayList<MyStringids>();

    public static String selectedBrand = new String();
    public static String selectedModel = new String();
    public static String selectedYear = new String();
    public static String selectedVariant = new String();


    public static boolean brandSelected = false;
    public static boolean modelSelected = false;
    public static boolean yearSelected = false;
    public static boolean variantSelected = false;

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
        WelcomeLabel.setVisible(false);
        InstructionLabel.setVisible(false);
        CarDetails.setVisible(true);
        AddToFavourites.setVisible(true);
        Yearchanged();
    }

    public void SelectedListview() throws IOException
    {
        if (!brandSelected && !modelSelected && !yearSelected && !variantSelected) {
            selectedBrand = CarListViewer.getSelectionModel().getSelectedItem().toString();
            Brandchanged();
        } else if (!modelSelected && brandSelected && !yearSelected && !variantSelected) {
            selectedModel = CarListViewer.getSelectionModel().getSelectedItem().toString();
            Modellchanged();
        } else if(modelSelected && brandSelected && !yearSelected && !variantSelected){
            selectedYear = CarListViewer.getSelectionModel().getSelectedItem().toString();
            Yearchanged();
        }
        else if(modelSelected && brandSelected && yearSelected && variantSelected)
            {
                selectedVariant = CarListViewer.getSelectionModel().getSelectedItem().toString();
                Variantchanged();
            }
            else
                {
                    selectedVariant = CarListViewer.getSelectionModel().getSelectedItem().toString();
                    Variante();
                }
    }

    public void Variantchanged () throws IOException
    {
        if (!selectedVariant.equals(null)) {
            variantSelected = true;
            int index = CarListViewer.getItems().indexOf(selectedVariant);
            CarListViewer.getSelectionModel().select(index);
            CarListViewer.getFocusModel().focus(index);
            CarListViewer.scrollTo(index);
            CarListViewer.getSelectionModel().select(selectedVariant);
            CarListViewer.getItems().clear();
            AlleVarianten();
        } else {
            Variante();
        }
    }

    public void Modellchanged() throws IOException
    {
        if (!selectedModel.equals(null)) {
            modelSelected = true;
            yearSelected = false;
            variantSelected = false;
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
            variantSelected = false;
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
            variantSelected = false;
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
            CarDetails.getItems().clear();
            CarDetails.setDisable(true);
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
        CarDetails.getItems().clear();
        CarDetails.setDisable(true);
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
            CarDetails.getItems().clear();
            CarDetails.setDisable(true);
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
            for (MyStringids Jahr : YearsDropDown) {
                if (Jahr.MyStringidModellName.equals(Automodell.MyStringidModellName)) {
                    year.getItems().add(Jahr.MyStringidJahr);
                    CarListViewer.getItems().add(Jahr.MyStringidJahr);
                }
            }
        }
    }

    public void AlleVarianten() throws IOException
    {
        CarDetails.getItems().clear();
        CarDetails.setDisable(true);
        if (selectedVariant != null) {
            brand.setDisable(false);
            modell.setDisable(false);
            year.setDisable(false);
            CarListViewer.getItems().clear();
            MyStringids Automodell = new MyStringids();
            for (MyStringids automodell : YearsDropDown) {
                String Year = automodell.MyStringidJahr + "";
                if (Year.equals(selectedYear)) {

                    Automodell.MyStringidMarkenName = automodell.MyStringidMarkenName;
                    Automodell.MyStringidMarkenID = automodell.MyStringidMarkenID;
                    Automodell.MyStringidMarkenNiceName = automodell.MyStringidMarkenNiceName;
                    Automodell.MyStringidModellName = automodell.MyStringidModellName;
                    Automodell.MyStringidModellID = automodell.MyStringidModellID;
                    Automodell.MyStringidModellNiceName = automodell.MyStringidModellNiceName;
                    Automodell.MyStringidJahr = automodell.MyStringidJahr;
                    Automodell.MyStringidJahrid = automodell.MyStringidJahrid;
                }
            }
            Varianten = Autoauslesen.Autodetailsauslesen(Automodell);
            for (MyStringids Variante : Varianten) {
                if (Variante.MyStringidJahr == Automodell.MyStringidJahr) {
                    CarListViewer.getItems().add(Variante.MyStringidStylename);
                }
            }
        }
    }

    public void Variante()
    {
        CarDetails.getItems().clear();
        CarDetails.setDisable(false);
        MyStringids Automodell = new MyStringids();
        for (MyStringids Variante : Varianten) {
            if (Variante.MyStringidStylename.equals(selectedVariant)) {
                Automodell.MyStringidMarkenName = Variante.MyStringidMarkenName;
                Automodell.MyStringidMarkenID = Variante.MyStringidMarkenID;
                Automodell.MyStringidMarkenNiceName = Variante.MyStringidMarkenNiceName;
                Automodell.MyStringidModellName = Variante.MyStringidModellName;
                Automodell.MyStringidModellID = Variante.MyStringidModellID;
                Automodell.MyStringidModellNiceName = Variante.MyStringidModellNiceName;
                Automodell.MyStringidJahr = Variante.MyStringidJahr;
                Automodell.MyStringidJahrid = Variante.MyStringidJahrid;
                Automodell.MyStringidDrivenWheels = Variante.MyStringidDrivenWheels;
                Automodell.MyStringidDoors = Variante.MyStringidDoors;
                Automodell.MyStringidCylinder = Variante.MyStringidCylinder;
                Automodell.MyStringidHorsePower = Variante.MyStringidHorsePower;
                Automodell.MyStringidTorque = Variante.MyStringidTorque;
                Automodell.MyStringidShift = Variante.MyStringidShift;
                Automodell.MyStringidTransmissiontype = Variante.MyStringidTransmissiontype;
                Automodell.MyStringidStyleid = Variante.MyStringidStyleid;
                Automodell.MyStringidStylename = Variante.MyStringidStylename;
            }
        }

        CarDetails.getItems().add("Variante: " + Automodell.MyStringidStylename);
        CarDetails.getItems().add("Türen: " + Automodell.MyStringidDoors);
        CarDetails.getItems().add("PS: " + Automodell.MyStringidHorsePower);
        CarDetails.getItems().add("Drehmoment: " + Automodell.MyStringidTorque);
        CarDetails.getItems().add("Zylinderanzahl: " + Automodell.MyStringidCylinder);
        CarDetails.getItems().add("Antrieb: " + Automodell.MyStringidDrivenWheels);
        CarDetails.getItems().add("Gangschaltung: " + Automodell.MyStringidShift);
        CarDetails.getItems().add("Getriebe: " + Automodell.MyStringidTransmissiontype);
    }
}

