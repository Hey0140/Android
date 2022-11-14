package com.example.android;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MyVocabularyActivity extends AppCompatActivity implements View.OnClickListener{

    // 화면 스와이프를 위한 좌표
    float x1,x2,y1,y2;

    // 객체 연결
    EditText searchWindow;
    Button searchOptionButton;
    Button searchButton;
    ImageButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myvocabulary);

        // 객체 연결
        searchWindow = findViewById(R.id.searchWindow);
        searchOptionButton = findViewById(R.id.searchOptionButton);
        searchButton = findViewById(R.id.searchButton);
        addButton = findViewById(R.id.addButton);

    }

    // 버튼 클릭 이벤트 구현
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchButton:
                String searchWindowString = getSearchWindowString();
                //
                break;
            case R.id.addButton:
                //
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