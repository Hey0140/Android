package com.example.android;

import java.sql.Timestamp;
import java.util.List;

public class WordBook {
    private String name;
    private String createDate;
    private String languageRelation;
    private String meanLang;
    private String wordLang;
    private List<String> word;
    private List<String> mean;

    public WordBook() {}

    public WordBook(String name, String createDate, String meanLang, String wordLang) {
        this.name = name;
        this.createDate = createDate;
        this.meanLang = meanLang;
        this.wordLang = wordLang;
    }




    public String getName() {
        return name;
    }

    public String getCreateDate() {
        return createDate;
    }
    public String getMeanLang() { return meanLang; }
    public String getWordLang(){
        return wordLang;
    }
    public List<String> getWord() {
        return word;
    }

    public List<String> getMean() {
        return mean;
    }
}

