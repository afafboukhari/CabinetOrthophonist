package Model;

import java.io.Serial;
import java.io.Serializable;

public abstract class Questionnaire implements Serializable
{
    private String Question[];

    public String[] getQuestion()
    {
        return Question;
    }

    public void setQuestion(String[] question)
    {
        Question = question;
    }
}
