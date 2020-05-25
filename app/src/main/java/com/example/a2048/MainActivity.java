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

    private void Nacitanie(){
        sb.loadData();
        sb.loadGame(dt.getType());
    }

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

    private View.OnClickListener closeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            sb.saveSettings();
            System.exit(0);
        }
    };

    private View.OnClickListener settingsOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            settingActivity();
            sb.saveSettings();
            Nacitanie();
        }
    };


    private View.OnClickListener newGameOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dt.setExituje(false);
            openGame(dt.getType());
        }
    };

    private View.OnClickListener loadGameOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int Druh = 2;
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
        intent.putExtra("Data", dt);
        startActivity(intent);
    }

    private void openGame(int Druh){
        Intent intent =new Intent(MainActivity.this,Game.class);
        intent.putExtra("Data", dt);
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
