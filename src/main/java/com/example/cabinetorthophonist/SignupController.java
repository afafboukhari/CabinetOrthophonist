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

    public void tamere()
    {
        System.out.println("emchi");
    }

}
