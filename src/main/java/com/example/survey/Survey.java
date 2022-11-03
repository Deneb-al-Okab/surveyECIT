package com.example.survey;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;

public class Survey {
    private int id;
    private String mail;
    private String category;
    private String name;
    private String description;
    private DateTime publishDate = new DateTime();
    private DateTime endingDdte = new DateTime();
    private ArrayList<Question> questions = new ArrayList<Question>();

    public Survey(int id, String mail, String category, String name, String description, DateTime publishDate, DateTime endingDdte, ArrayList<Question> questions) {
        this.id = id;
        this.mail = mail;
        this.category = category;
        this.name = name;
        this.description = description;
        this.publishDate = publishDate;
        this.endingDdte = endingDdte;
        this.questions = questions;
    }

    public Survey(int id, String mail, String category, String name, String description, DateTime publishDate, DateTime endingDdte) {
        this.id = id;
        this.mail = mail;
        this.category = category;
        this.name = name;
        this.description = description;
        this.publishDate = publishDate;
        this.endingDdte = endingDdte;
    }
}
