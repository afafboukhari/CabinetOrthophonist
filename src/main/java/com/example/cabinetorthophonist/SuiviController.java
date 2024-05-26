package com.example.cabinetorthophonist;

import Model.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class SuiviController  {

    @FXML
    private Label Agenda;
    @FXML
    private Label Patients;
    @FXML
    private Label BO;
    @FXML
    private Label FicheSuivi;
    @FXML
    private Label Testes;
    @FXML
    private Label Profile;
    @FXML
    private Label deconnecter;

    @FXML // fx:id="duree"
    private TextField duree; // Value injected by FXMLLoader

    @FXML // fx:id="dureerror"
    private Label dureerror; // Value injected by FXMLLoader

    @FXML // fx:id="enregistrer"
    private Button enregistrer; // Value injected by FXMLLoader

    @FXML // fx:id="heure_consult"
    private TextField heure_consult; // Value injected by FXMLLoader

    @FXML // fx:id="heureerror"
    private Label heureerror; // Value injected by FXMLLoader

    @FXML // fx:id="jour_consult"
    private DatePicker jour; // Value injected by FXMLLoader

    @FXML // fx:id="jourrror"
    private Label jourrror; // Value injected by FXMLLoader

    @FXML // fx:id="num_dossier"
    private TextField num_dossier; // Value injected by FXMLLoader

    @FXML // fx:id="numeroerror"
    private Label numeroerror; // Value injected by FXMLLoader

    @FXML // fx:id="profile"
    private Button profile; // Value injected by FXMLLoader

    @FXML // fx:id="retour"
    private Button retour; // Value injected by FXMLLoader

    @FXML // fx:id="type_deroulement"
    private ComboBox<String> type_deroulement;// Value injected by FXMLLoader

    private Label typeeerror;

    @FXML // fx:id="utilisateur"
    private Label utilisateur; // Value injected by FXMLLoader

    @FXML
    private Label typeerror;



    @FXML
    public void handleRouting(MouseEvent event) {

        Label label = (Label) event.getSource();
        String labelText = label.getText();


        String PageRouter = "home-view.fxml"; // Chemin par défaut
        boolean newPage = false;

        switch (labelText) {
            case "Patients":
                newPage = true;
                PageRouter = "Patients.fxml";
                break;

            case "Agenda":
                newPage = true;
                PageRouter = "Agenda.fxml";
                break;

            case "BO":
                newPage = true;
                PageRouter = "BO.fxml";
                break;

            case "Fiche de suivi":
                newPage = true;
                PageRouter = "FicheDeSuivi.fxml";
                break;

            case "Testes":
                newPage = true;
                PageRouter = "Testes.fxml";
                break;

            case "Votre profile":
                newPage = true;
                PageRouter = "Profile.fxml"; // Chemin vers la page de profil
                break;

            case "Se déconnecter":
//                Orthophonist user= OrthophonisteSessionManager.getCurrentOrthophonisteName();
//                String username =user.getEmail();
//                String filepath="./src/main/Userinformation/" + username + ".ser";
//                Orthophonist.serialize(filepath,user);
//                newPage = true;
//                PageRouter = "Login.fxml";
                break;

            default:
                newPage = true;
                PageRouter = "home-view.fxml";
                break;
        }
        //  PageRouter = "Login.fxml";

        if (newPage) {
            try {
                // Load the desired page
                Parent nextPage = FXMLLoader.load(getClass().getResource(PageRouter));

                Stage Scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
                javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
                Scene.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    boolean chercher_dossier(KeyEvent event)
    {
        numeroerror.setText("");
        String num = num_dossier.getText();
        boolean existe = false;
        int numero=0;
        if (num.isEmpty()) {
            numeroerror.setText("Le champ nom ne doit pas être vide.");

        }else
        {
            try {numero= Integer.parseInt(num);
                Dossier dossier = OrthophonisteSessionManager.getCurrentOrthophonisteName().rechercher_patient(numero);
                if(dossier != null) {
                    existe = true;
                }else {
                    numeroerror.setText("Le numéro de dossier n'existe pas!.");
                }

            } catch (NumberFormatException e) {
                numeroerror.setText("Le numéro de dossier doit être un nombre valide.");
            }
        }
        return existe;
    }


    @FXML
    void enregistrer(ActionEvent event)
    {
        resetErrorMessages();

        String heureText = heure_consult.getText();
        LocalDate date = jour.getValue();
        String dureeText = duree.getText();
        String num = num_dossier.getText();
        String selectedValue = type_deroulement.getValue();
        Deroulement_seance type;
        // Convert the selected value to the enum type
        System.out.println("Date sélectionnée : " + date);
        Orthophonist user = OrthophonisteSessionManager.getCurrentOrthophonisteName();

        boolean allFieldsValid = true;

        // Vérifier que le champ nom n'est pas vide
        int numero=0;
        Dossier dossier = null;
        if (num.isEmpty())
        {
            numeroerror.setText("Le champ nom ne doit pas être vide.");
            allFieldsValid = false;

        }else
        {
            try {
                numero= Integer.parseInt(num);
                dossier = OrthophonisteSessionManager.getCurrentOrthophonisteName().rechercher_patient(numero);

                if(dossier == null) {
                    numeroerror.setText("Le numéro de dossier n'existe pas!.");
                    allFieldsValid = false;

                }

            } catch (NumberFormatException e) {
                numeroerror.setText("Le numéro de dossier doit être un nombre valide.");
                allFieldsValid = false;
            }

        }

        // Vérifier que la date est fournie
        if (date == null) {
            jourrror.setText("La date ne doit pas être vide.");
            allFieldsValid = false;
        }else {
            if (date.isBefore(LocalDate.now())) {
                // La date est antérieure à aujourd'hui, afficher un message d'erreur
                jourrror.setText("La date est antérieure à aujourd'hui.");
                allFieldsValid = false;
            }

        }


        // Vérifier que l'heure de consultation est fournie
        if (heureText.isEmpty()) {
            heureerror.setText("L'heure  ne doit pas être vide.");
            allFieldsValid = false;
        }else {
            try {
                LocalTime.parse(heureText); // Essayer de parser l'heure

            } catch (Exception e) {
                heureerror.setText("Veuillez entrer une heure valide (HH:mm)");
                allFieldsValid = false;
            }
        }

        if (selectedValue == null || !(selectedValue.equals("en ligne") || selectedValue.equals("en présentiel"))) {
            typeerror.setText("Choisissez le type de la séance");
            allFieldsValid = false;}

        if (allFieldsValid) {

            if (selectedValue.equals("en ligne") )
            {
                type = Deroulement_seance.EN_LIGNE;
            }else
            {
                type = Deroulement_seance.EN_LIGNE;
            }
            System.out.println(dureeText);
            dossier = OrthophonisteSessionManager.getCurrentOrthophonisteName().rechercher_patient(numero);
            Suivi suivi = new Suivi(date, LocalTime.parse(heureText), Type_rendez_vous.SUIVI, numero, type, dureeText);
            user.add_rendez_vous_patient(dossier.getNumero(),suivi);
            //user.getAgenda().add_rendez_vous(suivi);
            String PageRouter = "Agenda.fxml";
            try {

                Parent nextPage = FXMLLoader.load(getClass().getResource(PageRouter));
                Stage Scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(nextPage, 1000, 670);
                Scene.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }}

        @FXML
        void retour(ActionEvent event) {
        try {
            String PageRouter = "Agenda.fxml";
            // Load the desired page
            Parent nextPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(PageRouter)));
            Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
            javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
            Scene.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        String nom1 =OrthophonisteSessionManager.getCurrentOrthophonisteName().getNom();
        System.out.println(nom1);
        String prenom1 =OrthophonisteSessionManager.getCurrentOrthophonisteName().getPrenom();

        utilisateur.setText(nom1 + " " + prenom1);

        ObservableList<String> options = FXCollections.observableArrayList(
                "en ligne",
                "en présentiel"
        );
        type_deroulement.getItems().clear();

        // Set the items for the ComboBox
        type_deroulement.setItems(options);
        // Set the items for the ComboBox
        duree.setText("1:00");
        String nom = OrthophonisteSessionManager.getCurrentOrthophonisteName().getNom();
        String prenom =OrthophonisteSessionManager.getCurrentOrthophonisteName().getPrenom();
        utilisateur.setText(nom + " " + prenom);
        boolean add = type_deroulement.getItems().add(String.valueOf(options));

    }

    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void resetErrorMessages() {
        jourrror.setText("");
        numeroerror.setText("");
        heureerror.setText("");
        dureerror.setText("");
        typeerror.setText("");
    }
    private void afficherMessageSucces(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
