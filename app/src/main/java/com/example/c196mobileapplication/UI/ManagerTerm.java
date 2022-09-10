package com.example.c196mobileapplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.example.c196mobileapplication.DB.Repo;
import com.example.c196mobileapplication.Entity.Courses;
import com.example.c196mobileapplication.Entity.Terms;
import com.example.c196mobileapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ManagerTerm extends AppCompatActivity {

    private DatePickerDialog startDpd;
    private DatePickerDialog endDpd;
    private Button startDateButton;
    private Button endDateButton;
    private ListView courseListView;

    EditText termName;

    Terms selectedTerm;

    int termID;
    String name;
    String start;
    String end;

    Repo repo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term_ui);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        startDatePicker();
        endDatePicker();
        startDateButton = findViewById(R.id.startDate);
        endDateButton = findViewById(R.id.endDate);
        termName = findViewById(R.id.termName);
        courseListView = findViewById(R.id.courseList);

        name = getIntent().getStringExtra("termName");
        start = getIntent().getStringExtra("startDate");
        end = getIntent().getStringExtra("endDate");
        termID = getIntent().getIntExtra("termID", -1);

        repo = new Repo(getApplication());

        termName.setText(name);
        startDateButton.setText(start);
        endDateButton.setText(end);


        List<Courses> allCourses = repo.getAllCourses();
        List<String> courseList = new ArrayList<>();


        for(Courses c : allCourses){
            if(c.getAssociatedTerm()==termID) {
                courseList.add(c.getCourseTitle());
            }
        }

        courseListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courseList));
        courseListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        List<Courses> selectedCourses= new ArrayList<>();
        //selectedCourses.add(courseListView)

    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
    List<Terms> allTerms = repo.getAllTerms();
    List<Courses> allCourses = repo.getAllCourses();

       switch (menuItem.getItemId()) {

           case R.id.delete:

               for(Terms t : allTerms){
                   if(t.getTermID() == termID) selectedTerm = t;
               }

               int i = 0;
               for (Courses c : allCourses) {
                   if (c.getAssociatedTerm() == selectedTerm.getTermID())
                       ++i;
               }

               if (i == 0){
                   repo.delete(selectedTerm);
                   Intent returnBack = new Intent(ManagerTerm.this, MainTermView.class);
                   startActivity(returnBack);
               }

               else{
                   Toast.makeText(ManagerTerm.this, "ERROR: Cannot delete a term with associated courses.", Toast.LENGTH_LONG).show();
               }
       }
    return super.onOptionsItemSelected(menuItem);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        if(name != null) {
            getMenuInflater().inflate(R.menu.term_detail, menu);
            return true;
        }
        else{
            return false;
        }
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

    public void addButton(View view) {

        Terms term;

        if(termID == -1){
            int newID = repo.getAllTerms().get(repo.getAllTerms().size() - 1).getTermID() + 1;
            Terms terms = new Terms(newID, termName.getText().toString(), (String) startDateButton.getText(), (String) endDateButton.getText());
            repo.insertTerm(terms);
        }

        else{
            Terms terms = new Terms(termID, termName.getText().toString(), (String) startDateButton.getText(), (String) endDateButton.getText());
            repo.updateTerm(terms);
        }

        Intent returnBack = new Intent(ManagerTerm.this, MainTermView.class);
        startActivity(returnBack);
    }


}