package Model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class AnamneseAdulte implements Serializable
{
    private String titre;
    private String[] Question;

    public AnamneseAdulte(String titre, String[] question) {
        this.titre = titre;
        this.Question = question;
    }

    public String[] getQuestion() {
        return Question;
    }

    public void setQuestion(String[] question) {
        Question = question;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
