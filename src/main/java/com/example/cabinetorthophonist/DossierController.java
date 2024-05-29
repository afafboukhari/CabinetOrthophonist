package com.example.cabinetorthophonist;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class DossierController implements Initializable {


    @FXML
    private AnchorPane Infopersonnelle;

    @FXML
    private Label dossierusername;

    @FXML
    private AnchorPane patientbo;

    @FXML
    private AnchorPane patientfiche;

    @FXML
    private AnchorPane patientrendezvous;

    @FXML
    private Label utilisateur1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Orthophonist user= OrthophonisteSessionManager.getCurrentOrthophonisteName();

        utilisateur1.setText(user.getCompte().getNom() + " " + user.getCompte().getPrenom());

    }




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
    void retour(ActionEvent event)
    {
        try {
            String PageRouter = "Patients.fxml";
            // Load the desired page
            Parent nextPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(PageRouter)));
            Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
            javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
            Scene.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    void retour(ActionEvent event)
//    {
//        try {
//            String PageRouter = "/com/example/tp_poo/Patient.fxml";
//            // Load the desired page
//            Parent nextPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(PageRouter)));
//            Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
//            javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
//            Scene.setScene(scene);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public void setDossierData(int num_dossier) throws IOException, ClassNotFoundException
    {
        Orthophonist user=OrthophonisteSessionManager.getCurrentOrthophonisteName();
        Dossier dossier = user.rechercher_patient(num_dossier);
        if (dossier != null)
        {

            Patient patient = dossier.getPatient();
            dossierusername.setText(patient.getNom()+" "+patient.getPrenom());



            Infopersonnelle.setOnMouseClicked(event ->
            {
                try
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Infopersonnelle.fxml"));
                    Parent root = loader.load();
                    InfopersonnelleController InfoController = loader.getController();
                    InfoController.setInfoData(patient);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root, 1000, 670);
                    stage.setScene(scene);

                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            });

            patientfiche.setOnMouseClicked(event ->
            {
                try
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Fiche.fxml"));
                    Parent root = loader.load();
                    FicheDeSuiviController fiche = loader.getController();
                    //fiche.setficheData(dossier);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root, 1000, 670);
                    stage.setScene(scene);

                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            });

            patientrendezvous.setOnMouseClicked(event ->
            {
                try
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("RendezvousPatient.fxml"));
                    Parent root = loader.load();
                    RendezvousPatientController rend = loader.getController();
                    rend.setficheData(dossier);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root, 1000, 670);
                    stage.setScene(scene);

                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            });
            patientbo.setOnMouseClicked(event ->
            {
                try
                {


                    FXMLLoader loader = new FXMLLoader(getClass().getResource("BO.fxml"));
                    Parent root = loader.load();
                    BOController rend = loader.getController();
                    //rend.setficheData(dossier);
//                    Stage stage = new Stage();
//                    stage.setScene(new Scene(root, 1000, 670));
//                    stage.show();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root, 1000, 670);
                    stage.setScene(scene);

                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            });
        }
    }



}
