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
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String[][] getExercice() {
        return Exercice;
    }

    public void setExercice(String[][] exercice) {
        Exercice = exercice;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
}
