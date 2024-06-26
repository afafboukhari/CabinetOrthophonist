package com.example.cabinetorthophonist;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class InfopersonnelleController implements Initializable {


    @FXML
    private Label adressepatient;

    @FXML
    private Label classd;

    @FXML
    private Label datenpatient;

    @FXML
    private Label diplome;

    @FXML
    private Label info1;

    @FXML
    private Label info2;

    @FXML
    private Label info3;

    @FXML
    private Label lieunpatient;

    @FXML
    private Label nompatient;

    @FXML
    private Label numadulte;

    @FXML
    private Label nummere;

    @FXML
    private Label numpere;

    @FXML
    private Label prenompatient;

    @FXML
    private Label profession;

    @FXML
    private Label utilisateur1;

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        Orthophonist user= OrthophonisteSessionManager.getCurrentOrthophonisteName();

        utilisateur1.setText(user.getCompte().getNom() + " " + user.getCompte().getPrenom());

    }

    public void setInfoData(Patient patient)
    {

        if( patient instanceof Enfant)
        {
            nompatient.setText(((Enfant)patient).getNom());
            prenompatient.setText(((Enfant)patient).getPrenom());
            datenpatient.setText(String.valueOf(((Enfant)patient).getDate_naissance()));
            lieunpatient.setText(((Enfant)patient).getLieu_naissance());
            adressepatient.setText(((Enfant)patient).getAdresse());
            info1.setText(((Enfant)patient).getClass_etude());
            int numparent[]=((Enfant)patient).getNumeroparent();
            //info2.setText(String.valueOf(numparent[0]));
            //info3.setText(String.valueOf(numparent[1]));

            profession.setVisible(false);
            diplome.setVisible(false);
            numadulte.setVisible(false);

        }
        if( patient instanceof Adulte)
        {
            nompatient.setText(((Adulte)patient).getNom());
            prenompatient.setText(((Adulte)patient).getPrenom());
            datenpatient.setText(String.valueOf(((Adulte)patient).getDate_naissance()));
            lieunpatient.setText(((Adulte)patient).getLieu_naissance());
            adressepatient.setText(((Adulte)patient).getAdresse());
            info1.setText(((Adulte)patient).getProfession());
            info2.setText(((Adulte)patient).getDimplome());
            info3.setText(String.valueOf(((Adulte)patient).getNumero_personnel()));
            classd.setVisible(false);
            nummere.setVisible(false);
            numpere.setVisible(false);
        }

    }
    @FXML
    void retour(ActionEvent event)
    {
        try {
            String PageRouter = "Dossier.fxml";
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
