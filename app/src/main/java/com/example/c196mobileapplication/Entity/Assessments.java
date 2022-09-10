package com.example.c196mobileapplication.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "assessments")
public class Assessments {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    @NonNull
    public int associatedCourse;
    @NonNull
    public String type;
    @NonNull
    public String assessment;
    @NonNull
    public String startDate;
    @NonNull
    public String endDate;

    public Assessments(int assessmentID, int associatedCourse, String type, String assessment, String startDate, String endDate) {
        this.assessmentID = assessmentID;
        this.associatedCourse = associatedCourse;
        this.type = type;
        this.assessment = assessment;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    @Override
    public String toString() {
        return "Assessments{" +
                "assessmentID=" + assessmentID +
                ", associatedCourse=" + associatedCourse +
                ", type='" + type + '\'' +
                ", assessment='" + assessment + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }


    public int getAssociatedCourse() {
        return associatedCourse;
    }

    public void setAssociatedCourse(int associatedCourse) {
        this.associatedCourse = associatedCourse;
    }

}
