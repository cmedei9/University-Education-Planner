package com.example.c196mobileapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.c196mobileapplication.DB.Repo;
import com.example.c196mobileapplication.Entity.Assessments;
import com.example.c196mobileapplication.R;

import java.util.List;

public class MainAssessmentView extends AppCompatActivity {

    private Repo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        TextView assessmentsHeader = findViewById(R.id.textView8);
        SpannableString text = new SpannableString(assessmentsHeader.getText());
        text.setSpan(new UnderlineSpan(), 0, assessmentsHeader.getText().length(), 0);
        assessmentsHeader.setText(text);

        repo = new Repo(getApplication());
        List<Assessments> assessmentsList = repo.getAllAssessments();
        final AssessmentsAdapter assessmentsAdapter = new AssessmentsAdapter(this);
        recyclerView.setAdapter(assessmentsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentsAdapter.setAssessments(assessmentsList);
    }

    public void addAssessment(View view) {
        Intent addAssessmentScreen = new Intent(MainAssessmentView.this, ManagerAssessment.class);
        startActivity(addAssessmentScreen);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent back = new Intent(getApplicationContext(), MainActivityUI.class);
        startActivityForResult(back, 0);
        return true;
    }

}