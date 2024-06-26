package com.example.cabinetorthophonist;

import Model.Dossier;
import Model.Orthophonist;
import Model.OrthophonisteSessionManager;
import Model.Rendez_vous;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AgendaController
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

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="utilisateur1"
    private Label utilisateur1;

    @FXML // fx:id="agendaligne"
    private VBox agendaligne;

    @FXML // fx:id="action"
    private Button action; // Value injected by FXMLLoader


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
    void vue_consultation(ActionEvent event) {
        try {
            // Load the FXML file for the signup page
            Parent signupRoot = FXMLLoader.load(getClass().getResource("Consultation.fxml"));
            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Create a new scene with the signup root
            Scene scene = new Scene(signupRoot, 1000, 670);

            // Set the scene on the stage
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void vue_suivi(ActionEvent event) {
        try {
            // Load the FXML file for the signup page
            Parent signupRoot = FXMLLoader.load(getClass().getResource("Suivi.fxml"));
            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Create a new scene with the signup root
            Scene scene = new Scene(signupRoot, 1000, 670);

            // Set the scene on the stage
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void vue_atelier(ActionEvent event) {
        try {
            // Load the FXML file for the signup page
            Parent signupRoot = FXMLLoader.load(getClass().getResource("atelier.fxml"));
            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Create a new scene with the signup root
            Scene scene = new Scene(signupRoot, 1000, 670);

            // Set the scene on the stage
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException, ClassNotFoundException {

        String nom = OrthophonisteSessionManager.getCurrentOrthophonisteName().getNom();
        System.out.println(nom);
        String prenom =OrthophonisteSessionManager.getCurrentOrthophonisteName().getPrenom();

        utilisateur1.setText(nom + " " + prenom);


        TreeMap<Rendez_vous, Dossier> futureRendezVous = rendezVous();

        for (Map.Entry<Rendez_vous, Dossier> entry : futureRendezVous.entrySet()) {
            Rendez_vous rendezVous = entry.getKey();
            Dossier dossier = entry.getValue();

            // Charger l'interface FXML pour chaque rendez-vous
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Agendaligne.fxml"));
            try {
                HBox hBox = fxmlLoader.load();

                // Remplir le tableau avec les informations du rendez-vous et du dossier
                AgendaligneController cic = fxmlLoader.getController();
                cic.remplir_tableau(rendezVous, dossier);

                // Ajouter le HBox à l'interface principale
                agendaligne.getChildren().add(hBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private TreeMap<Rendez_vous, Dossier> rendezVous() throws IOException, ClassNotFoundException
    {

        Orthophonist users =OrthophonisteSessionManager.getCurrentOrthophonisteName();

        TreeMap<Integer, Dossier> dossiers = users.getMes_patients();


        LocalDate now = LocalDate.now();

        TreeMap<Rendez_vous, Dossier> futureRendezVous = RendezVousManager.getFutureRendezVous(dossiers, now);


        return futureRendezVous;
    }

    // Stub pour comparer les rendez-vous par date uniquement
    public static class RendezVousManager
    {

        public static TreeMap<Rendez_vous, Dossier> getFutureRendezVous(TreeMap<Integer, Dossier> dossiers, LocalDate now) {
            return dossiers.entrySet().stream()
                    .flatMap(entry -> entry.getValue().getRendez_vous().stream()
                            .filter(rv -> !rv.getDate().isBefore(now))
                            .map(rv -> Map.entry(rv, entry.getValue())))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            TreeMap::new));
        }

    }



}
