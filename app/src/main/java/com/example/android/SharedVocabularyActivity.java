package com.example.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class SharedVocabularyActivity extends AppCompatActivity implements View.OnClickListener{

    // 화면 스와이프를 위한 좌표
    float x1,x2,y1,y2;

    // 객체 연결
    EditText searchWindow;
    Button searchOptionButton;
    Button searchButton;
    ImageButton addButton;

    // 단어장 뷰 생성을 위한 콘테이너
    LinearLayout myVocaContainer;

    //단어생성 뷰 연결
    Button acceptButton;
    EditText acceptfirst;
    Button acceptsecond;
    Button acceptthird;
    ConstraintLayout addViewWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_vocabulary_activity);

        // 객체 연결
        searchWindow = findViewById(R.id.searchWindow2);
        searchOptionButton = findViewById(R.id.searchOptionButton2);
        searchButton = findViewById(R.id.searchButton2);
        addButton = findViewById(R.id.addButton2);
        addViewWindow = findViewById(R.id.addViewWindow2);
        acceptButton = findViewById(R.id.acceptButton2);
        addViewWindow.setVisibility(View.GONE);

        addButton.setOnClickListener(this);
        acceptButton.setOnClickListener(this);
        acceptfirst = findViewById(R.id.vocabularyNameForAdd2);
        acceptsecond = findViewById(R.id.wordForAdd2);
        acceptthird = findViewById(R.id.wordMeanForAdd2);
        myVocaContainer = findViewById(R.id.vocabularyListItemContainer2);
    }

    // 버튼 클릭 이벤트 구현
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchButton2:
                String searchWindowString = getSearchWindowString();
                //
                break;
            case R.id.addButton2:
                addViewWindow.setVisibility(View.VISIBLE);
                break;
            case R.id.acceptButton2:
                LayoutInflater inflater2 = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater2.inflate(R.layout.my_vocabulary_listitem, myVocaContainer, true);
                Log.d("@@@@@@@@@@@@@@","@@@@@@@@@@@@@@@@@@@@@@@@@");
                break;
        }

    }

    // 화면 스와이프를 통한 내 단어장 액티비티로 전환
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x2 + 500> x1){
                    finish();
                }
        }
        return false;
    }


    // 검색창의 값을 리턴. 빈 값이면 null을 리턴.
    public String getSearchWindowString(){
        String str = searchWindow.getText().toString();

        if(str.length() == 0)
        {
            return null;
        }else{
            return str;
        }
    }


}