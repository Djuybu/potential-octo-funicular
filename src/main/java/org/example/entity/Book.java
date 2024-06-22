package org.example.entity;

import java.util.ArrayList;

public class Book {
    private String name;
    private String author;
    private ArrayList<Chapter> chapterArrayList;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public Book() {
        this.name = null;
        this.author = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ArrayList<Chapter> getChapterArrayList() {
        return chapterArrayList;
    }
    public void setChapterArrayList(ArrayList<Chapter> chapterArrayList) {
        this.chapterArrayList = chapterArrayList;
    }
}
