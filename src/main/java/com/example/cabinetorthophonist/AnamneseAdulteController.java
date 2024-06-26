package com.example.cabinetorthophonist;

import Model.AnamneseAdulte;
import Model.Orthophonist;
import Model.OrthophonisteSessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import java.io.IOException;

public class AnamneseAdulteController {
    static boolean ajouter;
    static String titrestatic = "";
    static String qst1static = "";
    static String qst2static = "";

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
    private TextField qst1;
    @FXML
    private TextField qst2;
    @FXML
    private Button enregistrer;
    @FXML
    private Label label;

    public void initialize() {
        titre.setText(titrestatic);
        qst1.setText(qst1static);
        qst2.setText(qst2static);
        if (!ajouter) {
            label.setText("Voici votre sujet d'anamnese déstiné au adults (" + titrestatic+")");
            enregistrer.setText("Modifier");
        }

    }

    @FXML
    public void create() {
        if (ajouter) {
            String[] tab = {qst1.getText(), qst2.getText()};
            AnamneseAdulte test = new AnamneseAdulte(titre.getText(), tab);
            OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getAnamneseAdultes().add(test);
        } else {
            String[] tab = {qst1.getText(), qst2.getText()};
            AnamneseAdulte anamneseAdulte = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getbyTitleAnamneseAdulte(titrestatic);
            anamneseAdulte.setTitre(titre.getText());
            anamneseAdulte.setQuestion(tab);
        }
        try {
            Parent next = (Parent) FXMLLoader.load(this.getClass().getResource("Testes.fxml"));
            Scene currentScene = this.enregistrer.getScene();
            currentScene.setRoot(next);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
