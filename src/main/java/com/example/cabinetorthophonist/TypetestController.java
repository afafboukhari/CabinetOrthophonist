package com.example.cabinetorthophonist;

import Model.AnamneseAdulte;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TypetestController
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
        //  PageRouter = "/com/example/tp_poo/Login.fxml";

        if (newPage) {
            try {
                // Load the desired page
                Parent nextPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(PageRouter)));
                PageData pageData = new PageData();
                pageData.setSomeBooleanValue(true);
                Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
                javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
                Scene.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private Label AnamneseAdulte;
    @FXML
    private Label AnamneseEnfant;
    @FXML
    private Label QCM_QCU;
    @FXML
    private Label SerieExercice;
    @FXML
    private Label Rep_libre;

    @FXML
    public void handleRouting2(MouseEvent event) {

        Label label = (Label) event.getSource();
        String labelText = label.getText();


        String PageRouter = "Testes-view.fxml"; // Chemin par défaut
        boolean newPage = false;

        switch (labelText) {
            case "Sujet Anamnese pour adulte":
                newPage = true;
                PageRouter = "CreerAnamneseAdulte.fxml";
                break;

            case "Sujet Anamnese pour enfant":
                newPage = true;
                PageRouter = "CreerAnamneseEnfant.fxml";
                break;

            case "QCM,QCU":
                newPage = true;
                PageRouter = "CreerQuestionnaire.fxml";
                break;

            case "Serie d'exercice":
                newPage = true;
                PageRouter = "CreerExercice.fxml";
                break;

            case "Questionnaire á reponses libres":
                newPage = true;
                PageRouter = "CreerRepLibres.fxml";
                break;

            default:
                newPage = true;
                PageRouter = "CreerRepLibres.fxml";
                break;
        }
        //  PageRouter = "/com/example/tp_poo/Login.fxml";

        if (newPage) {
            try {
                // Load the desired page
                System.out.println("hehehehehe");
                Parent nextPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(PageRouter)));
                Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
                javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
                Scene.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
