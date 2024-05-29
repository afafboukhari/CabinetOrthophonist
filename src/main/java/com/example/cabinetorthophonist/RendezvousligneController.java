package  com.example.cabinetorthophonist;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RendezvousligneController
{

    @FXML
    private Label date;

    @FXML
    private Label deroulement;

    @FXML
    private Label duree;

    @FXML
    private Label heure;

    @FXML
    private Label info1;

    @FXML
    private Label info2;

    @FXML
    private Label objecetifseance;

    @FXML
    private Label observation;

    @FXML
    private Label thematiaue;

    @FXML
    private Label typeobj;

    @FXML
    private Label typerend;

    public void setData(Rendez_vous rend)
    {
        if(rend instanceof Consultation)
        {
            thematiaue.setVisible(false);
            objecetifseance.setVisible(false);
            deroulement.setVisible(false);
            info1.setVisible(false);
            info2.setVisible(false);

            date.setText(String.valueOf(rend.getDate()));
            //date.setText("22/12/2024");
            heure.setText(String.valueOf(rend.getHeure()));

            duree.setText(((Consultation)rend).getDuree());
            typerend.setText(String.valueOf(rend.getType()));
            observation.setText(rend.getObservation());


        }
        if(rend instanceof Suivi)
        {
            thematiaue.setVisible(false);
            objecetifseance.setVisible(true);
            deroulement.setVisible(true);
            info1.setVisible(true);
            info2.setVisible(true);
            date.setText(String.valueOf(rend.getDate()));
            //date.setText("22/12/2024");

            heure.setText(String.valueOf(rend.getHeure()));
            duree.setText(((Suivi)rend).getDuree());
            typerend.setText(String.valueOf(rend.getType()));
            observation.setText(rend.getObservation());
            info1.setText(String.valueOf(((Suivi)rend).getType_suivi()));
            Objectif[] obj =((Suivi)rend).getObjectif_seance();
            info2.setText('-'+obj[0].getNom() +"\n -"+obj[1].getNom() );


        }
        if(rend instanceof Atelier)
        {

            thematiaue.setVisible(true);
            objecetifseance.setVisible(false);
            deroulement.setVisible(false);
            info1.setVisible(true);
            info2.setVisible(false);
            date.setText(String.valueOf(rend.getDate()));
            //date.setText("22/12/2024");
            heure.setText(String.valueOf(rend.getHeure()));
            duree.setText(((Atelier)rend).getDuree());
            typerend.setText(String.valueOf(rend.getType()));
            observation.setText(rend.getObservation());
            info1.setText(String.valueOf(((Atelier)rend).getThematique()));

        }

    }
}
