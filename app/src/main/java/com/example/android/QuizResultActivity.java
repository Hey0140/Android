package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizResultActivity extends AppCompatActivity {


    LinearLayout myWordListItemContainer;
    ImageView backbutton;


    int idEdit = 1000000000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        ArrayList<Integer> rightIndexList = new ArrayList<>();
        ArrayList<String> wordList = new ArrayList<>();
        ArrayList<String> meanList = new ArrayList<>();

        Intent intent = getIntent();
        int size = intent.getIntExtra("wholeSize",0);
        rightIndexList = intent.getIntegerArrayListExtra("clearList");
        wordList = intent.getStringArrayListExtra("wordList");
        meanList = intent.getStringArrayListExtra("meanList");
        Log.i("문제로 낸 단어의 개수 : ",Integer.toString(size));
        backbutton = findViewById(R.id.backButtonForQuizResult);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        for(int i = 0;i<rightIndexList.size();i++)
            Log.i("맞은 단어의 인덱스 : ",Integer.toString(rightIndexList.get(i)));
        myWordListItemContainer = findViewById(R.id.wordListItemContainerForQuizResult);
        for(int i = 0;i<size;i++) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.my_word_listitem_for_quiz_result, myWordListItemContainer, true);
            TextView temp = findViewById(R.id.wordForQuizResult);
            TextView temp2 = findViewById(R.id.meanForQuizResult);
            ImageView temp3 = findViewById(R.id.imageViewForQuizResult);
            ImageView temp4 = findViewById(R.id.imageViewForQuizResult2);
            Log.i("퀴즈 결과 단어 : ", temp.getText().toString());
            if(!rightIndexList.contains(i)){
                temp3.setVisibility(View.GONE);
                temp4.setVisibility(View.VISIBLE);
            }else{
                temp4.setVisibility(View.GONE);
                temp3.setVisibility(View.VISIBLE);
            }
            temp.setId(idEdit++);
            temp2.setId(idEdit++);
            temp.setText(wordList.get(i));
            temp2.setText(meanList.get(i));
            temp3.setId(idEdit++);
            temp4.setId(idEdit++);
        }
    }
}