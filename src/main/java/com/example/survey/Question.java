package com.example.survey;

import java.util.ArrayList;

public class Question {
    private int id;
    private String text;
    private String category;
    private ArrayList<Answer> answers = new ArrayList<>();

    public Question(int id,String text, String category, ArrayList<Answer> answers) {
        this.id = id;
        this.text = text;
        this.category = category;
        this.answers = answers;
    }

    public Question() {
    }

    public Question(int id, String name, ArrayList<Answer> answers) {
        this.id = id;
        this.text = name;
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
