package Model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerieExercices implements Serializable
{
    private String titre;
    private String[][] Exercice;
    private int capacite;

    public SerieExercices(String titre, String[][] exercice, int capacite) {
        this.titre = titre;
        Exercice = exercice;
        this.capacite = capacite;
        try {
            FileOutputStream fileOut = new FileOutputStream("src/data/"+"SerieExercices_"+titre+".ser");
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
