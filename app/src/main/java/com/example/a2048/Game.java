package com.example.a2048;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends AppCompatActivity{
    private Button newGame,endGame;
    private Data dt;
    //private int[][] Hra;
    private int hraSize=0;

    public void setGame(){
        //Urob co treba prri vytvarani hry
    }
/*
    public Game(Data tmp){
        this.hraSize=tmp.getType();
        this.Hra = new int[hraSize][hraSize];
        for (int i=0;i<hraSize;i++){
            for (int j =0; i<hraSize;j++){
                Hra[i][j]=tmp.getHraProk(i,j);
            }
        }
    }
 */

    //TODO: Swipovanie

    private View.OnClickListener newGameListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Co treba pri novej hre
        }
    };

    private View.OnClickListener endGameListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Co treba pri end hre
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        //Intent getIntent_ = getIntent();

        newGame = (Button) findViewById(R.id.bGame_NEW);
        endGame = (Button) findViewById(R.id.bGame_END);

        newGame.setOnClickListener(newGameListener);
        endGame.setOnClickListener(endGameListener);
    }
}
