package com.example.c196mobileapplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c196mobileapplication.DB.Repo;
import com.example.c196mobileapplication.Entity.Assessments;
import com.example.c196mobileapplication.Entity.Courses;
import com.example.c196mobileapplication.Entity.Terms;
import com.example.c196mobileapplication.R;

public class MainActivityUI extends AppCompatActivity {

    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(flag==0) {
            populateSampleData();
            flag=flag+1;
        }

    }

    public void populateSampleData(){

            Repo repo = new Repo(getApplication());

            Terms terms = new Terms(1, "Term 1", "1/1/2022", "6/1/2022");
            Terms terms2 = new Terms(2, "Term 2", "2/1/2022", "7/1/2022");
            Courses courses = new Courses(1, "Math", "12/12/22", "1/2/23", "In-Progress", "John Teacher", "123-456-7890", "email@wgu.edu", 1);
            Courses course = new Courses(2, "English", "12/12/22", "1/2/23", "Dropped", "Instructor Brown", "123-456-7890", "email@wgu.edu", 1);
            Assessments assessment = new Assessments(1, 1, "Objective", "Objective Assessment", "1/1/2022", "1/1/2022");
            Assessments assessmentt = new Assessments(1, 1, "Performance", "Performance Assessment", "1/1/2022", "2/2/2022");

            repo.insertTerm(terms);
            repo.insertTerm(terms2);
            repo.insertCourse(courses);
            repo.insertCourse(course);
            repo.insertAssessment(assessment);
            repo.insertAssessment(assessmentt);

    }

    public void termsButton(View view) {
        Intent terms = new Intent(MainActivityUI.this, MainTermView.class);
        startActivity(terms);
    }

    public void coursesButton(View view) {
        Intent courses = new Intent(MainActivityUI.this, MainCourseView.class);
        startActivity(courses);
    }

    public void assessmentsButton(View view) {
        Intent assessments = new Intent(MainActivityUI.this, MainAssessmentView.class);
        startActivity(assessments);
    }

    public void notesButton(View view) {
        Intent notes = new Intent(MainActivityUI.this, MainNoteView.class);
        startActivity(notes);
    }
}