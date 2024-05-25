package Model;

import java.io.Serializable;
import java.util.TreeSet;

public class Agenda  implements Serializable {
    private TreeSet<Rendez_vous>rendez_vous ;
    private int nbr_rdv =0;

    public Agenda() {

        this.rendez_vous = new TreeSet<Rendez_vous>();

    }

    public TreeSet<Rendez_vous> getRendez_vous() {
        return rendez_vous;
    }

    public void setRendez_vous(TreeSet<Rendez_vous> rendez_vous) {
        this.rendez_vous = rendez_vous;
    }

    public void add_rendez_vous(Rendez_vous rdv){

        this.rendez_vous.add(rdv);

    }

}