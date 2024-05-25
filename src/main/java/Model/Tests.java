package Model;

import java.io.Serializable;

public class Tests implements Serializable
{
    private AnamneseAdulte[] anamneseAdultes;
    private AnamneseEnfant[] anamneseEnfants;
    private Questionnaire[] questionnaires;
    private QuestionnaireLibre questionnaireLibre;
    private SerieExercices[] serieExercices;

    public AnamneseAdulte[] getAnamneseAdultes() {
        return anamneseAdultes;
    }

    public void setAnamneseAdultes(AnamneseAdulte[] anamneseAdultes) {
        this.anamneseAdultes = anamneseAdultes;
    }

    public AnamneseEnfant[] getAnamneseEnfants() {
        return anamneseEnfants;
    }

    public void setAnamneseEnfants(AnamneseEnfant[] anamneseEnfants) {
        this.anamneseEnfants = anamneseEnfants;
    }

    public Questionnaire[] getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(Questionnaire[] questionnaires) {
        this.questionnaires = questionnaires;
    }

    public QuestionnaireLibre getQuestionnaireLibre() {
        return questionnaireLibre;
    }

    public void setQuestionnaireLibre(QuestionnaireLibre questionnaireLibre) {
        this.questionnaireLibre = questionnaireLibre;
    }

    public SerieExercices[] getSerieExercices() {
        return serieExercices;
    }

    public void setSerieExercices(SerieExercices[] serieExercices) {
        this.serieExercices = serieExercices;
    }
}
