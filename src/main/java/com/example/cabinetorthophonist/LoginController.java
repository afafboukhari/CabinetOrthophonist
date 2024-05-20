package com.example.cabinetorthophonist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private Label usernameErrorMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label passwordErrorMessage;
    @FXML
    private Button SignInButton;
    @FXML
    private Button SignUpButton;

    public LoginController() {
    }

    @FXML
    public void SignIn() {
        String username = this.usernameField.getText();
        String password = this.passwordField.getText();
        Utilisateur utilisateur = this.authenticate(username, password);
        if (utilisateur != null) {
            DataManager.getInstance().setUtilisateur(utilisateur);
            ArrayList<Tache> tasks = new ArrayList();
            TaskManager.getInstance().setTasks(tasks);
            this.loadNextPage(utilisateur);
        }

    }

    @FXML
    public void SignUp() {
        String PageRouter = "Registration/Registration.fxml";

        try {
            Parent next = (Parent)FXMLLoader.load(this.getClass().getResource("../../Frontend/Pages/" + PageRouter));
            Scene currentScene = this.SignInButton.getScene();
            currentScene.setRoot(next);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    private Utilisateur authenticate(String username, String password) {
        String pseudo = username.toLowerCase().replaceAll(" ", "");
        String filename = "./src/TimePlanner/UsersInformation/" + pseudo + ".ser";
        File file = new File(filename);
        if (file.exists()) {
            Utilisateur utilisateur = null;

            try {
                Throwable var7 = null;
                Object var8 = null;

                try {
                    FileInputStream fileInputStream = new FileInputStream(file);

                    Utilisateur var10000;
                    label368: {
                        try {
                            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                            try {
                                utilisateur = (Utilisateur)objectInputStream.readObject();
                                String storedUsername = utilisateur.getProfile().getNom();
                                String storedPassword = utilisateur.getProfile().getPassword();
                                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                                    var10000 = utilisateur;
                                    break label368;
                                }

                                this.passwordErrorMessage.setText("Invalid password");
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

                    throw var7;
                }
            } catch (ClassNotFoundException | IOException var28) {
                var28.printStackTrace();
            }
        } else {
            System.out.println("Working Directory: " + System.getProperty("user.dir"));
            System.out.println("File Path: " + file.getAbsolutePath());
            this.usernameErrorMessage.setText("Username does not exist");
        }

        return null;
    }

    private void loadNextPage(Utilisateur utilisateur) {
        String nextPage;
        if (utilisateur.getProjets_en_cours() != null && utilisateur.getProjets_en_cours().size() != 0) {
            nextPage = "../../Frontend/Pages/Profile/Profile.fxml";
        } else {
            nextPage = "../../Frontend/Pages/PeriodChoice/PeriodChoice.fxml";
        }

        try {
            Parent next = (Parent)FXMLLoader.load(this.getClass().getResource(nextPage));
            Scene currentScene = this.SignUpButton.getScene();
            currentScene.setRoot(next);
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }
}
