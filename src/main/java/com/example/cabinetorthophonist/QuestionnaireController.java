package com.example.cabinetorthophonist;

import Model.AnamneseAdulte;
import Model.Questionnaire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import java.io.IOException;

public class QuestionnaireController
{
    static boolean ajouter;
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
//                Orthophonist user= OrthophonisteSessionManager.getCurrentOrthophonisteName();
//                String username =user.getCompte().getEmail();
//                String filepath="./src/main/Userinformation/" + username + ".ser";
//                Orthophonist.serialize(filepath,user);
//                newPage = true;
//                PageRouter = "/com/example/tp_poo/Login.fxml";
                break;

            default:
                newPage = true;
                PageRouter = "home-view.fxml";
                break;
        }

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
    private TextField titre;
    @FXML
    private VBox container;
    @FXML
    private TextField capacityTextField;
    @FXML
    private Button generer;

    @FXML
    public void generateTextFields(ActionEvent event) {
        int existingViews = container.getChildren().size() - 1;

        // Remove existing views only if necessary
        if (existingViews > 0) {
            container.getChildren().remove(0, existingViews + 1); // Remove from index 1 (inclusive) to existingViews (exclusive)
        }
        int capacity = Integer.parseInt(capacityTextField.getText());

        for (int i = 0; i < capacity; i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("QCM_QCU.fxml")); // Load your FXML
            try {
                VBox questionView = loader.load(); // Load the custom view
                QuestionController controller = loader.getController(); // Access the custom view's controller (if needed)
                controller.getQuestionLabel().setText("Question " + (i + 1) + " :"); // Set question text (optional)
                container.getChildren().add(questionView);
            } catch (IOException e) {
                e.printStackTrace(); // Handle FXML loading errors
            }
        }
    }

    @FXML
    private Button enregistrer;

    @FXML
    public void create() {
        String[][] tab;
        int capacity = Integer.parseInt(capacityTextField.getText());

        // Allocate space for the 2D array based on capacity (adjust columns for options)
        tab = new String[capacity][4]; // Adjust number of columns based on your number of choices

        // Iterate through the dynamically generated custom views
        for (int i = 0; i < capacity; i++) {
            VBox questionView = (VBox) container.getChildren().get(i); // Assuming first element is not a view

            // Access elements within the custom view (assuming IDs)
            Label questionLabel = (Label) questionView.getChildren().get(0);
            TextField questionTextField = (TextField) questionView.getChildren().get(1);
            HBox choicesBox = (HBox) questionView.getChildren().get(2); // Assuming choices are in the third child

            String question = questionTextField.getText();

            // Store question in the first column of the array
            tab[i][0] = question;

            // Iterate through choice TextFields within the HBox
            for (int j = 1; j < choicesBox.getChildren().size(); j++) { // Start from index 1 (assuming "Choix" label is the first child)
                TextField choiceField = (TextField) choicesBox.getChildren().get(j);
                String choice = choiceField.getText().trim(); // Trim leading/trailing whitespace

                // Store only non-empty choices (optional, adjust as needed)
                if (!choice.isEmpty()) {
                    tab[i][j] = choice;
                }
            }
        }


        Questionnaire test = new Questionnaire(titre.getText(),tab,capacity);

        try {
            Parent next = (Parent)FXMLLoader.load(this.getClass().getResource("Testes.fxml"));
            Scene currentScene = this.enregistrer.getScene();
            currentScene.setRoot(next);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
