package com.example.cabinetorthophonist;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import static java.lang.Integer.parseInt;

public class AjouterFicheController {
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
    private Button enregistrer;
    @FXML
    private TextField num_dossier;
    @FXML
    private ComboBox<String> Types1;
    @FXML
    private ComboBox<String> Types2;
    @FXML
    private ComboBox<String> Types3;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private TextField textField3;

    public void initialize()
    {
        String tab[] = {"court terme","moyen terme","long terme"};
        for(int i=0;i<3;i++) {
            Types1.getItems().add(tab[i]);
            Types2.getItems().add(tab[i]);
            Types3.getItems().add(tab[i]);
        }
    }

    public void creerfiche()
    {
        Objectif objectif1 = new Objectif(textField1.getText(),Type_objectif.COURT_TERME);
        Objectif objectif2 = new Objectif(textField2.getText(),Type_objectif.MOYEN_TERME);
        Objectif objectif3 = new Objectif(textField3.getText(),Type_objectif.LONG_TERME);

        Objectif[] tab = {objectif1,objectif2,objectif3};

        Fiche_suivi ficheSuivi = new Fiche_suivi(tab);

        OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_dossiers().lastEntry();
        Dossier dossier;
        dossier = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_dossiers().get((parseInt(num_dossier.getText())));
        dossier.setFiches_suivi(ficheSuivi);
        System.out.println("Fiche ajoutée !");
    }





}
