package com.example.android;

import static android.util.Log.d;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyVocabularyActivity extends AppCompatActivity implements View.OnClickListener{

    // 화면 스와이프를 위한 좌표
    float x1,x2,y1,y2;
    public static ArrayList<MyVocabularyItem> myVocaArrayList = new ArrayList<>();

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
        setContentView(R.layout.my_vocabulary_activity);

        // 객체 연결
        searchWindow = findViewById(R.id.searchWindow);
        searchOptionButton = findViewById(R.id.searchOptionButton);
        searchButton = findViewById(R.id.searchButton);
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(this);
        searchButton = findViewById(R.id.searchButton);
        addViewWindow = findViewById(R.id.addViewWindow);
        acceptButton = findViewById(R.id.acceptButton);
        acceptButton.setOnClickListener(this);
        addViewWindow.setVisibility(View.GONE);
        acceptfirst = findViewById(R.id.vocabularyNameForAdd);
        acceptsecond = findViewById(R.id.wordForAdd);
        acceptthird = findViewById(R.id.wordMeanForAdd);
        myVocaContainer = findViewById(R.id.vocabularyListItemContainer);

        // 튜토리얼

    }

    // 버튼 클릭 이벤트 구현
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchButton:
                String searchWindowString = getSearchWindowString();
                break;
            case R.id.addButton:
                addViewWindow.setVisibility(View.VISIBLE);
                break;
            case R.id.acceptButton:
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.my_vocabulary_listitem, myVocaContainer, true);
                break;
        }
    }

    // 화면 스와이프를 통한 공유 단어장 액티비티로 전환
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 + 500> x2){
                    Intent i = new Intent(MyVocabularyActivity.this, SharedVocabularyActivity.class);
                    startActivity(i);
                }
        }
        return false;
    }

    // 검색창의 문자열을 얻어오는 함수 (검색버튼 클릭시만 호출)
    public String getSearchWindowString(){
        String str = searchWindow.getText().toString();
        if(str.length() == 0)
        {
            return "";
        }else{
            return str;
        }
    }
}