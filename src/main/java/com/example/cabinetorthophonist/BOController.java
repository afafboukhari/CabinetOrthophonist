package com.example.cabinetorthophonist;

import Model.Dossier;
import Model.Orthophonist;
import Model.OrthophonisteSessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.TreeMap;

public class BOController
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
    private ComboBox<String> patients;
    @FXML
    private Button suivant;

    public void initialize()
    {
        TreeMap<Integer, Dossier> Dossiers;
        Dossiers = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_dossiers();

        if (Dossiers != null) {
            // Iterate through the TreeMap
            for (Dossier dossier : Dossiers.values()) {
                // Assuming "nom" is the attribute you want to display in the ComboBox
                String patient = dossier.getPatient().getNom()+" "+dossier.getPatient().getPrenom();
                patients.getItems().add(patient);
            }
        }

    }

    public void next()
    {
        String patient = patients.getValue();
        BOAnamneseController.adulte = patient.equals("Boukhari Afaf");
        try {
            Parent next = (Parent)FXMLLoader.load(this.getClass().getResource("BO-Anamnese.fxml"));
            Scene currentScene = this.suivant.getScene();
            currentScene.setRoot(next);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
