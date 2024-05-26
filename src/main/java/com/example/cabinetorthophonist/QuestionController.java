package com.example.cabinetorthophonist;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;

public class QuestionController
{
    static String question="";
    static String choix1="";
    static String choix2="";
    static String choix3="";

    @FXML
    private Label questionLabel;
    @FXML
    private TextField questionTextField;
    @FXML
    private TextField choice1TextField;
    @FXML
    private TextField choice2TextField;
    @FXML
    private TextField choice3TextField;

    public void initilize()
    {
        questionTextField.setText(question);
        choice1TextField.setText(choix1);
        choice2TextField.setText(choix2);
        choice3TextField.setText(choix3);
    }

    public Labeled getQuestionLabel()
    {
        return this.questionLabel;
    }
    public TextField getQuestionTextField()
    {
        return this.questionTextField;
    }
    public TextField getChoice1TextField()
    {
        return this.choice1TextField;
    }
    public TextField getChoice2TextField()
    {
        return this.choice2TextField;
    }
    public TextField getChoice3TextField()
    {
        return this.choice3TextField;
    }


}
