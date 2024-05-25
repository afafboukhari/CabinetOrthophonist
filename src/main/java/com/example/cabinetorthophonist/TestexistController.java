package com.example.cabinetorthophonist;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TestexistController implements Initializable
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
                Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
                javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
                Scene.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private ComboBox<String> AnamneseAdulte;
    @FXML
    private  ComboBox<String> AnamneseEnfant;
    @FXML
    private  ComboBox<String> QCMQCU;
    @FXML
    private  ComboBox<String> QuestionnaireLibre;
    @FXML
    private ComboBox<String> SerieExercice;

    private ObservableList<String> testOptions;
    private ObservableList<String> testOptions1;
    private ObservableList<String> testOptions2;
    private ObservableList<String> testOptions3;
    private ObservableList<String> testOptions4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        testOptions = FXCollections.observableArrayList();
        int size = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getAnamneseAdultes().size();
        for (int i = 0; i < size; i++) {
            testOptions.add(OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getAnamneseAdultes().get(i).getTitre());
        }
        AnamneseAdulte.setItems(testOptions);


        testOptions1 = FXCollections.observableArrayList();
        size = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getAnamneseEnfants().size();
        for (int i = 0; i < size; i++) {
            testOptions1.add(OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getAnamneseEnfants().get(i).getTitre());
        }
        AnamneseEnfant.setItems(testOptions1);

        testOptions2 = FXCollections.observableArrayList();
        size = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getQuestionnaires().size();
        for (int i = 0; i < size; i++) {
            testOptions2.add(OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getQuestionnaires().get(i).getTitre());
        }
        QCMQCU.setItems(testOptions2);

        testOptions3 = FXCollections.observableArrayList();
        size = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getQuestionnaireLibre().size();
        for (int i = 0; i < size; i++) {
            testOptions3.add(OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getQuestionnaireLibre().get(i).getTitre());
        }
        QuestionnaireLibre.setItems(testOptions3);

        testOptions4 = FXCollections.observableArrayList();
        size = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getSerieExercices().size();
        for (int i = 0; i < size; i++) {
            testOptions4.add(OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getSerieExercices().get(i).getTitre());
        }
        SerieExercice.setItems(testOptions4);
    }

    @FXML
    public void handleComboBoxAction1() throws IOException {
        System.out.println("a");
        String selectedOption = AnamneseAdulte.getSelectionModel().getSelectedItem();
        AnamneseAdulte anamneseAdulte = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getbyTitleAnamneseAdulte(selectedOption);
        String PageRouter = "CreerAnamneseAdulte.fxml";

        if (anamneseAdulte != null) {
            try {
                AnamneseAdulteController.titrestatic = anamneseAdulte.getTitre();
                AnamneseAdulteController.qst1static = anamneseAdulte.getQuestion()[0];
                AnamneseAdulteController.qst2static = anamneseAdulte.getQuestion()[1];
                FXMLLoader loader = new FXMLLoader(getClass().getResource(PageRouter));
                Parent nextPage = loader.load();
                // Get the current stage and set the new scene
                Stage stage = (Stage) AnamneseAdulte.getScene().getWindow();
                Scene scene = new Scene(nextPage, 1000, 670);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No matching AnamneseAdulte found for the selected option.");
        }
    }

    @FXML
    public void handleComboBoxAction2() throws IOException {
        System.out.println("a");
        String selectedOption = AnamneseEnfant.getSelectionModel().getSelectedItem();
        AnamneseEnfant anamneseEnfant = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getbyTitleAnamneseEnfant(selectedOption);
        String PageRouter = "CreerAnamneseEnfant.fxml";

        if (anamneseEnfant != null) {
            try {
                System.out.println(anamneseEnfant.getQuestion()[3]);
                AnamneseEnfantController.titrestatic = anamneseEnfant.getTitre();
                AnamneseEnfantController.qst1static = anamneseEnfant.getQuestion()[0];
                AnamneseEnfantController.qst2static = anamneseEnfant.getQuestion()[1];
                AnamneseEnfantController.qst3static = anamneseEnfant.getQuestion()[2];
                AnamneseEnfantController.qst4static = anamneseEnfant.getQuestion()[3];
                AnamneseEnfantController.qst5static = anamneseEnfant.getQuestion()[4];
                AnamneseEnfantController.qst6static = anamneseEnfant.getQuestion()[5];
                AnamneseEnfantController.qst7static = anamneseEnfant.getQuestion()[6];

                FXMLLoader loader = new FXMLLoader(getClass().getResource(PageRouter));
                Parent nextPage = loader.load();
                // Get the current stage and set the new scene
                Stage stage = (Stage) AnamneseEnfant.getScene().getWindow();
                Scene scene = new Scene(nextPage, 1000, 670);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No matching AnamneseEnfant found for the selected option.");
        }
    }

    @FXML
    public void handleComboBoxAction3() throws IOException {
        System.out.println("a");
        String selectedOption = QCMQCU.getSelectionModel().getSelectedItem();
        Questionnaire questionnaire = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getbyTitleQuestionnaire(selectedOption);
        String PageRouter = "CreerQuestionnaire.fxml";

        if (questionnaire != null) {
            try {
                QuestionnaireController.titrestatic = questionnaire.getTitre();
                QuestionnaireController.size = questionnaire.getCapacite();
                QuestionnaireController.tab = questionnaire.getQuestion();
                FXMLLoader loader = new FXMLLoader(getClass().getResource(PageRouter));
                Parent nextPage = loader.load();
                // Get the current stage and set the new scene
                Stage stage = (Stage) AnamneseAdulte.getScene().getWindow();
                Scene scene = new Scene(nextPage, 1000, 670);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No matching Questionnaire found for the selected option.");
        }
    }
    @FXML
    public void handleComboBoxAction5() throws IOException {
        String selectedOption = QuestionnaireLibre.getSelectionModel().getSelectedItem();
        Model.QuestionnaireLibre questionnaire = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getbyTitleQuestionnaireLibre(selectedOption);
        String PageRouter = "CreerRepLibres.fxml";

        if (questionnaire != null) {
            try {
                QuestionnaireLibreController.titrestatic = questionnaire.getTitre();
                QuestionnaireLibreController.size = questionnaire.getCapacite();
                QuestionnaireLibreController.tab1 = questionnaire.getQuestion();
                FXMLLoader loader = new FXMLLoader(getClass().getResource(PageRouter));
                Parent nextPage = loader.load();
                // Get the current stage and set the new scene
                Stage stage = (Stage) AnamneseAdulte.getScene().getWindow();
                Scene scene = new Scene(nextPage, 1000, 670);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No matching Questionnaire found for the selected option.");
        }
    }
    public void handleComboBoxAction4() throws IOException {
        String selectedOption = SerieExercice.getSelectionModel().getSelectedItem();
        Model.SerieExercices serieExercices = OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().getbyTitleSerieExercices(selectedOption);
        String PageRouter = "CreerExercice.fxml";

        if (serieExercices != null) {
            try {
                SerieExercicesController.titrestatic = serieExercices.getTitre();
                SerieExercicesController.size = serieExercices.getCapacite();
                SerieExercicesController.tab1 = serieExercices.getExercice();
                FXMLLoader loader = new FXMLLoader(getClass().getResource(PageRouter));
                Parent nextPage = loader.load();
                // Get the current stage and set the new scene
                Stage stage = (Stage) AnamneseAdulte.getScene().getWindow();
                Scene scene = new Scene(nextPage, 1000, 670);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No matching Exercices found for the selected option.");
        }
    }

}
