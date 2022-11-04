package com.example.survey;

public class GetAnswerClass {
    private String username;
    private int id_survey;
    private int[] id_questionanswer;

    public GetAnswerClass(String username, int id_survey, int[] id_questionanswer) {
        this.username = username;
        this.id_survey = id_survey;
        this.id_questionanswer = id_questionanswer;
    }

    public String getUsername() {
        return username;
    }

    public int getId_survey() {
        return id_survey;
    }

    public int[] getId_questionanswer() {
        return id_questionanswer;
    }
}
