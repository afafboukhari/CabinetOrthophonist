package Model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObservationClinique implements Serializable
{
    private Patient patient;
    private String text;

    ObservationClinique()
    {}

    ObservationClinique(Patient patient,String text)
    {
        this.patient = patient;
        this.text = text;
    }

    public void saveObservation(String filePath) throws IOException {
        String observationString = patient.getNom() + "," + text + "\n";
        FileOutputStream fileOut = new FileOutputStream(filePath, true); // Append mode
        byte[] observationBytes = observationString.getBytes();
        fileOut.write(observationBytes);
        fileOut.close();
        System.out.println("Observation appended successfully!");
    }

}
