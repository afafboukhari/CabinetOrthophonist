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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.TreeMap;

public class BOAnamneseController
{

    static boolean adulte;
    static Patient patient;

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

    AnamneseAdulte anamneseAdulte;
    AnamneseEnfant anamneseEnfant;
    @FXML
    private ComboBox Sujet;
    public void initialize() {
        ArrayList<AnamneseAdulte> anamneseList1 = new ArrayList<>();
        ArrayList<AnamneseEnfant> anamneseList2 = new ArrayList<>();

        if (adulte) {
            anamneseList1 = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getAnamneseAdultes();
        } else {
            anamneseList2 = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getAnamneseEnfants();
        }

        if (anamneseList1 != null) {
            // Iterate through the list and fill the ComboBox
            for (AnamneseAdulte anamnese : anamneseList1) {
                String anamneseTitle = anamnese.getTitre(); // Assuming "titre" holds the display text
                Sujet.getItems().add(anamneseTitle);
            }
        }
        if (anamneseList2 != null) {
            // Iterate through the list and fill the ComboBox
            for (AnamneseEnfant anamnese : anamneseList2) {
                String anamneseTitle = anamnese.getTitre(); // Assuming "titre" holds the display text
                Sujet.getItems().add(anamneseTitle);
            }
        }
    }

    @FXML
    private VBox container;
    public void generateTextFields(ActionEvent event) {
        int existingViews = container.getChildren().size() - 1;

        // Remove existing views only if necessary
        if (existingViews > 0) {
            container.getChildren().remove(0, existingViews + 1); // Remove from index 1 (inclusive) to existingViews (exclusive)
        }
        if(adulte)
        {
            anamneseAdulte = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getbyTitleAnamneseAdulte((String) Sujet.getSelectionModel().getSelectedItem());
            String[] Question = new String[2];
            Question = anamneseAdulte.getQuestion();
            for (int i = 0; i < 2; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ReponseTest.fxml")); // Load your FXML
                try {
                    VBox questionView = loader.load(); // Load the custom view
                    ReponseTestController controller = loader.getController(); // Access the custom view's controller (if needed)
                    controller.getQuestion().setText("Question " + (i + 1) + " :" + Question[i] ); // Set question text (optional)
                    container.getChildren().add(questionView);
                } catch (IOException e) {
                    e.printStackTrace(); // Handle FXML loading errors
                }
            }
        }else{
            anamneseEnfant = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getbyTitleAnamneseEnfant((String)Sujet.getSelectionModel().getSelectedItem());
            String[] Question = new String[7];
            Question = anamneseEnfant.getQuestion();
            for (int i = 0; i < 7; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ReponseTest.fxml")); // Load your FXML
                try {
                    VBox questionView = loader.load(); // Load the custom view
                    ReponseTestController controller = loader.getController(); // Access the custom view's controller (if needed)
                    controller.getQuestion().setText("Question " + (i + 1) + " :" + Question[i] ); // Set question text (optional)
                    container.getChildren().add(questionView);
                } catch (IOException e) {
                    e.printStackTrace(); // Handle FXML loading errors
                }
            }
        }
    }

    @FXML
    private Button suivant;
    public void save_answers()
    {
//        if(adulte)
//        {
//            for (Node child : container.getChildren()) {
//                if (child instanceof VBox) { // Assuming questionView is a VBox
//                    ReponseTestController childController = ((VBox) child).getController();
//                    String userResponse = String.valueOf(childController.getAnswer());
//                }
//            }
//        }else{
//
//        }
//        try {
//            FileOutputStream fileOut = new FileOutputStream("src/data/"+orthophonist.getEmail() + ".ser");
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(orthophonist);
//            out.close();
//            fileOut.close();
//            System.out.println("Compte sauvegardé avec succees !");
//        } catch (IOException var3) {
//            System.out.println("Une erreure est servenue : " + var3.getMessage());
//        }
        try {
            Parent next = (Parent)FXMLLoader.load(this.getClass().getResource("EpreuveEcrite.fxml"));
            Scene currentScene = this.suivant.getScene();
            currentScene.setRoot(next);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
