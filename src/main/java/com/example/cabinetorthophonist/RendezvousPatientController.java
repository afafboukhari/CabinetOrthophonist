package  com.example.cabinetorthophonist;

import Model.Dossier;
import Model.Orthophonist;
import Model.OrthophonisteSessionManager;
import Model.Rendez_vous;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class RendezvousPatientController implements Initializable {


    @FXML
    private Label numfiche1;

    @FXML
    private Label numfiche11;

    @FXML
    private VBox patientslay;

    @FXML
    private Label username1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Orthophonist user= OrthophonisteSessionManager.getCurrentOrthophonisteName();

        username1.setText(user.getCompte().getNom() + " " + user.getCompte().getPrenom());

    }




    @FXML
    public void handleRouting(MouseEvent event) {

        Label label = (Label) event.getSource();
        String labelText = label.getText();


        String PageRouter = "home-view.fxml"; // Chemin par défaut
        boolean newPage = false;

        switch (labelText) {
            case "Patients":
                newPage = true;
                PageRouter = "Patients.fxml";
                break;

            case "Agenda":
                newPage = true;
                PageRouter = "Agenda.fxml";
                break;

            case "BO":
                newPage = true;
                PageRouter = "BO.fxml";
                break;

            case "Fiche de suivi":
                newPage = true;
                PageRouter = "FicheDeSuivi.fxml";
                break;

            case "Testes":
                newPage = true;
                PageRouter = "Testes.fxml";
                break;

            case "Votre profile":
                newPage = true;
                PageRouter = "Profile.fxml"; // Chemin vers la page de profil
                break;

            case "Se déconnecter":
//                Orthophonist user= OrthophonisteSessionManager.getCurrentOrthophonisteName();
//                String username =user.getEmail();
//                String filepath="./src/main/Userinformation/" + username + ".ser";
//                Orthophonist.serialize(filepath,user);
//                newPage = true;
//                PageRouter = "Login.fxml";
                break;

            default:
                newPage = true;
                PageRouter = "home-view.fxml";
                break;
        }
        //  PageRouter = "Login.fxml";

        if (newPage) {
            try {
                // Load the desired page
                Parent nextPage = FXMLLoader.load(getClass().getResource(PageRouter));

                Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
                javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
                Scene.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setficheData(Dossier dossier)
    {
        dossier.getRendez_vous();
        TreeSet<Rendez_vous> rendez_vous = new TreeSet<>(dossier.getRendez_vous());


//        LocalDate now = LocalDate.now();
//        String heure= "9:00";
//        String observation ="ya pas d observation ";
//        Objectif[] objectifs = new Objectif[3];
//
//        // Initialize the array with Objectif objects
//        objectifs[0] = new Objectif("Stay alive until the end", Type_objectif.COURT_TERME);
//        objectifs[1] = new Objectif("Complete the project", Type_objectif.MOYEN_TERME);
//        objectifs[2] = new Objectif("Achieve career goals", Type_objectif.LONG_TERME);
//
//
//
//        Rendez_vous r1 =new Consultation(now,heure,Type_rendez_vous.CONSULTATION,observation,"1h");
//       // Rendez_vous r2 =new Suivi(now1,heure,Type_rendez_vous.SUIVI,observation,1,Deroulement_seance.EN_PRESENTIEL,objectifs,"1h");
//          rendez_vous.add(r1);
//        //   rendez_vous.add(r2);
        dossier.setRendez_vous(rendez_vous);

        for (Rendez_vous  rend : rendez_vous) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("rendez-vouselement.fxml"));
            try {
                BorderPane hBox = fxmlLoader.load();
                RendezvousligneController cic = fxmlLoader.getController();
                cic.setData(rend);
                patientslay.getChildren().add(hBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


    }


}
