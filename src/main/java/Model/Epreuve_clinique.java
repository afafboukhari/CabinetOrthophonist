package Model;

import java.io.Serializable;

public class Epreuve_clinique  implements Serializable {


    private String[] Observations_cliniques;
    private Tests[] tests;

    public Epreuve_clinique(String[] observations_cliniques, Tests[] tests)
    {

        Observations_cliniques = observations_cliniques;
        this.tests = tests;


    }

    public String[] getObservations_cliniques()
    {
        return Observations_cliniques;
    }

    public void setObservations_cliniques(String[] observations_cliniques) {
        Observations_cliniques = observations_cliniques;
    }

    public Tests[] getTests()
    {
        return tests;
    }

    public void setTests(Tests[] tests) {
        this.tests = tests;
    }
}
