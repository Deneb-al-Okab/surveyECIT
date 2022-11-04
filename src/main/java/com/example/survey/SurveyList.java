package com.example.survey;

import java.util.ArrayList;

public class SurveyList {
    private ArrayList<Survey> surveyList;
    private int count = 0;

    public SurveyList(ArrayList<Survey> surveyList, int count) {
        this.surveyList = surveyList;
        this.count = count;
    }


}
