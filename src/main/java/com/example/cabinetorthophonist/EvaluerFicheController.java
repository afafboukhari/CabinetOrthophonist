package com.example.cabinetorthophonist;

import Model.Dossier;
import Model.Fiche_suivi;
import Model.Orthophonist;
import Model.OrthophonisteSessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.TreeMap;


import static java.lang.Integer.parseInt;

public class EvaluerFicheController
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
                Orthophonist user = OrthophonisteSessionManager.getCurrentOrthophonisteName();
                String username = user.getCompte().getEmail();
                String filepath = "./src/main/Userinformation/" + username + ".ser";
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

                Stage Scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
                javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
                Scene.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private TextField numero_dossier;
    @FXML
    private Text objectif1;
    @FXML
    private Text objectif2;
    @FXML
    private Text objectif3;
    @FXML
    private Button Evaluer;
    @FXML
    private Button Enregistrer;
    @FXML
    private CheckBox checkBox1;
    @FXML
    private CheckBox checkBox2;
    @FXML
    private CheckBox checkBox3;

    public void generateText()
    {
        Dossier dossier = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_dossiers().get(parseInt(numero_dossier.getText()));
        Fiche_suivi ficheSuivi = dossier.getFiches_suivi().getLast();
        objectif1.setText(ficheSuivi.getObjectifs()[0].getNom());
        objectif2.setText(ficheSuivi.getObjectifs()[1].getNom());
        objectif3.setText(ficheSuivi.getObjectifs()[2].getNom());

        if(ficheSuivi.isAtteint())
        {
            checkBox1.setSelected(true);
            checkBox2.setSelected(true);
            checkBox3.setSelected(true);
        }

    }

    public void enregistrer_evaluation()
    {
        Dossier dossier = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_dossiers().get(parseInt(numero_dossier.getText()));
        Fiche_suivi ficheSuivi = dossier.getFiches_suivi().getLast();
        ficheSuivi.setAtteint(true);
        System.out.println("done");
        try {
            Parent next = (Parent)FXMLLoader.load(this.getClass().getResource("FicheDeSuivi.fxml"));
            Scene currentScene = this.Evaluer.getScene();
            currentScene.setRoot(next);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
