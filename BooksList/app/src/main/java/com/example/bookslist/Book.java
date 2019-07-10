package com.example.bookslist;

import android.support.annotation.NonNull;

import java.util.Arrays;

public class Book {

    private String title;
    private String[] authors;
    private String publishedDate;
    private String description;
    private int pages;
    private String thumbnailUrl;
    private double averageRating;
    private String previewLink;

    public Book(String title, String[] authors, String publishedDate, String description,
                int pages, String thumbnailUrl, double averageRating, String previewLink) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.description = description;
        this.pages = pages;
        this.thumbnailUrl = thumbnailUrl;
        this.averageRating = averageRating;
        this.previewLink = previewLink;
    }

    public String getTitle() {
        return title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public int getPages() {
        return pages;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("title: %s\n" +
                "authors: %s\n" +
                "publishedDate: %s\n" +
                "pages: %d\n" +
                "thumbnailUrl: %s\n" +
                "averageRating: %f\n" +
                "previewLink: %s",
                getTitle(), Arrays.toString(getAuthors()), getPublishedDate(), getPages(), getThumbnailUrl(), getAverageRating(), getPreviewLink());
    }
}
