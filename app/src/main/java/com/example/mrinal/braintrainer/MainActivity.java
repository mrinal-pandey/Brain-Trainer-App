package com.example.mrinal.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    Button option1, option2, option3, option4, goButton, playAgainButton;
    TextView timerTextView, questionTextView, scoreTextView, resultTextView;
    GridLayout gridLayout;
    boolean gameIsActive;
    int number1,number2,sum1,sum2,sum3,sum4,userTapped,counterQuestion,counterCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = (Button) findViewById(R.id.goButton);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        resultTextView=(TextView)findViewById(R.id.resultTextView);
        playAgainButton=(Button)findViewById(R.id.playAgainButton);
        option1 = (Button) findViewById(R.id.option1);
        option2 = (Button) findViewById(R.id.option2);
        option3 = (Button) findViewById(R.id.option3);
        option4 = (Button) findViewById(R.id.option4);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        counterQuestion=-1;
        counterCorrect=0;
        gameIsActive=true;
    }



    public void updateQuestion(View view){

        if(gameIsActive==true) {


            Button getTagButton = (Button) view;

            userTapped=Integer.valueOf(getTagButton.getTag().toString());

            String buttonPressed="0";
            if(userTapped!=0) {
                buttonPressed = getTagButton.getText().toString();
            }

            if (Integer.valueOf(buttonPressed) == sum1) {

                if (userTapped != 0){
                resultTextView.setText("Correct");
                counterCorrect++;}

            } else {
                if (userTapped != 0)
                    resultTextView.setText("Wrong");

            }

            number1 = (int) (Math.random() * 100);
            number2 = (int) (Math.random() * 100);
            sum1 = number1 + number2;
            sum2 = number1 + number2 +5;
            sum3 = number1 + number2 + 3;
            sum4 = number1 + number2 +10;
            ArrayList<Integer> options=new ArrayList<Integer>(4);
            options.add(sum1);
            options.add(sum2);
            options.add(sum3);
            options.add(sum4);
            Collections.shuffle(options);

            questionTextView.setText(String.valueOf(number1) + "+" + String.valueOf(number2));

            option1.setText(String.valueOf(options.get(0)));
            option2.setText(String.valueOf(options.get(1)));
            option3.setText(String.valueOf(options.get(2)));
            option4.setText(String.valueOf(options.get(3)));



            counterQuestion++;

            resultTextView.setVisibility(View.VISIBLE);
            scoreTextView.setText(String.valueOf(counterCorrect) + "/" + String.valueOf(counterQuestion));
        }
    }

    public void beginTraining(View view)
    {

if(gameIsActive==true) {
    goButton.setVisibility(View.INVISIBLE);
    gridLayout.setVisibility(View.VISIBLE);
    timerTextView.setVisibility(View.VISIBLE);
    questionTextView.setVisibility(View.VISIBLE);
    scoreTextView.setVisibility(View.VISIBLE);


    scoreTextView.setText("0/0");
    timerTextView.setText("00:30");
    updateQuestion(view);


    timerGame();
}

}


public void timerGame(){

    final CountDownTimer countDownTimer=new CountDownTimer(30000+110,1000) {
        @Override
        public void onTick(long l) {


            String secondString=String.valueOf((int)(l/1000));
            if((int)(l/1000)<=9)
                secondString="0"+String.valueOf((int)(l/1000));
            timerTextView.setText("00:"+secondString);


        }

        @Override
        public void onFinish() {

            resultTextView.setVisibility(View.VISIBLE);
            playAgainButton.setVisibility(View.VISIBLE);
            resultTextView.setText("Your score:"+String.valueOf(counterCorrect)+"/"+String.valueOf(counterQuestion));
            playAgainButton.setText("Play Again");
            counterCorrect=0;
            counterQuestion=-1;
            gameIsActive=false;

        }
    }.start();

}

public void playAgain(View view){
    gameIsActive=true;
if(gameIsActive==true) {
    scoreTextView.setText("0/0");
    timerTextView.setText("00:30");
    resultTextView.setText("");
    playAgainButton.setVisibility(View.INVISIBLE);
    resultTextView.setVisibility(View.INVISIBLE);
    updateQuestion(view);

    timerGame();

}
}
}