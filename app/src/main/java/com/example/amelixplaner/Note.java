package com.example.amelixplaner;

public class Note {
    private String title;
    private String content;
    private boolean isImportant;

    public Note(String title, String content, boolean isImportant) {
        this.title = title;
        this.content = content;
        this.isImportant = isImportant;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isImportant() {
        return isImportant;
    }
}
