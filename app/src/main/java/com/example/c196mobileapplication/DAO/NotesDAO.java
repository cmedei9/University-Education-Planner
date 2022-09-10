package com.example.c196mobileapplication.DAO;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196mobileapplication.Entity.Notes;
import com.example.c196mobileapplication.Entity.Terms;

import java.util.List;

public interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Notes notes);

    @Update
    void update(Notes notes);

    @Delete
    void delete(Notes notes);

    @Query("SELECT * FROM notes ORDER BY noteID ASC")
    List<Notes> getAllNotes();
}
