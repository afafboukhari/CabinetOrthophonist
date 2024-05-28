package com.example.cabinetorthophonist;

import Model.*;
import javafx.application.Application;
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

public class ConsultationController {

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

    @FXML
    private TextField Age;

    @FXML
    private Label ageerror;

    @FXML
    private Label utilisateur1;

    @FXML
    private TextField duree;

    @FXML
    private Label dureerror;

    @FXML
    private Button enregistrer;

    @FXML
    private TextField heure;

    @FXML
    private Label houreerror11;

    @FXML
    private DatePicker jour;

    @FXML
    private Label nameerror;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private Label prenomerror;


    @FXML
    private Label jourrror;
    @FXML
    private Button retour;




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
//                String username =user.getCompte().getEmail();
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

                Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
                javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
                Scene.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    void calcule_durée(KeyEvent event)
    {

        ageerror.setText("");

        String age = Age.getText();
        try {
            int a  = Integer.parseInt(age);
            if(a>=18){
                duree.setText("1:30");

            }else {
                duree.setText("2:30");
            }

        } catch (NumberFormatException e) {
            ageerror.setText("L'âge doit être un nombre valide.");
        }
    }


    public void enregistrer(ActionEvent event) {
        resetErrorMessages();

        // Récupérer les valeurs des champs
        String Nom = nom.getText();
        String Prenom = prenom.getText();
        String ageText = Age.getText();
        String heureText = heure.getText();
        LocalDate date = jour.getValue();
        String dureeText = duree.getText();
        System.out.println("Date sélectionnée : " + date);


        // Variable pour vérifier si toutes les vérifications passent
        boolean allFieldsValid = true;

        // Vérifier que le champ nom n'est pas vide
        if (Nom.isEmpty()) {
            nameerror.setText("Le champ nom ne doit pas être vide.");
            allFieldsValid = false;
        }

        // Vérifier que le champ prénom n'est pas vide
        if (Prenom.isEmpty()) {
            prenomerror.setText("Le champ prénom ne doit pas être vide.");
            allFieldsValid = false;
        }

        // Vérifier que l'âge est fourni et est un entier valide
        int age = 0;
        if (ageText.isEmpty()) {
            ageerror.setText("Le champ âge ne doit pas être vide.");
            allFieldsValid = false;
        } else {
            try {
                age = Integer.parseInt(ageText);
            } catch (NumberFormatException e) {
                ageerror.setText("L'âge doit être un nombre valide.");
                allFieldsValid = false;
            }
        }

        // Vérifier que la date de consultation est fournie
        if (date == null) {
            jourrror.setText("La date de consultation ne doit pas être vide.");
            allFieldsValid = false;
        } else {
            if (date.isBefore(LocalDate.now())) {
                // La date est antérieure à aujourd'hui, afficher un message d'erreur
                jourrror.setText("La date est antérieure à aujourd'hui.");
                allFieldsValid = false;
            }
        }


        if (dureeText.isEmpty()) {
            dureerror.setText("La durée de consultation ne doit pas être vide.");
            allFieldsValid = false;
        }

        // Vérifier que l'heure de consultation est fournie
        if (heureText.isEmpty()) {
            houreerror11.setText("L'heure de consultation ne doit pas être vide.");
            allFieldsValid = false;
        } else {
            try {
                LocalTime.parse(heureText); // Essayer de parser l'heure

            } catch (Exception e) {
                houreerror11.setText("Veuillez entrer une heure valide (HH:mm)");
                allFieldsValid = false;
            }
        }

        // Si toutes les vérifications passent, procéder à la création du dossier
        Dossier dossier = new Dossier();
        int num = dossier.getNumero();
        if (allFieldsValid) {
            Patient patient;
            if (age >= 18) {
                patient = new Adulte(Nom, Prenom, num);
            } else {
                patient = new Enfant(Nom, Prenom, num);
            }
            dossier.setPatient(patient);
            LocalTime time = LocalTime.parse(heureText);
            Consultation consultation = new Consultation(date, time, Type_rendez_vous.CONSULTATION, Nom, Prenom, age, dureeText);

            dossier.add_rendez_vous(consultation);
            OrthophonisteSessionManager.getCurrentOrthophonisteName().add_patient(dossier);
            Orthophonist user = OrthophonisteSessionManager.getCurrentOrthophonisteName();
            user.getAgenda().add_rendez_vous(consultation);
            afficherMessageSucces("La consultation est ajouter avec succés");


            try {

                // Load the desired page
                String PageRouter = "Agenda.fxml";
                Parent nextPage = FXMLLoader.load(getClass().getResource(PageRouter));

                // You need to set the new page in the current scene or open a new window
                // Example for setting the new page in the current scene:
                Stage Scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(nextPage, 1000, 670);
                Scene.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void resetErrorMessages() {
        nameerror.setText("");
        prenomerror.setText("");
        ageerror.setText("");
        dureerror.setText("");
        houreerror11.setText("");
        jourrror.setText("");
    }

    private void afficherMessageSucces(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    void retour(ActionEvent event)
    {
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
          String nom = OrthophonisteSessionManager.getCurrentOrthophonisteName().getNom();
          System.out.println(nom);
          String prenom =OrthophonisteSessionManager.getCurrentOrthophonisteName().getPrenom();

          utilisateur1.setText(nom + " " + prenom);

        assert Age != null : "fx:id=\"Age\" was not injected: check your FXML file 'Consultation.fxml'.";
        assert duree != null : "fx:id=\"duree\" was not injected: check your FXML file 'Consultation.fxml'.";
        assert enregistrer != null : "fx:id=\"enregistrer\" was not injected: check your FXML file 'Consultation.fxml'.";
        assert heure != null : "fx:id=\"heure_consult\" was not injected: check your FXML file 'Consultation.fxml'.";
        assert jour != null : "fx:id=\"jour_consult\" was not injected: check your FXML file 'Consultation.fxml'.";
        assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'Consultation.fxml'.";
        assert prenom != null : "fx:id=\"prenom\" was not injected: check your FXML file 'Consultation.fxml'.";
        //assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'Consultation.fxml'.";
        assert retour != null : "fx:id=\"retour\" was not injected: check your FXML file 'Consultation.fxml'.";

    }

    }






