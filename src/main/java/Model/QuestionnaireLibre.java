package Model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class QuestionnaireLibre implements Serializable
{
    private String titre;
    private String Question[];
    private int capacite;


    public QuestionnaireLibre(String titre, String[] question, int capacite) {
        this.titre = titre;
        Question = question;
        this.capacite = capacite;
        try {
            FileOutputStream fileOut = new FileOutputStream("src/data/"+"QuestionnaireRepLibre_"+titre+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("sauvegardé avec succees !");
        } catch (IOException var3) {
            System.out.println("Une erreure est servenue : " + var3.getMessage());
        }
    }
}
