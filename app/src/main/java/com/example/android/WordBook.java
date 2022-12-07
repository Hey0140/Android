package com.example.android;

import android.view.View;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class WordBook {

    public String name;
    public Timestamp createDate;
    public String languageRelation;
    private long userId;
    public LinkedList<String> word;
    public LinkedList<String> mean;
    public LinkedList<View> wordView;
    int likeCount;
    View v1;

    public WordBook(String name, String word, String wordMean){
        // 단어장 이름 초기화
        this.name = name;
        // 단어장 생성 날짜 초기화
        createDate = new Timestamp(System.currentTimeMillis());
        languageRelation = word + "/" + wordMean;
        likeCount = 0;
        this.word = new LinkedList<>();
        this.mean = new LinkedList<>();
        this.wordView = new LinkedList<>();
    }

    // createDate를 문자열 형식으로 리턴하는 메소드.
    public String getCreateDateToString(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MM/dd");
        return formatter.format(createDate);

    }

}
