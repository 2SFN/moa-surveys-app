package de.fhswf.moa.surveys.model;

import androidx.annotation.NonNull;

public class EndQuestion extends Question {
    //TODO New QuestionType .End ?
public static final QuestionType QUESTION_TYPE = QuestionType.INFO;
    @NonNull
    @Override
    public QuestionType getType() {
        return QUESTION_TYPE;
    }
    public EndQuestion(){

    }
    public EndQuestion(String id, String title){
        this.setId(id);
        this.setTitle(title);
    }
}

