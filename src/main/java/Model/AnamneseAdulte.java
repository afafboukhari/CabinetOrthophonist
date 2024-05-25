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
        Question = question;
        AnamneseAdulte anamneseAdulte = new AnamneseAdulte(titre,question);
        OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_test().setAnamneseAdultes(anamneseAdulte);
        System.out.println("sauvegardé avec succees !");
    }

    public String[] getQuestion() {
        return Question;
    }

    public void setQuestion(String[] question) {
        Question = question;
    }
}
