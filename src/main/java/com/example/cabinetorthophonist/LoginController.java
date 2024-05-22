package com.example.cabinetorthophonist;

import Model.Orthophonist;
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
import java.util.ArrayList;



public class LoginController
{
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

    public LoginController()
    {

    }

    @FXML
    public void SignIn() {
        String username = this.usernameField.getText();
        String password = this.passwordField.getText();
        Orthophonist orthophonist = this.authenticate(username, password);
        if (orthophonist != null) {
            this.loadNextPage(orthophonist);
        }

    }

    private Orthophonist authenticate(String username, String password) {
        String pseudo = username.toLowerCase().replaceAll(" ", "");
        String filename = "./src/data/" + pseudo + ".ser";
        File file = new File(filename);
        if (file.exists()) {
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
                                String storedUsername = orthophonist.getNom();
                                String storedPassword = orthophonist.getMotDePasse();
                                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                                    var10000 = orthophonist;
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
            System.out.println("Working Directory: " + System.getProperty("user.dir"));
            System.out.println("File Path: " + file.getAbsolutePath());
            this.usernameErrorMessage.setText("Username does not exist");
        }

        return null;
    }

    private void loadNextPage(Orthophonist orthophonist) {
        String nextPage;
        nextPage = "com/example/cabinetorthophonist/home-view.fxml";

        try {
            Parent next = (Parent)FXMLLoader.load(this.getClass().getResource(nextPage));
            Scene currentScene = this.SignInButton.getScene();
            currentScene.setRoot(next);
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }


}
