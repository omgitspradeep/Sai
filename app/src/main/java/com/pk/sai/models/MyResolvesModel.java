package com.pk.sai.models;

public class MyResolvesModel {
    private String myPromise;
    private int id;

    public MyResolvesModel(int id,String my_promise) {
        this.id = id;
        this.myPromise = my_promise;
    }

    public String getMyPromise() {
        return myPromise;
    }

    public int getId() {
        return id;
    }

    public void setMyPromise(String myPromise) {
        this.myPromise = myPromise;
    }
}
