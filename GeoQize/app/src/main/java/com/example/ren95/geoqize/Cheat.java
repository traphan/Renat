package com.example.ren95.geoqize;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Cheat extends AppCompatActivity {
    private static final String TAG="CheatActivity";
    private static final String EXTRA_ANSWER_IS_TRUE="com.example.ren95.geoqize.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN ="com.example.ren95.geoqize.answer_shown";
    private static final String ANSWER_IS_TRUE="ANSWER";
    private boolean answerIsTrue;
    private Button showAnswer;
    private TextView answerTextView;
    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data=new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,data);
    }
    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        answerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        showAnswer=(Button)findViewById(R.id.showAnswerButton);
        answerTextView=(TextView)findViewById(R.id.answerTextView);
        if(savedInstanceState==null){
           setAnswerShownResult(false);
        }
        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answerIsTrue){
                    answerTextView.setText(R.string.TostAnswerTry);
                }else {
                    answerTextView.setText(R.string.TostAnswerFalse);
                }

                setAnswerShownResult(true);
            }
        });

    }


}
