package com.example.cabinetorthophonist;

import Model.Orthophonist;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignupController
{
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField motdepasse;
    @FXML
    private TextField numerotelephone;
    @FXML
    private TextField adress;
    @FXML
    private Button retourner;
    @FXML
    private Button creer;

    public void SignUp()
    {
        String nomvalue = this.nom.getText();
        String prenomvalue = this.prenom.getText();
        String password = this.motdepasse.getText();
        String emailvalue = this.email.getText();
        String numerotelephonevalue = this.numerotelephone.getText();
        String adressvalue = this.adress.getText();
        new Orthophonist(nomvalue,prenomvalue,emailvalue,password,numerotelephonevalue,adressvalue);
        System.out.println("done");
        this.returne();
    }

    @FXML
    public void returne() {
        try {
            Parent next = (Parent)FXMLLoader.load(this.getClass().getResource("login-view.fxml"));
            Scene currentScene = this.creer.getScene();
            currentScene.setRoot(next);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

}
