package com.example.c196mobileapplication.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196mobileapplication.Entity.Courses;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CoursesDAO {
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insert(Courses courses);

        @Update
        void update(Courses courses);

        @Delete
        void delete(Courses courses);

        @Query("SELECT * FROM courses ORDER BY courseID ASC")
        List<Courses> getAllCourses();

        @Query("Select courseTitle FROM courses ORDER BY courseTitle ASC")
        List<String> getCourseTitles();



}
