package com.example.cabinetorthophonist;

import Model.Orthophonist;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import Model.OrthophonisteSessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;



public class LoginController
{
    @FXML
    private TextField emailField;
    @FXML
    private Label emailerrormessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label passwordErrorMessage;
    @FXML
    private Button SignInButton;
    @FXML
    private Button Signup;

    public LoginController()
    {
    }

    @FXML
    public void SignIn() {
        System.out.println("login");
        String email = this.emailField.getText();
        String password = this.passwordField.getText();
        Orthophonist orthophonist = this.authenticate(email, password);

        if (orthophonist != null) {

            this.loadNextPage(orthophonist);
            OrthophonisteSessionManager.setCurrentOrthophonisteName(orthophonist);
            Orthophonist user1=OrthophonisteSessionManager.getCurrentOrthophonisteName();
        }

    }

    private Orthophonist authenticate(String email, String password) {
        String pseudo = email.toLowerCase().replaceAll(" ", "");
        String filename = "./src/data/" + pseudo + ".ser";
        File file = new File(filename);
        if (file.exists()) {
            System.out.println("file exist");
            Orthophonist orthophonist = null;

            try {
                Throwable var7 = null;
                Object var8 = null;

                try {
                    FileInputStream fileInputStream = new FileInputStream(file);

                    Orthophonist var10000;
                    label368: {
                        try {
                            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                            try {
                                orthophonist = (Orthophonist)objectInputStream.readObject();
                                String storedEmail = orthophonist.getEmail();
                                String storedPassword = orthophonist.getMotDePasse();
                                if (email.equals(storedEmail) && password.equals(storedPassword)) {
                                    var10000 = orthophonist;
                                    break label368;
                                }

                                this.emailerrormessage.setText("Mot de passe incorrecte");
                                emailerrormessage.setVisible(true);
                            } finally {
                                if (objectInputStream != null) {
                                    objectInputStream.close();
                                }

                            }
                        } catch (Throwable var26) {
                            if (var7 == null) {
                                var7 = var26;
                            } else if (var7 != var26) {
                                var7.addSuppressed(var26);
                            }

                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }

                            throw var7;
                        }

                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }

                        return null;
                    }

                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }

                    return var10000;
                } catch (Throwable var27) {
                    if (var7 == null) {
                        var7 = var27;
                    } else if (var7 != var27) {
                        var7.addSuppressed(var27);
                    }
                    try {
                        throw var7;
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }


                }
            } catch (Throwable var28) {
                var28.printStackTrace();
            }
        } else {
            System.out.println("email mkech");
            System.out.println("Working Directory: " + System.getProperty("user.dir"));
            System.out.println("File Path: " + file.getAbsolutePath());
            this.emailerrormessage.setText("email non trouvé");
            emailerrormessage.setVisible(true);
        }

        return null;
    }

    private void loadNextPage(Orthophonist utilisateur) {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home-view.fxml"));
            Parent root = loader.load();
            Scene currentScene = SignInButton.getScene();
            currentScene.setRoot(root);

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void Signup()
    {
        try {
            Parent next = (Parent)FXMLLoader.load(getClass().getResource("signup-view.fxml"));
            Scene currentScene = this.Signup.getScene();
            currentScene.setRoot(next);
        } catch (IOException var5) {
            System.out.println("here");
            var5.printStackTrace();
        }
    }


}
