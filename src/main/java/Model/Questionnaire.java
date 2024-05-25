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
        try {
            FileOutputStream fileOut = new FileOutputStream("src/data/"+"Questionnaire_"+titre+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("sauvegardé avec succees !");
        } catch (IOException var3) {
            System.out.println("Une erreure est servenue : " + var3.getMessage());
        }
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
}
