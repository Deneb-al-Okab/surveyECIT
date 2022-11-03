package com.example.survey;

import java.util.ArrayList;

public class Question {
    private int id;
    private String name;
    private String category;
    private ArrayList<String> answers = new ArrayList<>();

    public Question(int id,String name, String category, ArrayList<String> answers) {
        this.name = name;
        this.category = category;
        this.answers = answers;
    }

    public Question() {
    }

    public Question(int id, String name, ArrayList<String> answers) {
        this.name = name;
        this.category = category;
        this.answers = answers;
    }
}
