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
import android.widget.ScrollView;
import android.widget.TextView;

public class SharedVocabularyActivity extends AppCompatActivity implements View.OnClickListener{

    // 화면 스와이프를 위한 좌표
    float x1,x2,y1,y2;
    private boolean isSecond = true;
    private boolean isForRewrite = false;

    // 객체 연결
    EditText searchWindow;
    Button searchOptionButton;
    Button searchButton;
    ImageButton addButton;

    // 단어장 뷰 생성을 위한 콘테이너
    LinearLayout myVocaContainer;

    //단어장 생성 뷰 연결
    Button buttonForAddWordBook;
    EditText nameForAddWordBook;
    Button wordForAddWordBook;
    Button wordMeanForAddWordBook;
    ConstraintLayout addViewWindow;
    ScrollView languagePickerScrollView;
    ConstraintLayout languagePickerWindow;


    // 단어장 수정 뷰 연결
    ConstraintLayout rewriteViewWindow;
    ConstraintLayout deleteViewWindow;
    EditText vocaNameForRewrite;
    Button buttonForRewrite;
    Button acceptButtonForDelete;
    Button wordForRewrite;
    Button wordMeanForRewrite;

    TextView korB;
    TextView japB;
    TextView grkB;
    TextView gerB;
    TextView porB;
    TextView spnB;
    TextView chiB;
    TextView frhB;
    TextView rusB;
    TextView engB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_vocabulary_activity);

        // 객체 연결
        searchWindow = findViewById(R.id.shared_searchWindow);
        searchOptionButton = findViewById(R.id.shared_searchOptionButton);
        searchButton = findViewById(R.id.shared_searchButton);
        addButton = findViewById(R.id.shared_addButton);
        addViewWindow = findViewById(R.id.shared_addViewWindow);
        buttonForAddWordBook = findViewById(R.id.shared_acceptButton);
        addViewWindow.setVisibility(View.GONE);
        nameForAddWordBook = findViewById(R.id.shared_vocabularyNameForAdd);
        wordForAddWordBook = findViewById(R.id.shared_wordForAdd);
        wordMeanForAddWordBook = findViewById(R.id.shared_wordMeanForAdd);
        myVocaContainer = findViewById(R.id.shared_vocabularyListItemContainer);
        languagePickerScrollView = (ScrollView)findViewById(R.id.shared_languagePickerWindowScrollView);
        languagePickerWindow = findViewById(R.id.shared_languagePickerWindow);

        rewriteViewWindow = findViewById(R.id.shared_rewriteViewWindow);
        buttonForRewrite = findViewById(R.id.shared_acceptButtonForRewrite);
        wordForRewrite = findViewById(R.id.shared_wordForRewrite);
        wordMeanForRewrite = findViewById(R.id.shared_wordMeanForRewrite);
        acceptButtonForDelete = findViewById(R.id.shared_acceptButtonForDelete);
        vocaNameForRewrite = findViewById(R.id.shared_vocabularyNameForRewrite);

        korB = findViewById(R.id.shared_koreanPick);
        chiB = findViewById(R.id.shared_chinesePick);
        japB = findViewById(R.id.shared_japanesePick);
        frhB = findViewById(R.id.shared_frenchPick);
        porB = findViewById(R.id.shared_portugalPick);
        grkB = findViewById(R.id.shared_greekPick);
        gerB = findViewById(R.id.shared_germanPick);
        spnB = findViewById(R.id.shared_spanshPick);
        rusB = findViewById(R.id.shared_russianPick);
        engB = findViewById(R.id.shared_englisgPick);

        // 객체 이벤트 리스너 등
        addButton.setOnClickListener(this);
        buttonForAddWordBook.setOnClickListener(this);
        korB.setOnClickListener(this);
        engB.setOnClickListener(this);
        chiB.setOnClickListener(this);
        japB.setOnClickListener(this);
        frhB.setOnClickListener(this);
        porB.setOnClickListener(this);
        grkB.setOnClickListener(this);
        gerB.setOnClickListener(this);
        spnB.setOnClickListener(this);
        rusB.setOnClickListener(this);

        addButton.setOnClickListener(this);
        buttonForAddWordBook.setOnClickListener(this);
        wordForAddWordBook.setOnClickListener(this);
        wordMeanForAddWordBook.setOnClickListener(this);
        rewriteViewWindow.setOnClickListener(this);
        languagePickerWindow.setVisibility(View.GONE);
        rewriteViewWindow.setVisibility(View.GONE);
    }

    // 버튼 클릭 이벤트 구현
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchButton: // 검색 버튼 (미완료)
                String searchWindowString = getSearchWindowString();
                break;
                //
            case R.id.shared_addButton: // 단어장 추가 버튼 클릭시
                addViewWindow.setVisibility(View.VISIBLE);
                isForRewrite = false;
                break;
            case R.id.shared_acceptButton: // 단어장 [생성하기] 버튼 클릭시
                addViewWindow.setVisibility(View.INVISIBLE);
                isForRewrite = false;
                break;
            case R.id.shared_wordForAdd:
                languagePickerWindow.setVisibility(View.VISIBLE);
                break;
            case R.id.shared_wordMeanForAdd:
                languagePickerWindow.setVisibility(View.VISIBLE);
                break;
            case R.id.shared_koreanPick:
                setLanguageBlank(isSecond, isForRewrite,"한국어");
                break;
            case R.id.shared_chinesePick:
                setLanguageBlank(isSecond, isForRewrite,"중국어");
                break;
            case R.id.shared_englisgPick:
                setLanguageBlank(isSecond, isForRewrite, "영어");
                break;
            case R.id.shared_frenchPick:
                setLanguageBlank(isSecond, isForRewrite,"프랑스어");
                break;
            case R.id.shared_germanPick:
                setLanguageBlank(isSecond, isForRewrite,"독일어");
                break;
            case R.id.shared_portugalPick:
                setLanguageBlank(isSecond, isForRewrite,"포르투갈어");
                break;
            case R.id.shared_spanshPick:
                setLanguageBlank(isSecond, isForRewrite,"스페인어");
                break;
            case R.id.shared_greekPick:
                setLanguageBlank(isSecond, isForRewrite,"그리스어");
                break;
            case R.id.shared_japanesePick:
                setLanguageBlank(isSecond, isForRewrite,"일본어");
                break;
            case R.id.shared_russianPick:
                setLanguageBlank(isSecond, isForRewrite,"러시아어");

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
                    addViewWindow.setVisibility(View.GONE);
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

    public void setLanguageBlank(boolean isS, boolean isR, String str) {
        if (isR) {
            if (isS){
                wordForRewrite.setText(str);
            }else{
                wordMeanForRewrite.setText(str);
            }
            isSecond = !isSecond;
            languagePickerWindow.setVisibility(View.INVISIBLE);
            languagePickerScrollView.fullScroll(0);
            return;
        } else {
            if (isS){
                wordForAddWordBook.setText(str);
            }else{
                wordMeanForAddWordBook.setText(str);
            }
            isSecond = !isSecond;
            languagePickerWindow.setVisibility(View.INVISIBLE);
            languagePickerScrollView.fullScroll(0);
            return;
        }
    }



}