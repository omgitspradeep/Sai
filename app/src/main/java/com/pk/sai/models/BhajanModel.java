package com.pk.sai.models;

public class BhajanModel {

    String bhajanTitle, bhajanUrl;
    public BhajanModel(String title, String url) {
        this.bhajanTitle = title;
        this.bhajanUrl = url;
    }

    public String getBhajanTitle() {
        return bhajanTitle;
    }

    public String getBhajanUrl() {
        return bhajanUrl;
    }
}
