package com.example.c196mobileapplication.DB;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.c196mobileapplication.DAO.AssessmentsDAO;
import com.example.c196mobileapplication.DAO.CoursesDAO;
import com.example.c196mobileapplication.DAO.TermsDAO;
import com.example.c196mobileapplication.Entity.Assessments;
import com.example.c196mobileapplication.Entity.Courses;
import com.example.c196mobileapplication.Entity.Terms;


@Database(entities = {Assessments.class, Courses.class, Terms.class}, version = 8, exportSchema = false)
public abstract class Builder extends RoomDatabase {

    public abstract TermsDAO termsDAO();
    public abstract CoursesDAO coursesDAO();
    public abstract AssessmentsDAO assessmentsDAO();

    private static volatile com.example.c196mobileapplication.DB.Builder INSTANCE;

    static com.example.c196mobileapplication.DB.Builder getDatabase(final Context context){

        if(INSTANCE == null){

            synchronized (com.example.c196mobileapplication.DB.Builder.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), com.example.c196mobileapplication.DB.Builder.class, "Database.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
