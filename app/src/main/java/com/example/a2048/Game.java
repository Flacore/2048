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
    private int hraSize=0;

    //TODO: nastavenie hry
    public void setGame(){
        int size_ = dt.getType();
        //Vytvor plochu
        upravZobrazovaciuPlochu();
    }

    //TODO: Uprav plochu
    public void upravZobrazovaciuPlochu(){
        int size_ = dt.getType();
        //Napln vytvorenu plochu z data;
    }

    //TODO: Swipovanie
    //TODO: Naklananie hry

    //TODO: Resetovanie hry
    private View.OnClickListener newGameListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Co treba pri novej hre
        }
    };

    //TODO: ukoncenie hry predcasne
    private View.OnClickListener endGameListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Co treba pri end hre
        }
    };

    //Zrusenie navratu
    @Override
    public void onBackPressed() { }

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
