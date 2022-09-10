package com.example.c196mobileapplication.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196mobileapplication.Entity.Assessments;

import java.util.List;

@Dao
public interface AssessmentsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessments assessments);

    @Update
    void update(Assessments assessments);

    @Delete
    void delete(Assessments assessments);

    @Query("SELECT * FROM assessments ORDER BY assessmentID ASC")
    List<Assessments> getAllAssessments();
}
