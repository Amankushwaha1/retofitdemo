package com.example.retofitdemo.model.children;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("subreddit")
    @Expose
    private String subreddit;

    @SerializedName("saved")
    @Expose
    private boolean saved;

    @SerializedName("title")
    @Expose
    private String title;

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Data{" +
                "subreddit='" + subreddit + '\'' +
                ", saved=" + saved +
                ", title='" + title + '\'' +
                '}';
    }
}
