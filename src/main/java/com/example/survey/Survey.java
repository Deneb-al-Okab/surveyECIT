package com.example.survey;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Survey {
    // Due constructor separati con o senza le questions
    private int id;
    private String mail;
    private String category;
    private String name;
    private String description;
    private Calendar publishDate;
    private Calendar endingDate;
    private String publishDateS;
    private String endingDateS;
    private ArrayList<Question> questions = new ArrayList<Question>();
    public void setCalendars() throws ParseException {
        this.publishDate = Calendar.getInstance();
        this.endingDate = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss", Locale.ENGLISH);
        this.publishDate.setTime(sdf.parse(this.publishDateS));
        this.endingDate.setTime(sdf.parse(this.endingDateS));
    }
    public Survey() {
    }

    public Survey(int id, String mail, String category, String name, String description, Calendar publishDate, Calendar endingDate, ArrayList<Question> questions) {
        this.id = id;
        this.mail = mail;
        this.category = category;
        this.name = name;
        this.description = description;
        this.publishDate = publishDate;
        this.endingDate = endingDate;
        this.questions = questions;
    }

    public Survey(int id, String mail, String category, String name, String description, Calendar publishDate, Calendar endingDate) {
        this.id = id;
        this.mail = mail;
        this.category = category;
        this.name = name;
        this.description = description;
        this.publishDate = publishDate;
        this.endingDate = endingDate;
    }

    public Survey(String mail, String category, String name, String description, Calendar publishDate, Calendar endingDate) {
        this.mail = mail;
        this.category = category;
        this.name = name;
        this.description = description;
        this.publishDate = publishDate;
        this.endingDate = endingDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Calendar publishDate) {
        this.publishDate = publishDate;
    }

    public Calendar getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Calendar endingDate) {
        this.endingDate = endingDate;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
