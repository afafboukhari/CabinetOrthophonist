package com.example.cabinetorthophonist;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;

public class QuestionController
{
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

    public Labeled getQuestionLabel()
    {
        return this.questionLabel;
    }
}
