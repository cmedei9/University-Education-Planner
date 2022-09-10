package com.example.c196mobileapplication.DB;


import android.app.Application;

import androidx.room.RoomDatabase;

import com.example.c196mobileapplication.DAO.AssessmentsDAO;
import com.example.c196mobileapplication.DAO.CoursesDAO;
import com.example.c196mobileapplication.DAO.NotesDAO;
import com.example.c196mobileapplication.DAO.TermsDAO;
import com.example.c196mobileapplication.Entity.Assessments;
import com.example.c196mobileapplication.Entity.Courses;
import com.example.c196mobileapplication.Entity.Notes;
import com.example.c196mobileapplication.Entity.Terms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repo {

    private AssessmentsDAO mAssessmentsDAO;
    private CoursesDAO mCoursesDAO;
    private TermsDAO mTermsDAO;
    private NotesDAO mNotesDAO;
    private List<Terms> mAllTerms;
    private List<Assessments> mAllAssessments;
    private List<Courses> mAllCourses;
    private List <Notes> mAllNotes;
    private List<String> mAllCourseTitles;


    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService dbExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repo(Application application){
        Builder db=Builder.getDatabase(application);
        mAssessmentsDAO = db.assessmentsDAO();
        mCoursesDAO = db.coursesDAO();
        mTermsDAO = db.termsDAO();
    }

    public void insertNote(Notes notes) {
        dbExecutor.execute(() -> {
            mNotesDAO.insert(notes);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insertAssessment(Assessments assessments) {
        dbExecutor.execute(() -> {
            mAssessmentsDAO.insert(assessments);
        });

        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }

    public void insertTerm(Terms terms) {
        dbExecutor.execute(() -> {
            mTermsDAO.insert(terms);
        });

        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insertCourse(Courses courses) {
        dbExecutor.execute(() -> {
            mCoursesDAO.insert(courses);
        });


        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }

    public void updateNote(Notes notes){
        dbExecutor.execute(()->{
            mNotesDAO.update(notes);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateTerm(Terms terms){
        dbExecutor.execute(()->{
            mTermsDAO.update(terms);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateCourse(Courses courses){
        dbExecutor.execute(()->{
            mCoursesDAO.update(courses);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateAssessments(Assessments assessments){
        dbExecutor.execute(()->{
            mAssessmentsDAO.update(assessments);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Notes> getAllNotes(){
        dbExecutor.execute(()->{
            mAllNotes=mNotesDAO.getAllNotes();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllNotes;
    }

    public List<Terms> getAllTerms(){
        dbExecutor.execute(()->{
            mAllTerms=mTermsDAO.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }

    public List<Courses> getAllCourses(){
        dbExecutor.execute(()->{
            mAllCourses=mCoursesDAO.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    public List<String> getCourseTitles(){
        dbExecutor.execute(()->{
            mAllCourseTitles=mCoursesDAO.getCourseTitles();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourseTitles;
    }


    public List<Assessments> getAllAssessments(){
        dbExecutor.execute(()->{
            mAllAssessments=mAssessmentsDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public void delete(Notes notes){
        dbExecutor.execute(()->{
            mNotesDAO.delete(notes);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Assessments assessments){
        dbExecutor.execute(()->{
            mAssessmentsDAO.delete(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Terms terms){
        dbExecutor.execute(()->{
            mTermsDAO.delete(terms);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Courses courses){
        dbExecutor.execute(()->{
            mCoursesDAO.delete(courses);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
