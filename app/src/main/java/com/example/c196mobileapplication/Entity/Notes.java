package com.example.c196mobileapplication.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Notes {
    @PrimaryKey
    private int noteID;
    @NonNull
    private String associatedCourse;
    @NonNull
    private String noteName;
    @NonNull
    private String noteDescription;

    public Notes(int noteID, String associatedCourse, String noteName, String noteDescription) {
        this.noteID = noteID;
        this.associatedCourse = associatedCourse;
        this.noteName = noteName;
        this.noteDescription = noteDescription;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "noteID=" + noteID +
                ", associatedCourse='" + associatedCourse + '\'' +
                ", noteName='" + noteName + '\'' +
                ", noteDescription='" + noteDescription + '\'' +
                '}';
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getAssociatedCourse() {
        return associatedCourse;
    }

    public void setAssociatedCourse(String associatedCourse) {
        this.associatedCourse = associatedCourse;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }
}
