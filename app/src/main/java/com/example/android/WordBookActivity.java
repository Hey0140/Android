package com.example.android;

import static com.example.android.MyVocabularyActivity.myVocaArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WordBookActivity extends AppCompatActivity implements View.OnClickListener {

    TextView vocaNameLabel;
    ConstraintLayout rewriteWordWindow;
    ConstraintLayout addWordWindow;
    ConstraintLayout quizSelectWindow;
    ImageView backButton;
    ImageButton addButton;
    ImageView uploadButton;
    Button quizButton;
    LinearLayout myWordListItemContainer;
    EditText wordNameForAdd;
    EditText wordMeanForAdd;
    EditText wordNameForRewrite;
    EditText wordMeanForRewrite;
    Button acceptButtonForRewriteButton;
    Button acceptButtonForDeleteButton;
    Button acceptButtonForAddWord;
    Button wordQuizButton;
    Button meanQuizButton;
    View backgroundView;

    int wordId; // 5 10 15 20
    String wordIdString;
    int idForRewrite;
    boolean isWordQuiz = false;
    ArrayList<String> wordList;
    ArrayList<String> meanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_book);

        vocaNameLabel = findViewById(R.id.wordBookNameLabel);
        rewriteWordWindow = findViewById(R.id.wordRewriteWindow);
        addWordWindow = findViewById(R.id.addWordWindow);
        backButton = findViewById(R.id.backButton);
        myWordListItemContainer = findViewById(R.id.wordListItemContainer);
        wordNameForAdd = findViewById(R.id.EditTextForAddWord);
        wordMeanForAdd = findViewById(R.id.EditTextForAddWordMean);
        addButton = findViewById(R.id.addButton);
        acceptButtonForAddWord = findViewById(R.id.acceptButtonForAddWord);
        uploadButton = findViewById(R.id.uploadButton);
        quizButton = findViewById(R.id.quizButton);
        wordNameForRewrite = findViewById(R.id.EditTextForRewriteWord);
        wordMeanForRewrite = findViewById(R.id.EditTextForRewriteWordMean);
        acceptButtonForRewriteButton = findViewById(R.id.acceptButtonForRewriteWord);
        acceptButtonForDeleteButton = findViewById(R.id.acceptButtonForDeleteWord);
        quizSelectWindow = findViewById(R.id.quizChooseView);
        wordQuizButton = findViewById(R.id.wordQuiz);
        meanQuizButton = findViewById(R.id.meanQuiz);
        backgroundView = findViewById(R.id.backgroundViewForWordActivity);
        quizButton.setOnClickListener(this);
        uploadButton.setOnClickListener(this);
        addButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        acceptButtonForAddWord.setOnClickListener(this);
        wordNameForRewrite.setOnClickListener(this);
        wordMeanForRewrite.setOnClickListener(this);
        acceptButtonForRewriteButton.setOnClickListener(this);
        acceptButtonForDeleteButton.setOnClickListener(this);
        wordQuizButton.setOnClickListener(this);
        meanQuizButton.setOnClickListener(this);
        addWordWindow.setVisibility(View.GONE);
        rewriteWordWindow.setVisibility(View.GONE);
        Intent intent = getIntent();
        String getName = intent.getStringExtra("단어장 data");
        int tempInt = getName.indexOf("@");
        wordIdString = getName.substring(0, tempInt);
        String temp2 = getName.substring(tempInt + 1, getName.length());
        wordId = Integer.parseInt(temp2);
        quizSelectWindow.setVisibility(View.GONE);
        vocaNameLabel.setText(wordIdString);
        backgroundView.bringToFront();
        backgroundView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        wordList = new ArrayList<>(); // 신경X
        meanList = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backButton:
                Log.i("뒤로 가기 버튼 클릭","");
                finish();
                break;
            case R.id.addButton:
                Log.i("단어 추가 버튼 클릭","");
                addWordWindow.bringToFront();
                quizButton.setVisibility(View.GONE);
                addWordWindow.setVisibility(View.VISIBLE);
                backgroundView.setBackgroundColor(Color.parseColor("#85323232"));
                backgroundView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return true;
                    }
                });
                break;
            case R.id.acceptButtonForAddWord:

                backgroundView.setBackgroundColor(Color.parseColor("#00000000"));
                String str1 = wordNameForAdd.getText().toString();
                String str2 = wordMeanForAdd.getText().toString();
                Log.i("단어 생성 : ",str1);
                initWord(str1, str2);
                addWordWindow.setVisibility(View.GONE);
                quizButton.setVisibility(View.VISIBLE);
                wordMeanForAdd.setText("");
                wordNameForAdd.setText("");
                backgroundView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return false;
                    }
                });
                break;
            case R.id.uploadButton:
                Log.i("업로드 버튼 클릭","");
                break;
            case R.id.acceptButtonForDeleteWord:
                Log.i("단어 삭제 버튼 클릭","");
                deleteWord(idForRewrite);
                rewriteWordWindow.setVisibility(View.GONE);
                quizButton.setVisibility(View.VISIBLE);
                break;
            case R.id.acceptButtonForRewriteWord:
                Log.i("단어 수정 버튼 클릭","");
                rewriteWord(idForRewrite);
                wordNameForRewrite.setText("");
                wordMeanForRewrite.setText("");
                rewriteWordWindow.setVisibility(View.GONE);
                quizButton.setVisibility(View.VISIBLE);
                break;
            case R.id.quizButton:
                Log.i("퀴즈 버튼 클릭","");
                //startActivity(new Intent(WordBookActivity.this,MywordQuizActivity.class));
                quizSelectWindow.setVisibility(View.VISIBLE);
                quizButton.setVisibility(View.GONE);
                quizSelectWindow.bringToFront();
                backgroundView.setBackgroundColor(Color.parseColor("#85323232"));
                backgroundView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return true;
                    }
                });
                break;
            case R.id.wordQuiz:
                Log.i("단어 이름으로 퀴즈 버튼 클릭","");
                isWordQuiz = true;
                int idx = wordId / 5 - 1;
                Intent intent = new Intent(WordBookActivity.this, MywordQuizActivity.class);
                for (int i = 0; i < myVocaArrayList.get(idx).word.size(); i++) {
                    int id = wordId * 1000 + (i + 1) * 5 + 3;
                    CheckBox temp = findViewById(id);
                    TextView tempWord = findViewById(id - 2);
                    TextView tempMean = findViewById(id - 1);
                    if (temp.isChecked()) {
                        wordList.add(tempWord.getText().toString());
                        meanList.add(tempMean.getText().toString());
                    }
                }

                intent.putStringArrayListExtra("wordList", wordList);
                intent.putStringArrayListExtra("meanList", meanList);
                intent.putExtra("size", wordList.size());
                intent.putExtra("isWordQuiz", isWordQuiz);
                quizSelectWindow.setVisibility(View.GONE);
                startActivity(intent);
                backgroundView.setBackgroundColor(Color.parseColor("#00000000"));
                backgroundView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });
                break;
            case R.id.meanQuiz:
                Log.i("단어 의미로 퀴즈 버튼 클릭","");
                int idx2 = wordId / 5 - 1;
                ArrayList<String> wordList = new ArrayList<>(); // 신경X
                ArrayList<String> meanList = new ArrayList<>(); // 신경X
                Intent intent2 = new Intent(WordBookActivity.this, MywordQuizActivity.class);
                for (int i = 0; i < myVocaArrayList.get(idx2).word.size(); i++) {
                    int id = wordId * 1000 + (i + 1) * 5 + 3;
                    CheckBox temp = findViewById(id);
                    TextView tempWord = findViewById(id - 2);
                    TextView tempMean = findViewById(id - 1);
                    if (temp.isChecked()) {
                        wordList.add(tempWord.getText().toString());
                        meanList.add(tempMean.getText().toString());
                        Log.i("단어 체크됨 : ",tempWord.getText().toString());
                    }
                }

                intent2.putStringArrayListExtra("wordList", wordList);
                intent2.putStringArrayListExtra("meanList", meanList);
                intent2.putExtra("size", wordList.size());
                intent2.putExtra("isWordQuiz", isWordQuiz);
                quizSelectWindow.setVisibility(View.GONE);
                Log.i("단어 배열 전달,퀴즈 액티비티로 전환","");
                startActivity(intent2);
                backgroundView.setBackgroundColor(Color.parseColor("#00000000"));
                backgroundView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });
                break;
        }
    }


    public void initWord(String word, String wordMean) {
        int idx = wordId / 5 - 1;
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.my_word_listitem, myWordListItemContainer, true);
        myVocaArrayList.get(idx).word.addLast(word);
        myVocaArrayList.get(idx).mean.addLast(wordMean);
        myVocaArrayList.get(idx).isChecked.addLast(false);
        View tempView = myWordListItemContainer.findViewById(R.id.wordView);
        tempView.setId(wordId * 1000 + myVocaArrayList.get(idx).word.size() * 5); // 뷰 id
        Log.d("setID : ", Integer.toString(wordId * 1000 + myVocaArrayList.get(idx).word.size() * 5));
        tempView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                rewriteWordWindow.setVisibility(View.VISIBLE);
                quizButton.setVisibility(View.GONE);
                idForRewrite = v.getId();
                return true;
            }
        });
        myVocaArrayList.get(idx).wordView.addLast(tempView);
        TextView tempWord = myWordListItemContainer.findViewById(R.id.word);
        tempWord.setText(word);
        tempWord.setId(wordId * 1000 + myVocaArrayList.get(idx).word.size() * 5 + 1); // 단어 id
        TextView tempMean = myWordListItemContainer.findViewById(R.id.mean);
        tempMean.setId(wordId * 1000 + myVocaArrayList.get(idx).word.size() * 5 + 2); // 단어 뜻 id
        tempMean.setText(wordMean);
        CheckBox tempBox = myWordListItemContainer.findViewById(R.id.checkBox);
        tempBox.setId(wordId * 1000 + myVocaArrayList.get(idx).word.size() * 5 + 3); // 체크 박스 id
        Log.i("단어 생성 : ",word);

    }

    public void rewriteWord(int reId) {
        int index = wordId / 5 - 1;
        int arrIndex = (reId % 5000) / 5 - 1;

        TextView tempWord = myWordListItemContainer.findViewById(reId + 1);
        TextView tempMean = myWordListItemContainer.findViewById(reId + 2);
        String temp1 = wordNameForRewrite.getText().toString();
        String temp2 = wordMeanForRewrite.getText().toString();
        myVocaArrayList.get(index).word.set(arrIndex, temp1);
        myVocaArrayList.get(index).mean.set(arrIndex, temp2);
        tempWord.setText(temp1);
        tempMean.setText(temp2);
        Log.i("단어 수정 : ", tempWord.getText().toString() + "으로 변경");
    }

    public void deleteWord(int reId)
    //reid == 5010
    {
        int index = wordId / 5 - 1;
        int arrIndex = (reId % 5000) / 5 - 1; // 1
        int lastId = reId + (myVocaArrayList.get(index).word.size() - 1 - arrIndex) * 5;

        myVocaArrayList.get(index).word.remove(arrIndex);
        myVocaArrayList.get(index).mean.remove(arrIndex);
        myVocaArrayList.get(index).wordView.remove(arrIndex);

        View delView = (View) findViewById(reId);
        myWordListItemContainer.removeView((View) delView.getParent());
        for (int i = reId + 5; i <= lastId; i += 5) {
            // i == 5010 |
            View one = myWordListItemContainer.findViewById(i);

            one.setId(i - 5);

            TextView two = myWordListItemContainer.findViewById(i + 1);
            two.setId(i - 4);

            TextView three = myWordListItemContainer.findViewById(i + 2);
            three.setId(i - 3);

            TextView four = myWordListItemContainer.findViewById(i + 3);
            four.setId(i - 2);
        }
        Log.i("단어 삭제","");

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (addWordWindow.getVisibility() == View.VISIBLE) {
                addWordWindow.setVisibility(View.GONE);
                quizButton.setVisibility(View.VISIBLE);
                backgroundView.setBackgroundColor(Color.parseColor("#00000000"));
                backgroundView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return false;
                    }
                });
            }
            if (quizSelectWindow.getVisibility() == View.VISIBLE) {
                quizSelectWindow.setVisibility(View.GONE);
                quizButton.setVisibility(View.VISIBLE);
                backgroundView.setBackgroundColor(Color.parseColor("#00000000"));
                backgroundView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return false;
                    }
                });

            }
            Log.i("뒤로 가기 버튼 클릭","");
            return true;
        }
        else{
                return super.onKeyDown(keyCode, event);
        }

    }
}