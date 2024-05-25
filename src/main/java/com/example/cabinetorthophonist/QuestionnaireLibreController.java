package com.example.cabinetorthophonist;

import Model.OrthophonisteSessionManager;
import Model.Questionnaire;
import Model.QuestionnaireLibre;
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

import java.io.IOException;

public class QuestionnaireLibreController
{
    static boolean ajouter;
    static String titrestatic;
    static String[] tab1;
    static int size;
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

                Stage Scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

    public void initialize()
    {
        if(!ajouter) {
            int existingViews = container.getChildren().size() - 1;

            // Remove existing views only if necessary
            if (existingViews > 0) {
                container.getChildren().remove(0, existingViews + 1); // Remove from index 1 (inclusive) to existingViews (exclusive)
            }
            titre.setText(titrestatic);
            for (int i = 0; i < size; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ReponseLibre.fxml")); // Load your FXML
                try {
                    VBox questionView = loader.load(); // Load the custom view
                    RepLibreController controller = loader.getController(); // Access the custom view's controller (if needed)
                    controller.getLabel().setText("Question " + (i + 1) + " :"); // Set question text (optional)
                    controller.getQuestion().setText(tab1[i]);
                    container.getChildren().add(questionView);
                } catch (IOException e) {
                    e.printStackTrace(); // Handle FXML loading errors
                }
            }
        }
    }

    public void generateTextFields(ActionEvent event) {
        int existingViews = container.getChildren().size() - 1;

        // Remove existing views only if necessary
        if (existingViews > 0) {
            container.getChildren().remove(0, existingViews + 1); // Remove from index 1 (inclusive) to existingViews (exclusive)
        }
        int capacity = Integer.parseInt(capacityTextField.getText());

        for (int i = 0; i < capacity; i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReponseLibre.fxml")); // Load your FXML
            try {
                VBox questionView = loader.load(); // Load the custom view
                RepLibreController controller = loader.getController(); // Access the custom view's controller (if needed)
                controller.getLabel().setText("Question " + (i + 1) + " :"); // Set question text (optional)
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
        if(ajouter)
        {
            int capacity = Integer.parseInt(capacityTextField.getText());
            // Allocate space for the 2D array based on capacity (adjust columns for options)
            String[] tab = new String[capacity]; // Adjust number of columns based on your number of choices
            // Iterate through the dynamically generated custom views
            for (int i = 0; i < capacity; i++)
            {
                VBox questionView = (VBox) container.getChildren().get(i); // Assuming first element is not a view

                // Access elements within the custom view (assuming IDs)
                Label questionLabel = (Label) questionView.getChildren().get(0);
                TextField questionTextField = (TextField) questionView.getChildren().get(1);

                String question = questionTextField.getText();

                // Store question in the first column of the array
                System.out.println(question);
                tab[i] = question;
            }
            QuestionnaireLibre test = new QuestionnaireLibre(titre.getText(),tab,capacity);
            OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getQuestionnaireLibre().add(test);


        }else{
            // Allocate space for the 2D array based on capacity (adjust columns for options)
            String[] tab = new String[size]; // Adjust number of columns based on your number of choices
            // Iterate through the dynamically generated custom views
            for (int i = 0; i < size; i++)
            {
                VBox questionView = (VBox) container.getChildren().get(i); // Assuming first element is not a view

                // Access elements within the custom view (assuming IDs)
                Label questionLabel = (Label) questionView.getChildren().get(0);
                TextField questionTextField = (TextField) questionView.getChildren().get(1);

                String question = questionTextField.getText();

                // Store question in the first column of the array
                System.out.println(question);
                tab[i] = question;
            }
            QuestionnaireLibre questionnaireLibre = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getbyTitleQuestionnaireLibre(titrestatic);
            questionnaireLibre.setTitre(titre.getText());
            questionnaireLibre.setQuestion(tab);
        }
        try {
            Parent next = (Parent)FXMLLoader.load(this.getClass().getResource("Testes.fxml"));
            Scene currentScene = this.enregistrer.getScene();
            currentScene.setRoot(next);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
