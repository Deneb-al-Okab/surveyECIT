package com.example.survey;

import java.util.ArrayList;

public class Question {
    private String name;
    private String category;
    private ArrayList<String> answers = new ArrayList<>();

    public Question(String name, String category, ArrayList<String> answers) {
        this.name = name;
        this.category = category;
        this.answers = answers;
    }
}
