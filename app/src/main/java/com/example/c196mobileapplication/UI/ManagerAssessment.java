package com.example.c196mobileapplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.c196mobileapplication.DB.Repo;
import com.example.c196mobileapplication.Entity.Assessments;
import com.example.c196mobileapplication.Entity.Courses;
import com.example.c196mobileapplication.Entity.Terms;
import com.example.c196mobileapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ManagerAssessment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatePickerDialog startDpd;
    private DatePickerDialog endDpd;
    private Button startDateButton;
    private Button endDateButton;
    private Spinner coursesSpinner;
    private Spinner typeSpinner;
    private ArrayAdapter courseAdapter;

    EditText assessmentName;

    int courseSpinnerID;
    int assessmentID;
    String type;
    String name;
    String start;
    String end;
    String courseName;

    Repo repo;

    Assessments selectedAssessment;

    List<Courses> allCourses;
    List<String> courseList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        startDatePicker();
        endDatePicker();

        startDateButton = findViewById(R.id.startDate);
        endDateButton = findViewById(R.id.endDate);
        assessmentName = findViewById(R.id.courseName);
        coursesSpinner = findViewById(R.id.coursesSpinner);
        typeSpinner = findViewById(R.id.typeSpinner);

        name = getIntent().getStringExtra("assessment");
        start = getIntent().getStringExtra("startDate");
        end = getIntent().getStringExtra("endDate");
        assessmentID = getIntent().getIntExtra("assessmentID", -1);
        type = getIntent().getStringExtra("type");
        courseName = getIntent().getStringExtra("courseName");

        repo = new Repo(getApplication());

        List<Courses> coursesList = repo.getAllCourses();
        allCourses = coursesList;

        List<String> courseListString = new ArrayList<>();
        courseList = courseListString;

        assessmentName.setText(name);
        startDateButton.setText(start);
        endDateButton.setText(end);

        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this, R.array.assessmentTypes, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setOnItemSelectedListener(this);


        List<Courses> allCourses = repo.getAllCourses();
        List<String> courseList = new ArrayList<>();

        for(Courses c : allCourses){
            courseList.add(c.getCourseTitle());
        }

        courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseList);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursesSpinner.setAdapter(courseAdapter);
        coursesSpinner.setOnItemSelectedListener(this);

    }






    private void startDatePicker() {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = startDateString(day, month, year);
                startDateButton.setText(date);
            }
        };

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        startDpd = new DatePickerDialog(this, listener, year, month, day);

    }

    private String startDateString(int day, int month, int year) {
        return month + "/" + day + "/" + year;
    }

    private void endDatePicker() {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = endDateString(day, month, year);
                endDateButton.setText(date);
            }
        };

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        endDpd = new DatePickerDialog(this, listener, year, month, day);


    }

    private String endDateString(int day, int month, int year) {
        return month + "/" + day + "/" + year;
    }

    public void openStartDatePicker(View view){
        startDpd.show();
    }
    public void openEndDatePicker(View view){
        endDpd.show();
    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selection = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplication(), selection, Toast.LENGTH_LONG);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public boolean onCreateOptionsMenu(Menu menu){
        if(name != null) {
            getMenuInflater().inflate(R.menu.assessment_menu, menu);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        List<Assessments> allAssessments = repo.getAllAssessments();


        switch (item.getItemId()) {

            case R.id.delete:

                for (Assessments a : allAssessments) {
                    if (a.getAssessmentID() == assessmentID) selectedAssessment = a;

                }

                    repo.delete(selectedAssessment);
                    Intent returnBack = new Intent(ManagerAssessment.this, MainAssessmentView.class);
                    startActivity(returnBack);
                }
                return super.onOptionsItemSelected(item);
        }



    public void addButton(View view) {

        for (Courses c : allCourses){
            if (coursesSpinner.getSelectedItem().toString().equals(c.getCourseTitle())){
                courseSpinnerID = c.getCourseID();
            }
        }

        if(assessmentID == -1){
            int newAssessmentID = repo.getAllAssessments().get(repo.getAllAssessments().size() - 1).getAssessmentID() + 1;

            Assessments assessment = new Assessments(newAssessmentID, courseSpinnerID, typeSpinner.getSelectedItem().toString(), assessmentName.getText().toString(), (String) startDateButton.getText(), (String) endDateButton.getText());
            repo.insertAssessment(assessment);
        }

        else{
            Assessments assessment = new Assessments(assessmentID, courseSpinnerID, typeSpinner.getSelectedItem().toString(), assessmentName.getText().toString(), (String) startDateButton.getText(), (String) endDateButton.getText());
            repo.updateAssessments(assessment);
        }

        Intent returnBack = new Intent(ManagerAssessment.this, MainAssessmentView.class);
        startActivity(returnBack);
    }

}