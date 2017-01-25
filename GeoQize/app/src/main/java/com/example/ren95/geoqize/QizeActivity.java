package com.example.ren95.geoqize;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QizeActivity extends AppCompatActivity {
    private static final String TAG="QizeActivity";
    private static final String KEY_INDEX="index";
    private static final String EXTRA_ANSWER_IS_TRUE="com.example.ren95.geoqize.answer_is_true";
    private static final int REQUEST_CODE_CHEAT=0;
    private boolean isCheat;
    private int currentQuestion=0;
    private TextView textBroser;
    private ImageButton leftButton;
    private ImageButton rigtButton;
    private Button tryButton;
    private Button falseButton;
    private Button cheatButton;
    private Question [] bankQiestion=new Question[]{
new Question(R.string.question1,true),
    new Question(R.string.question2,false),
            new Question(R.string.question3,false),
            new Question(R.string.question4,false)
    };
public static Intent newIntent(Context packageContext,boolean answerIsTrue){
    Intent i=new Intent(packageContext,Cheat.class);
    i.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
    return i;
}


    private void updateQuestion() {
         int question = bankQiestion[currentQuestion].getTextQuestionId();
        textBroser.setText(question);
       // Log.d(TAG,"Updating question text for question #"+currentQuestion,new Exception());
    }
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = bankQiestion[currentQuestion].isAnswerTrue();//возращаем из текущего вопроса ответ
        int messageResId = 0;
        if (isCheat) {
            messageResId = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.TostAnswerTry;
            } else {
                messageResId = R.string.TostAnswerFalse;
            }
        }
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        if(requestCode==REQUEST_CODE_CHEAT){
            if(data==null){
                return;
            }
            isCheat=Cheat.wasAnswerShown(data);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate(Bundel) called");
        setContentView(R.layout.activity_qize);
        textBroser=(TextView)findViewById(R.id.textView);
if(savedInstanceState!=null){
    currentQuestion=savedInstanceState.getInt(KEY_INDEX,0);
}
        cheatButton=(Button)findViewById(R.id.cheat_button);
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue=bankQiestion[currentQuestion].isAnswerTrue();
                Intent i=newIntent(QizeActivity.this,answerIsTrue);
                startActivityForResult(i,REQUEST_CODE_CHEAT);
            }
        });
        tryButton=(Button)findViewById(R.id.buttonTry);
      tryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkAnswer(true);
            }
        });
        falseButton=(Button)findViewById(R.id.buttonFalse);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
checkAnswer(false);
            }
        });
        leftButton=(ImageButton)findViewById(R.id.imageButtonLeft);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestion != 0) {
                    currentQuestion = currentQuestion - 1;
                }
                isCheat=false;
               updateQuestion();
            }
        });

        rigtButton=(ImageButton)findViewById(R.id.imageButtonRigt);
        rigtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             currentQuestion=(currentQuestion+1)%bankQiestion.length;
                isCheat=false;
updateQuestion();
            }
        });
        textBroser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestion=(currentQuestion+1)%bankQiestion.length;
                isCheat=false;
                updateQuestion();
            }
        });
        updateQuestion();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG,"onStart");
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,currentQuestion);
    }
}
