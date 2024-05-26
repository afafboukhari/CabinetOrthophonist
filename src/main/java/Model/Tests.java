package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Tests implements Serializable
{
    private ArrayList<AnamneseAdulte> anamneseAdultes;
    private ArrayList<AnamneseEnfant> anamneseEnfants;
    private ArrayList<Questionnaire> questionnaires;
    private ArrayList<QuestionnaireLibre> questionnaireLibre;
    private ArrayList<SerieExercices> serieExercices;

    public Tests()
    {
        anamneseAdultes = new ArrayList<AnamneseAdulte>();
        anamneseEnfants = new ArrayList<AnamneseEnfant>();
        questionnaires = new ArrayList<Questionnaire>();
        questionnaireLibre = new ArrayList<QuestionnaireLibre>();
        serieExercices = new ArrayList<SerieExercices>();
    }

    public ArrayList<AnamneseAdulte> getAnamneseAdultes() {
        return anamneseAdultes;
    }

    public ArrayList<AnamneseEnfant> getAnamneseEnfants() {
        return anamneseEnfants;
    }

    public ArrayList<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public ArrayList<QuestionnaireLibre> getQuestionnaireLibre() {
        return questionnaireLibre;
    }

    public ArrayList<SerieExercices> getSerieExercices() {
        return serieExercices;
    }

    public AnamneseAdulte getbyTitleAnamneseAdulte(String title) {
        for (AnamneseAdulte anamnese : anamneseAdultes) {
            if (Objects.equals(anamnese.getTitre(), title)) {
                return anamnese;
            }
        }
        return null;
    }

    public AnamneseEnfant getbyTitleAnamneseEnfant(String title) {
        for (AnamneseEnfant anamnese : anamneseEnfants) {
            if (Objects.equals(anamnese.getTitre(), title)) {
                return anamnese;
            }
        }
        return null;
    }

    public Questionnaire getbyTitleQuestionnaire(String title) {
        for (Questionnaire anamnese : questionnaires) {
            if (Objects.equals(anamnese.getTitre(), title)) {
                return anamnese;
            }
        }
        return null;
    }

    public QuestionnaireLibre getbyTitleQuestionnaireLibre(String title) {
        for (QuestionnaireLibre anamnese : questionnaireLibre) {
            if (Objects.equals(anamnese.getTitre(), title)) {
                return anamnese;
            }
        }
        return null;
    }

    public SerieExercices getbyTitleSerieExercices(String title) {
        for (SerieExercices anamnese : serieExercices) {
            if (Objects.equals(anamnese.getTitre(), title)) {
                return anamnese;
            }
        }
        return null;
    }

}
