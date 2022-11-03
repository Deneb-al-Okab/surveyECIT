package com.example.survey;

import java.util.ArrayList;

public class Question {
    private int id;
    private String name;
    private String category;
    private ArrayList<String> answers = new ArrayList<>();

    public Question(int id,String name, String category, ArrayList<String> answers) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.answers = answers;
    }

    public Question() {
    }

    public Question(int id, String name, ArrayList<String> answers) {
        this.id = id;
        this.name = name;
        this.answers = answers;
    }
/*    public void addAnswer(String answer){
        this.answers.add(answer);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }*/
}
