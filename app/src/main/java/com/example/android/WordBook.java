package com.example.android;

import android.view.View;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class WordBook {

    public String name;
    public Timestamp createDate;
    public String languageRelation;
    private long userId;
    public LinkedList<String> word; // 단어장의 단어 배열
    public LinkedList<String> mean; // 단어장의 의미 배열
    public LinkedList<Boolean> isChecked;
    public LinkedList<View> wordView;
    public int rewriteId;
    //추가 필요함
    private int vocabulary_id;
    public String date;
    int likeCount;
    public int word_id;
    public String voca_word;
    public String voca_mean;

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
        this.isChecked = new LinkedList<>();
    }

    public WordBook(String name, String word, String wordMean, int id){
        // 단어장 이름 초기화
        this.name = name;
        // 단어장 생성 날짜 초기화
        createDate = new Timestamp(System.currentTimeMillis());
        languageRelation = word + "/" + wordMean;
        likeCount = 0;
        this.vocabulary_id = id;
        this.word = new LinkedList<>();
        this.mean = new LinkedList<>();
        this.wordView = new LinkedList<>();
        this.isChecked = new LinkedList<>();
    }

    public WordBook(String name, String word, String wordMean, int id, String date){
        // 단어장 이름 초기화
        this.name = name;
        // 단어장 생성 날짜 초기화
        this.date = date;
        this.vocabulary_id = id;
        //추가 부분
        languageRelation = word + "/" + wordMean;
        this.word = new LinkedList<>();
        this.mean = new LinkedList<>();
        this.wordView = new LinkedList<>();
        this.isChecked = new LinkedList<>();
    }

    public WordBook(int word_id, String voca_word, String voca_mean, String date){
        // 단어장 이름 초기화
        this.name = name;
        // 단어장 생성 날짜 초기화
        this.date = date;
        this.vocabulary_id = word_id;
        this.voca_word = voca_word;
        this.voca_mean = voca_mean;
    }


    // createDate를 문자열 형식으로 리턴하는 메소드.
    public String getCreateDateToString(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MM/dd");
        return formatter.format(createDate);
    }
    public int getVocabulary_id(){return vocabulary_id; }
    public String getName(){
        return name;
    }
    public String getDate() {return date;}
    public int getLikeCount(){return likeCount;}
}