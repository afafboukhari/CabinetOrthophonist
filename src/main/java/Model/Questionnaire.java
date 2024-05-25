package Model;

import java.io.*;

public class Questionnaire implements Serializable
{
    private String titre;
    private String[][] Question;
    private int capacite;

    public String[][] getQuestion() {
        return Question;
    }

    public void setQuestion(String[][] question) {
        Question = question;
    }

    public Questionnaire(String titre, String[][] Question, int capacite)
    {
        this.titre = titre;
        this.Question = Question;
        this.capacite = capacite;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
