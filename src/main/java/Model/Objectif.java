package Model;

import java.io.Serializable;

public class Objectif  implements Serializable {
    private String nom;
    private Type_objectif type;

    public Objectif(String nom, Type_objectif type) {
        this.nom = nom;
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public Type_objectif getType() {
        return type;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(Type_objectif type) {
        this.type = type;
    }
}
