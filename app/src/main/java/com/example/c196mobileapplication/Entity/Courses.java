package com.example.c196mobileapplication.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.c196mobileapplication.DAO.CoursesDAO;

import java.time.LocalDate;

@Entity(tableName = "courses")
public class Courses {
    @PrimaryKey(autoGenerate = true)
    private int courseID;
    @NonNull
    private String courseTitle;
    @NonNull
    private String startDate;
    @NonNull
    private String endDate;
    @NonNull
    private String status;
    @NonNull
    private String instructorName;
    @NonNull
    private String instructorPhone;
    @NonNull
    private String instructorEmail;
    @NonNull
    private int associatedTerm;


    public Courses(int courseID, String courseTitle, String startDate, String endDate, String status, String instructorName, String instructorPhone, String instructorEmail, int associatedTerm) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.instructorName = instructorName;
        this.instructorPhone = instructorPhone;
        this.instructorEmail = instructorEmail;
        this.associatedTerm = associatedTerm;
    }


    @Override
    public String toString() {
        return "Courses{" +
                "courseID='" + courseID +
                ", courseTitle='" + courseTitle + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", status='" + status + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", instructorPhone='" + instructorPhone + '\'' +
                ", instructorEmail='" + instructorEmail + '\'' +
                ", associatedTerm='" + associatedTerm + '\'' +
                '}';
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public int getAssociatedTerm() {
        return associatedTerm;
    }

    public void setAssociatedTerm(int associatedTerm) {
        this.associatedTerm = associatedTerm;
    }
}
