package Model;

import java.io.Serializable;

public class BO  implements Serializable
{
    private Epreuve_clinique epreuves_cliniques[];
    private Diagnostique diagnostique;
    private Projet_therapeutique projet;

    public BO()
    {
    }

    public BO(Epreuve_clinique[] epreuves_cliniques, Diagnostique diagnostique, Projet_therapeutique projet) {
        this.epreuves_cliniques = epreuves_cliniques;
        this.diagnostique = diagnostique;
        this.projet = projet;
    }

    public BO(Projet_therapeutique po) {

        this.projet = po;



    }

    public BO(Projet_therapeutique po, Epreuve_clinique[] s) {

        this.epreuves_cliniques = s;
        this.projet = po;

    }

    public Epreuve_clinique[] getEpreuves_cliniques() {
        return epreuves_cliniques;
    }

    public void setEpreuves_cliniques(Epreuve_clinique[] epreuves_cliniques) {
        this.epreuves_cliniques = epreuves_cliniques;
    }

    public Diagnostique getDiagnostique() {
        return diagnostique;
    }

    public void setDiagnostique(Diagnostique diagnostique) {
        this.diagnostique = diagnostique;
    }

    public Projet_therapeutique getProjet() {
        return projet;
    }

    public void setProjet(Projet_therapeutique projet) {
        this.projet = projet;
    }
}
