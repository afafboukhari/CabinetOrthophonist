package com.example.cabinetorthophonist;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ReponseTestController2
{
    @FXML
    private Label question;
    @FXML
    private Label choix;
    @FXML
    private TextField answer;


    public Label getQuestion() {
        return question;
    }

    public void setQuestion(Label question) {
        this.question = question;
    }

    public TextField getAnswer() {
        return answer;
    }

    public void setAnswer(TextField answer) {
        this.answer = answer;
    }

    public Label getChoix() {
        return choix;
    }

    public void setChoix(Label choix) {
        this.choix = choix;
    }
}