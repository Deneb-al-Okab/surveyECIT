package com.example.survey;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

public class Survey {
    // Due constructor separati con o senza le questions
    private int id;
    private String mail;
    private String category;
    private String name;
    private String description;
    private LocalDateTime publishDate;
    private LocalDateTime endingDate;
    private ArrayList<Question> questions = new ArrayList<Question>();

    public Survey(int id, String mail, String category, String name, String description, LocalDateTime publishDate, LocalDateTime endingDate, ArrayList<Question> questions) {
        this.id = id;
        this.mail = mail;
        this.category = category;
        this.name = name;
        this.description = description;
        this.publishDate = publishDate;
        this.endingDate = endingDate;
        this.questions = questions;
    }

    public Survey(int id, String mail, String category, String name, String description, LocalDateTime publishDate, LocalDateTime endingDate) {
        this.id = id;
        this.mail = mail;
        this.category = category;
        this.name = name;
        this.description = description;
        this.publishDate = publishDate;
        this.endingDate = endingDate;
    }

}
