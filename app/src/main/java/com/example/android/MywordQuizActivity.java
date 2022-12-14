package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class MywordQuizActivity extends AppCompatActivity implements View.OnClickListener {

    //데이터
    wordDataBaseHelper wordDB = new wordDataBaseHelper(this);

    boolean isWordQuiz;

    Button submit_answer;
    ImageView back_button_image;
    Button next_quiz;
    TextView quiz_text;
    TextView quiz_text2;
    TextView input_answer;
    View quiz_text_box2;
    View quiz_text_box;
    ImageView quiz_answer_correct;
    ImageView quiz_answer_incorrect;
    wordDataBaseHelper quiz_data;
    wordDataBaseHelper answer_data;

//    List<String> quiz;
//    List<String> answer
//    List<Integer> check;
    //1이 나오는 상태 0이 나오지 않는 상태





    Random random = new Random();
    int quizIndex = 0;
    boolean check = true;
    int size = 0;
    ArrayList<Integer> clearQuizIndex = new ArrayList<>();
    ArrayList<Integer> randomIndexList = new ArrayList<>();
    ArrayList<String> wordList;
    ArrayList<String> meanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent intent = new Intent(this.getIntent());
        size = intent.getIntExtra("size",0);
        wordList = intent.getStringArrayListExtra("wordList");
        meanList = intent.getStringArrayListExtra("meanList");
        isWordQuiz = intent.getBooleanExtra("isWordQuiz",false);
        Log.i("퀴즈로 나올 단어의 개수 : ",Integer.toString(size));
        if(wordList.size()== 0){
            Toast toast = new Toast(MywordQuizActivity.this);
            toast.makeText(MywordQuizActivity.this,
                    "퀴즈로 낼 수 있는 단어가 없습니다..", Toast.LENGTH_LONG).show();
            Log.i("size == 0","액티비티 종료");
            finish();
        }
        next_quiz = findViewById(R.id.next_quiz);
        quiz_text2 = findViewById(R.id.quiz_co_answer);
        quiz_text_box2 = findViewById(R.id.quiz_co_answer_box);
        quiz_text_box = findViewById(R.id.quiz_text_box);
        input_answer =  findViewById(R.id.input_answer);
        quiz_text =  findViewById(R.id.quiz_text);
        submit_answer = findViewById(R.id.submit_answer);
        back_button_image = findViewById(R.id.back_button_image);
        quiz_answer_correct = findViewById(R.id.quiz_answer_correct);
        quiz_answer_incorrect = findViewById(R.id.quiz_answer_incorrect);
        quiz_answer_correct.bringToFront();
        quiz_answer_incorrect.bringToFront();
        quiz_data = new wordDataBaseHelper(this);
        answer_data = new wordDataBaseHelper(this);
        //리스너
        submit_answer.setOnClickListener(this);
        submit_answer.setVisibility(View.GONE);
        next_quiz.setVisibility(View.VISIBLE);
        next_quiz.setOnClickListener(this);
        back_button_image.setOnClickListener(this);
        for(int i = 0;i<size;i++) {
            randomIndexList.add(i);
        }
        Collections.shuffle(randomIndexList);

//        quiz = getResources().getStringArray(R.array.quiz);
//        answer = getResources().getStringArray(R.array.answer);
        if(!isWordQuiz){
            Log.i("의미 퀴즈 버튼 클릭","");
            quiz_text.setText(wordList.get(randomIndexList.get(quizIndex)));
            quiz_text.setVisibility(View.VISIBLE);
            quiz_text_box.setVisibility(View.VISIBLE);
            quiz_text2.setVisibility(View.GONE);
            quiz_text_box2.setVisibility(View.GONE);
            quizIndex++;
        }else{
            Log.i("단어 퀴즈 버튼 클릭","");
            quiz_text.setText(meanList.get(randomIndexList.get(quizIndex)));
            quiz_text.setVisibility(View.VISIBLE);
            quiz_text_box.setVisibility(View.VISIBLE);
            quiz_text2.setVisibility(View.GONE);
            quiz_text_box2.setVisibility(View.GONE);
            quizIndex++;
        }
    }





    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.submit_answer:
                Log.i("submit","submit");
                if(!isWordQuiz) {
                    if (input_answer.getText().toString().equals(meanList.get(randomIndexList.get(quizIndex - 1)))) {
                        Animation animation = new AlphaAnimation(0, 1);
                        animation.setDuration(500);
                        Animation _animation = new AlphaAnimation(1, 0);
                        _animation.setDuration(1500);
                        quiz_answer_correct.setAnimation(animation);
                        quiz_answer_correct.setVisibility(View.VISIBLE);
                        quiz_answer_correct.setAnimation(_animation);
                        quiz_answer_correct.setVisibility(View.GONE);
                        clearQuizIndex.add(randomIndexList.get(quizIndex - 1));
                    } else {
                        Animation animation = new AlphaAnimation(0, 1);
                        animation.setDuration(500);
                        Animation _animation = new AlphaAnimation(1, 0);
                        _animation.setDuration(1500);
                        quiz_answer_incorrect.setAnimation(animation);
                        quiz_answer_incorrect.setVisibility(View.VISIBLE);
                        quiz_answer_incorrect.setAnimation(_animation);
                        quiz_answer_incorrect.setVisibility(View.GONE);
                    }
                }else{
                    if (input_answer.getText().toString().equals(wordList.get(randomIndexList.get(quizIndex - 1)))) {
                        Animation animation = new AlphaAnimation(0, 1);
                        animation.setDuration(500);
                        Animation _animation = new AlphaAnimation(1, 0);
                        _animation.setDuration(1500);
                        quiz_answer_correct.setAnimation(animation);
                        quiz_answer_correct.setVisibility(View.VISIBLE);
                        quiz_answer_correct.setAnimation(_animation);
                        quiz_answer_correct.setVisibility(View.GONE);
                        clearQuizIndex.add(randomIndexList.get(quizIndex - 1));
                    } else {
                        Animation animation = new AlphaAnimation(0, 1);
                        animation.setDuration(500);
                        Animation _animation = new AlphaAnimation(1, 0);
                        _animation.setDuration(1500);
                        quiz_answer_incorrect.setAnimation(animation);
                        quiz_answer_incorrect.setVisibility(View.VISIBLE);
                        quiz_answer_incorrect.setAnimation(_animation);
                        quiz_answer_incorrect.setVisibility(View.GONE);
                    }
                }
                /////////////////////
                Intent intent = new Intent(MywordQuizActivity.this,QuizResultActivity.class);
                intent.putIntegerArrayListExtra("clearList",clearQuizIndex);
                intent.putExtra("wholeSize", size);
                intent.putStringArrayListExtra("wordList", wordList);
                intent.putStringArrayListExtra("meanList", meanList);
                for(int i = 0;i<clearQuizIndex.size();i++){
                    Log.d("맞춘 퀴즈의 인덱스 : ", Integer.toString(clearQuizIndex.get(i)));
                }

                startActivity(intent);
                finish();
                break;
            case R.id.next_quiz:
                if(!isWordQuiz){
                    if (check) {
                        quiz_text2.setText(wordList.get(randomIndexList.get(quizIndex)));
                        quiz_text2.setVisibility(View.VISIBLE);
                        quiz_text_box2.setVisibility(View.VISIBLE);
                        quiz_text.setVisibility(View.GONE);
                        quiz_text_box.setVisibility(View.GONE);
                        if(input_answer.getText().toString().equals(meanList.get(randomIndexList.get(quizIndex-1)))){
                            input_answer.setText("");
                            correctAnswer();
                        }else{
                            incorrectAnswer();
                            input_answer.setText("");
                        }
                    }else {
                        quiz_text.setText(wordList.get(randomIndexList.get(quizIndex)));
                        quiz_text.setVisibility(View.VISIBLE);
                        quiz_text_box.setVisibility(View.VISIBLE);
                        quiz_text2.setVisibility(View.GONE);
                        quiz_text_box2.setVisibility(View.GONE);
                        if(input_answer.getText().toString().equals(meanList.get(randomIndexList.get(quizIndex-1)))){
                            input_answer.setText("");
                            correctAnswer();
                        }else{
                            incorrectAnswer();
                            input_answer.setText("");
                        }
                    }
                    ////
                }else {
                    if (check) {
                        quiz_text2.setText(meanList.get(randomIndexList.get(quizIndex)));
                        quiz_text2.setVisibility(View.VISIBLE);
                        quiz_text_box2.setVisibility(View.VISIBLE);
                        quiz_text.setVisibility(View.GONE);
                        quiz_text_box.setVisibility(View.GONE);
                        if (input_answer.getText().toString().equals(wordList.get(randomIndexList.get(quizIndex - 1)))) {
                            input_answer.setText("");
                            correctAnswer();
                        } else {
                            incorrectAnswer();
                            input_answer.setText("");
                        }
                    } else {
                        quiz_text.setText(meanList.get(randomIndexList.get(quizIndex)));
                        quiz_text.setVisibility(View.VISIBLE);
                        quiz_text_box.setVisibility(View.VISIBLE);
                        quiz_text2.setVisibility(View.GONE);
                        quiz_text_box2.setVisibility(View.GONE);
                        if (input_answer.getText().toString().equals(wordList.get(randomIndexList.get(quizIndex - 1)))) {
                            input_answer.setText("");
                            correctAnswer();
                        } else {
                            incorrectAnswer();
                            input_answer.setText("");
                        }

                    }
                }
                if(quizIndex == size){
                    next_quiz.setVisibility(View.GONE);
                    submit_answer.setVisibility(View.VISIBLE);
                    break;
                }
        }
        if (view.getId() == R.id.back_button_image) {
            finish();
        }
    }
    public void correctAnswer(){
        Animation animation = new AlphaAnimation(0, 1);
        animation.setDuration(500);
        Animation _animation = new AlphaAnimation(1, 0);
        _animation.setDuration(1500);
        quiz_answer_correct.setAnimation(animation);
        quiz_answer_correct.setVisibility(View.VISIBLE);
        quiz_answer_correct.setAnimation(_animation);
        quiz_answer_correct.setVisibility(View.GONE);
        clearQuizIndex.add(randomIndexList.get(quizIndex-1));
        quizIndex++;
        check = !check;

    }
    public void incorrectAnswer(){
        Animation animation = new AlphaAnimation(0, 1);
        animation.setDuration(500);
        Animation _animation = new AlphaAnimation(1, 0);
        _animation.setDuration(1500);
        quiz_answer_incorrect.setAnimation(animation);
        quiz_answer_incorrect.setVisibility(View.VISIBLE);
        quiz_answer_incorrect.setAnimation(_animation);
        quiz_answer_incorrect.setVisibility(View.GONE);
        quizIndex++;
        check = !check;
    }

}
