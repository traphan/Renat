package com.example.ren95.geoqize;

/**
 * Created by ren95 on 27.07.2016.
 */
public class Question {
    private int textQuestionId;
    private boolean answerTrue;
    public Question(int textQuestion,boolean ansTrue){
        textQuestionId=textQuestion;
        answerTrue=ansTrue;
    }

    public int getTextQuestionId() {
        return textQuestionId;
    }

    public void setTextQuestionId(int textQuestionId) {
        this.textQuestionId = textQuestionId;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;
    }
}
