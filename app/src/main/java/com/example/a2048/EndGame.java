package com.example.a2048;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;

//TODO: Zoznam co treba urobit
/*
    1.Zmen jazyk podla zvoleneho
    2.Zobraz nahrane skore
    3.Zobraz doposial najvacsie skore
    4.Ak je skore najvecsie vypis gratulaciu
    5.Uloz score ak je najvacsie
    6.Nastav vsetky prvky na nulo v databaze pre danu hru
 */

public class EndGame extends AppCompatActivity {
    private Data dt;

    //TODO: Preklad

    //Zrusenie navratu
    @Override
    public void onBackPressed() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endgame_activity);

        //Intent getIntent_ = getIntent();
    }

}
