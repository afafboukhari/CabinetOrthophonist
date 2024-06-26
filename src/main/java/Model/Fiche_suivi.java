package Model;

import java.io.Serializable;

public class Fiche_suivi implements Serializable {
    static int num ;
    Patient patient;
    private Objectif[] objectifs;
    private boolean atteint = false;

    public  int getNum() {
        return num;
    }

    public  void setNum(int num) {
        Fiche_suivi.num = num;
    }

    public Fiche_suivi()
    {
    }

    public Fiche_suivi(Objectif[] objectifs) {
        this.objectifs = objectifs;
        this.num=num+1;

    }

    public void setObjectifs(Objectif[] objectifs) {
        this.objectifs = objectifs;
    }

    public Objectif[] getObjectifs() {
        return objectifs;
    }

    public boolean isAtteint() {
        return atteint;
    }

    public void setAtteint(boolean atteint) {
        this.atteint = atteint;
    }
}
