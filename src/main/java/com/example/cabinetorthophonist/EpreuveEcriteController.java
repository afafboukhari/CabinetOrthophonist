package com.example.cabinetorthophonist;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class EpreuveEcriteController
{
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
    private Label ajoutertest;
    @FXML
    private Label Consultertest;

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
                Orthophonist user= OrthophonisteSessionManager.getCurrentOrthophonisteName();
                String username =user.getCompte().getEmail();
                String filepath="./src/main/Userinformation/" + username + ".ser";
                user.saveProfile(user);
                newPage = true;
                PageRouter = "login-view.fxml";
                break;

            default:
                newPage = true;
                PageRouter = "home-view.fxml";
                break;
        }
        //  PageRouter = "/com/example/tp_poo/Login.fxml";

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
    private ComboBox Sujet;
    public void initialize() {
        ArrayList<Questionnaire> questionnaires = new ArrayList<>();

        questionnaires = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getQuestionnaires();

        if (questionnaires != null) {

            // Iterate through the list and fill the ComboBox
            for (Questionnaire anamnese : questionnaires) {
                String anamneseTitle = anamnese.getTitre(); // Assuming "titre" holds the display text
                Sujet.getItems().add(anamneseTitle);
            }
        }

    }

    @FXML
    private VBox container;
    @FXML
    private Button suivant;

    public void generateTextFields(ActionEvent event)
    {
        int existingViews = container.getChildren().size() - 1;

        // Remove existing views only if necessary
        if (existingViews > 0) {
            container.getChildren().remove(0, existingViews + 1); // Remove from index 1 (inclusive) to existingViews (exclusive)
        }
        Questionnaire questionnaire;
        questionnaire = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getbyTitleQuestionnaire((String) Sujet.getSelectionModel().getSelectedItem());
        String[][] Question;
        Question = questionnaire.getQuestion();
        for (int i = 0; i < questionnaire.getCapacite(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReponseTest2.fxml")); // Load your FXML
            try {
                VBox questionView = loader.load(); // Load the custom view
                ReponseTestController2 controller = loader.getController(); // Access the custom view's controller (if needed)
                controller.getQuestion().setText("Question " + (i + 1) + " :" + Question[i][0] ); // Set question text (optional)
                controller.getChoix().setText("1-"+Question[i][0] +" 2-" + Question[i][1]+" 3-" + Question[i][2]);
                container.getChildren().add(questionView);
            } catch (IOException e) {
                e.printStackTrace(); // Handle FXML loading errors
            }
        }

    }

    public void save_answers()
    {
        try {
            Parent next = (Parent)FXMLLoader.load(this.getClass().getResource("Diagnostic.fxml"));
            Scene currentScene = this.suivant.getScene();
            currentScene.setRoot(next);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
