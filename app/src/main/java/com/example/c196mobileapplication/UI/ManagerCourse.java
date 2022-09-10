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
import android.widget.ListView;
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

public class ManagerCourse extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private DatePickerDialog startDpd;
    private DatePickerDialog endDpd;
    private Button startDateButton;
    private Button endDateButton;
    private Spinner termSpinner;
    private Spinner statusSpinner;
    private ArrayAdapter termsAdapter;
    private ListView assessmentListView;
    EditText courseTitleText;
    EditText instructorNameText;
    EditText instructorEmailText;
    EditText instructorPhoneText;

    Repo repo;

    int courseID;
    int termSpinnerID;
    String courseTitle;
    String instructorName;
    String instructorEmail;
    String instructorPhone;
    String startDate;
    String endDate;
    String courseStatus;
    int associateTerm;

    Courses selectedCourse;


    List<Terms> allTerms;
    List<String> termList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        startDatePicker();
        endDatePicker();

        repo = new Repo(getApplication());

        List<Terms> terms = repo.getAllTerms();
        allTerms = terms;

        List<String> termListString = new ArrayList<>();
        termList = termListString;

        statusSpinner = findViewById(R.id.statusSpinner);
        startDateButton = findViewById(R.id.startDate);
        endDateButton = findViewById(R.id.endDate);
        courseTitleText = findViewById(R.id.courseName);
        instructorNameText = findViewById(R.id.instructorName);
        instructorEmailText = findViewById(R.id.instructorEmail);
        instructorPhoneText = findViewById(R.id.instructorPhone);
        termSpinner = findViewById(R.id.associatedTerm);
        assessmentListView = findViewById(R.id.assessmentList);

        courseID = getIntent().getIntExtra("courseID", -1);
        courseTitle = getIntent().getStringExtra("courseTitle");
        startDate = getIntent().getStringExtra("startDate");
        endDate = getIntent().getStringExtra("endDate");
        courseStatus = getIntent().getStringExtra("status");
        instructorName = getIntent().getStringExtra("instructorName");
        instructorPhone = getIntent().getStringExtra("instructorPhone");
        instructorEmail = getIntent().getStringExtra("instructorEmail");
        associateTerm = getIntent().getIntExtra("associateTerm", -1);

        courseTitleText.setText(courseTitle);
        instructorNameText.setText(instructorName);
        instructorEmailText.setText(instructorEmail);
        instructorPhoneText.setText(instructorPhone);
        startDateButton.setText(startDate);
        endDateButton.setText(endDate);


        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this, R.array.courseStatus, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusAdapter);
        statusSpinner.setOnItemSelectedListener(this);

        for (Terms t : allTerms) {
            termList.add(t.getTermName());
        }

        termsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, termList);
        termsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termSpinner.setAdapter(termsAdapter);
        termSpinner.setOnItemSelectedListener(this);

        List<String> assessmentList = new ArrayList<>();
        for (Assessments a : repo.getAllAssessments()) {
            if (a.associatedCourse == courseID) {
                assessmentList.add(a.getAssessment());
            }
        }

        assessmentListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, assessmentList));
        assessmentListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

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

    public void openStartDatePicker(View view) {
        startDpd.show();
    }

    public void openEndDatePicker(View view) {
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

    public void addButton(View view) {

        for (Terms t : allTerms) {
            if (termSpinner.getSelectedItem().toString().equals(t.getTermName())) {
                termSpinnerID = t.getTermID();
            }
        }

        if (courseID == -1) {
            int newCourseID = repo.getAllCourses().get(repo.getAllCourses().size() - 1).getCourseID() + 1;

            Courses course = new Courses(newCourseID, courseTitleText.getText().toString(), (String) startDateButton.getText(), (String) endDateButton.getText(), statusSpinner.getSelectedItem().toString(), instructorNameText.getText().toString(), instructorEmailText.getText().toString(), instructorPhoneText.getText().toString(), termSpinnerID);
            repo.insertCourse(course);
        } else {
            Courses course = new Courses(courseID, courseTitleText.getText().toString(), (String) startDateButton.getText(), (String) endDateButton.getText(), statusSpinner.getSelectedItem().toString(), instructorNameText.getText().toString(), instructorEmailText.getText().toString(), instructorPhoneText.getText().toString(), termSpinnerID);
            repo.updateCourse(course);
        }

        Intent returnBack = new Intent(ManagerCourse.this, MainCourseView.class);
        startActivity(returnBack);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        if(courseTitle != null) {
            getMenuInflater().inflate(R.menu.course_menu, menu);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        List<Courses> allCourses = repo.getAllCourses();
        List<Assessments> allAssessments = repo.getAllAssessments();

        switch (item.getItemId()) {

            case R.id.delete:

                for (Courses c : allCourses) {
                    if (c.getCourseID() == courseID) selectedCourse = c;
                }

                int i = 0;
                for (Assessments a : allAssessments) {
                    if (a.getAssociatedCourse() == selectedCourse.getCourseID())
                        ++i;
                }

                if (i == 0) {

                    repo.delete(selectedCourse);
                    Intent returnBack = new Intent(ManagerCourse.this, MainTermView.class);
                    startActivity(returnBack);
                } else {
                    Toast.makeText(ManagerCourse.this, "ERROR: Cannot delete a course with associated assessments.", Toast.LENGTH_LONG).show();
                }
        }
        return super.onOptionsItemSelected(item);
    }
}