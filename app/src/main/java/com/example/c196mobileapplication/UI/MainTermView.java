package com.example.c196mobileapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.c196mobileapplication.DB.Repo;
import com.example.c196mobileapplication.Entity.Terms;
import com.example.c196mobileapplication.R;

import java.util.List;

public class MainTermView extends AppCompatActivity {

    private Repo repo;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView termsHeader = findViewById(R.id.textView8);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);


        SpannableString text = new SpannableString(termsHeader.getText());
        text.setSpan(new UnderlineSpan(), 0, termsHeader.getText().length(), 0);
        termsHeader.setText(text);


        repo = new Repo(getApplication());

        List<Terms> termsList = repo.getAllTerms();

        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(termsList);

        DividerItemDecoration dvd = new DividerItemDecoration(recyclerView.getContext(), 1);
        recyclerView.addItemDecoration(dvd);
    }





/*    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.course_detail, menu);
        return true;
    }*/

    public boolean onOptionsItemSelected(MenuItem menuItem){

        switch(menuItem.getItemId()) {

            case android.R.id.home:
                this.finish();
                return true;

            case R.id.share:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Text from the note field");
                intent.putExtra(Intent.EXTRA_TITLE, "Message Title");
                intent.setType("text/plain");
                Intent share = Intent.createChooser(intent, null);
                startActivity(share);
                return true;

            case R.id.notify:
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void addTerms(View view) {
        Intent addTermScreen = new Intent(MainTermView.this, ManagerTerm.class);
        startActivity(addTermScreen);
    }

}