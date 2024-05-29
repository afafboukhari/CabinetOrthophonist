package Model;

public class ReponseAnamnese
{
    private Patient patient;
    private String[] Question;
    private String[] Answer;

    public ReponseAnamnese(Patient patient, String[] Question, String[] Answer)
    {
        this.patient = patient;
        this.Question = Question;
        this.Answer = Answer;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String[] getQuestion() {
        return Question;
    }

    public void setQuestion(String[] question) {
        Question = question;
    }

    public String[] getAnswer() {
        return Answer;
    }

    public void setAnswer(String[] answer) {
        Answer = answer;
    }
}
