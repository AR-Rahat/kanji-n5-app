package com.example.kanji_n5;

public class Week {
    private String name;

    public Week() {
        // Required empty public constructor for Firestore
    }
    Week(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

}
