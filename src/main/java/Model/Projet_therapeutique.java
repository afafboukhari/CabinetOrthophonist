package Model;

import java.io.Serializable;

public class Projet_therapeutique implements Serializable
{

    private String description;

    public Projet_therapeutique(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
