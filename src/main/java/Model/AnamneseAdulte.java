package Model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class AnamneseAdulte implements Test, Serializable
{
    private String titre;
    private String[] Question;

    public AnamneseAdulte(String titre, String[] question) {
        this.titre = titre;
        Question = question;
        try {
            FileOutputStream fileOut = new FileOutputStream("src/data/"+"AnamneseAdulte_"+titre+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("sauvegardé avec succees !");
        } catch (IOException var3) {
            System.out.println("Une erreure est servenue : " + var3.getMessage());
        }
    }

    @Override
    public void lire() throws IOException
    {

    }

    @Override
    public void creer()
    {

    }

    public String[] getQuestion() {
        return Question;
    }

    public void setQuestion(String[] question) {
        Question = question;
    }
}
