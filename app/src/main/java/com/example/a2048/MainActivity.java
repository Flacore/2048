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

public class MainActivity extends AppCompatActivity {

    private Button close,settings,newGame,loadGame;
    private ActionBar actionBar;

    private Data dt = new Data();
    private Subor sb = new Subor(dt);

    //TODO: Nacitaj Databazu
    private void Nacitanie(){

        //Nacitaj hru z databazy
    }

    //TODO Oprav
    private void Zmen(){

        switch (dt.getJazyk()){
            case 1:
                close.setText("Close");
                settings.setText("Settings");
                newGame.setText("NEW GAME");
                loadGame.setText("Load Game");
                break;
            case 2:
                close.setText("Zatvor hru");
                settings.setText("Nastavenia");
                newGame.setText("Nová hra");
                loadGame.setText("Načítaj hru");
                break;
        }
    }

    //TODO Uprav
    private View.OnClickListener closeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            //Treba ulozit progres
            System.exit(0);
        }
    };

    //TODO Uprav
    private View.OnClickListener settingsOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Otvor nastavenia
            settingActivity();
            //Vykonaj zmeny
        }
    };

    //TODO Uprav
    private View.OnClickListener newGameOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int Druh=dt.getType();
            for (int i = 0; i < Druh; i++) {
                for (int j = 0; j < Druh; j++) {
                    //Vytvorenie hry
                    dt.setPrvokPola(0, i, j);
                }
            }
            //Uloz novu hru
            //Otvor konkretnu hru
            openGame(Druh);
        }
    };

    //TODO Uprav
    private View.OnClickListener loadGameOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int Druh = 2;
            //Nacitaj hru
            if(dt.getExistuje()){
                openGame(Druh);
            }else{
                switch (dt.getJazyk()){
                    case 1:
                        Toast.makeText(MainActivity.this,"Can´t load game!",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this,"Nemožem načítať hru!",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    };

    private void settingActivity(){
        Intent intent = new Intent(MainActivity.this, Settings.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("dtData", dt);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //TODO: otvaranie hry
    private void openGame(int Druh){
        //setContentView(R.layout.game_activity);
        Intent intent =new Intent(MainActivity.this,Game.class);
        startActivity(intent);
    }
    private void openGame(){
        openGame(3);
    }

    @Override
    public void onBackPressed() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        close = (Button) findViewById(R.id.close) ;
        settings = (Button) findViewById(R.id.settings) ;
        newGame = (Button) findViewById(R.id.newGame) ;
        loadGame = (Button) findViewById(R.id.loadGame) ;

        Nacitanie();
        Zmen();

        close.setOnClickListener(closeOnClickListener);
        settings.setOnClickListener(settingsOnClickListener);
        newGame.setOnClickListener(newGameOnClickListener);
        loadGame.setOnClickListener(loadGameOnClickListener);
    }



}
