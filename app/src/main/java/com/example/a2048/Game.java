package com.example.a2048;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import java.*;

public class Game extends AppCompatActivity implements SwipeInterface{
    private Button newGame,endGame;
    private Data dt;
    private gameLogic gl;
    private int hraSize=0;
    private TextView score,best;
    private TextView[][] gameFieldTest;


    private Activity activity;
    static final int MIN_DISTANCE = 100;
    private float downX, downY, upX, upY;

    public void setGame(){
        int size_ = dt.getType();

        LinearLayout gameField =(LinearLayout) findViewById(R.id.gameField);
        LinearLayout.LayoutParams tmp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.FILL_PARENT,1);

        for(int i = 0 ; i<size_ ; i++){

            LinearLayout Layout_ = new LinearLayout(this);
            Layout_.setLayoutParams(tmp);
            Layout_.setBackgroundColor(Color.parseColor("#a88d61"));
            Layout_.setOrientation(LinearLayout.HORIZONTAL);
            gameField.addView(Layout_);

            for (int j = 0 ; j<size_ ; j++){

                gameFieldTest[i][j] = new TextView(this) ;
                gameFieldTest[i][j].setLayoutParams(tmp);
                gameFieldTest[i][j].setText("val!");
                gameFieldTest[i][j].setBackgroundColor(Color.parseColor("#e5d3c7"));
                //TODO: SetUniform
                gameFieldTest[i][j].setGravity(Gravity.CENTER);
                Layout_.addView(gameFieldTest[i][j]);

            }
        }

        upravZobrazovaciuPlochu();
    }

    public void upravZobrazovaciuPlochu(){
        int size_ = dt.getType();
        for(int i = 0 ; i<size_ ; i++){
            for(int j = 0 ; j<size_ ; j++){
                gameFieldTest[i][j].setText(dt.getHraProk(i,j));
            }
        }
        score.setText(dt.getScore());
        switch (dt.getType()){
            case 2:
                best.setText(dt.getHigh2x2());
                break;
            case 3:
                best.setText(dt.getHigh3x3());
                break;
            default:
                best.setText(dt.getHigh4x4());
        }
    }

    public void vykonaj(int tmp){
        if(gl.swictcher(tmp))
            gl.doSmer(tmp);
        this.upravZobrazovaciuPlochu();
        if(gl.testKoniecHry())
            ukonc();

    }

    //TODO: Preklad


    @Override
    public void left2right(View v) {
        switch(v.getId()){
            case R.id.game_layout:
               vykonaj(6);
                break;
        }
    }
    @Override
    public void right2left(View v) {
        switch(v.getId()){
            case R.id.game_layout:
                vykonaj(4);
                break;
        }
    }
    @Override
    public void bottom2top(View v) {
        switch(v.getId()){
            case R.id.game_layout:
                vykonaj(8);
                break;
        }
    }
    @Override
    public void top2bottom(View v) {
        switch(v.getId()){
            case R.id.game_layout:
                vykonaj(2);
                break;
        }
    }

    //TODO: Naklananie hry

    private View.OnClickListener newGameListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int size_ = dt.getType();
            for(int i = 0 ; i<size_ ; i++){
                for(int j = 0 ; j<size_ ; j++){
                    dt.setPrvokPola(0,i,j);
                    gl.pridajPrvok();
                    gl.pridajPrvok();
                }
            }
            dt.setScore(0);
            upravZobrazovaciuPlochu();
        }
    };

    public void ukonc(){
        Intent intent = new Intent(Game.this, EndGame.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("dtData", dt);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private View.OnClickListener endGameListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ukonc();
        }
    };

    @Override
    public void onBackPressed() { }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        ActivitySwipeDetector swipe = new ActivitySwipeDetector(this);
        LinearLayout swipe_layout = (LinearLayout) findViewById(R.id.game_layout);
        swipe_layout.setOnTouchListener(swipe);

        //TODO: Intent getIntent_ = getIntent();

        score = (TextView) findViewById(R.id.valueScore);
        best = (TextView) findViewById(R.id.bestScore);

        newGame = (Button) findViewById(R.id.bGame_NEW);
        endGame = (Button) findViewById(R.id.bGame_END);

        newGame.setOnClickListener(newGameListener);
        endGame.setOnClickListener(endGameListener);
    }
}
