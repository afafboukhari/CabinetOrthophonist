package Model;

import java.io.Serializable;

public class Tests implements Serializable
{
    private AnamneseAdulte[] anamneseAdultes;
    private int nbr1;
    private AnamneseEnfant[] anamneseEnfants;
    private int nbr2;
    private Questionnaire[] questionnaires;
    private int nbr3;
    private QuestionnaireLibre[] questionnaireLibre;
    private int nbr4;
    private SerieExercices[] serieExercices;
    private int nbr5;

    public AnamneseAdulte[] getAnamneseAdultes() {
        return anamneseAdultes;
    }

    public void setAnamneseAdultes(AnamneseAdulte anamneseAdultes) {
        this.anamneseAdultes[nbr1] = anamneseAdultes;
    }

    public AnamneseEnfant[] getAnamneseEnfants() {
        return anamneseEnfants;
    }

    public void setAnamneseEnfants(AnamneseEnfant anamneseEnfants) {
        this.anamneseEnfants[nbr2] = anamneseEnfants;
    }

    public Questionnaire[] getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(Questionnaire questionnaires) {
        this.questionnaires[nbr3] = questionnaires;
    }

    public QuestionnaireLibre[] getQuestionnaireLibre() {
        return questionnaireLibre;
    }

    public void setQuestionnaireLibre(QuestionnaireLibre questionnaireLibre) {
        this.questionnaireLibre[nbr4] = questionnaireLibre;
    }

    public SerieExercices[] getSerieExercices() {
        return serieExercices;
    }

    public void setSerieExercices(SerieExercices serieExercices) {
        this.serieExercices[nbr5] = serieExercices;
    }

    public void setNbrAnamneseAdulte(int nbr1) {
        this.nbr1 = nbr1;
    }

    public void setNbrAnamneseEnfant(int nbr2) {
        this.nbr2 = nbr2;
    }

    public void setNbrQCMQCU(int nbr3) {
        this.nbr3 = nbr3;
    }

    public void setNbrQuestionnaireLibre(int nbr4) {
        this.nbr4 = nbr4;
    }

    public void setNbrSerieExercice(int nbr5) {
        this.nbr5 = nbr5;
    }
}
