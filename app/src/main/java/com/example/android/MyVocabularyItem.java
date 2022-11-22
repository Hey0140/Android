package com.example.android;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyVocabularyItem {

    public String vocabularyName;
    public String vocabularyBirthDay;
    public int vocabularyCount;
    public String languageRelation;
    int id;

    public MyVocabularyItem(String name, String word, String wordMean){
        // 단어장 이름 초기화
        vocabularyBirthDay = name;
        // 단어장 생성 날짜 초기화
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date(System.currentTimeMillis());
        vocabularyBirthDay = formatter.format(date);
        // 단어장 수 초기화
        vocabularyCount = 0;
        this.languageRelation = languageRelation;
    }

}
