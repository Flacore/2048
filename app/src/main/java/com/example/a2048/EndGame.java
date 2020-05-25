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


public class EndGame extends AppCompatActivity {
    private Data dt;
    private Subor sb;
    private Button bNewGame,bMainMenu,bEndGame;
    private TextView tHighScore,tScoreValue,tScore;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("Data", dt);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        dt =(Data) savedInstanceState.getSerializable("Data");
        Zmen();
    }

    //Zrusenie navratu
    @Override
    public void onBackPressed() { }

    void Zmen(){
        String text1 ="",text2="";
        switch (dt.getJazyk()){
            case 1:
                bNewGame.setText("New Game");
                bMainMenu.setText("Main Menu");
                bEndGame.setText("End Game");
                tHighScore.setText("New High Score");
                tScore.setText("Score:");
                text1="You create new record,Congratulation!";
                text2="Your record is: ";
                break;
            case 2:
                bNewGame.setText("Nová Hra");
                bMainMenu.setText("Hlavné Menu");
                bEndGame.setText("Ukončiť");
                tHighScore.setText("Nový Rekord");
                tScore.setText("Skóre:");
                text1="Gratulujem dosiahol si nové najväčšie skóre!";
                text2="Tvôj rekord je: ";
                break;
        }
        tScoreValue.setText(String.valueOf(dt.getScore()));
        int tmp = 0;
        switch(dt.getType()){
            case 2:
                tmp = dt.getHigh2x2();
                break;
            case 3:
                tmp = dt.getHigh3x3();
                break;
            default:
                tmp = dt.getHigh4x4();
        }
        if(tmp < dt.getScore()){
            Toast.makeText(EndGame.this,text1,Toast.LENGTH_SHORT).show();
            tHighScore.setVisibility(View.VISIBLE);
            switch(dt.getType()){
                case 2:
                    dt.setHigh2x2(dt.getScore());
                    break;
                case 3:
                    dt.setHigh3x3(dt.getScore());
                    break;
                default:
                    dt.setHigh4x4(dt.getScore());
            }
        }else{
            if(!(tmp == dt.getScore()))
                Toast.makeText(EndGame.this,text2+tmp,Toast.LENGTH_SHORT).show();
            tHighScore.setVisibility(View.INVISIBLE);
        }
    }

    private View.OnClickListener bNewGameOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO: sb.saveSettings();
            Intent output = new Intent();
            output.putExtra("Data", dt);
            setResult(RESULT_OK, output);
            finish();
        }
    };
    private View.OnClickListener bMainMenuOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO: sb.saveSettings();
            Intent intent  = new Intent(EndGame.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Data", dt);
            startActivity(intent);
        }
    };
    private View.OnClickListener bEndGameOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO: sb.saveSettings();
            finishAffinity();
            System.exit(0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endgame_activity);

        Intent i = getIntent();
        dt = (Data) i.getSerializableExtra("Data");

        dt.setExituje(false);

        bNewGame = (Button) findViewById(R.id.NewGameButton_EndGame);
        bMainMenu = (Button) findViewById(R.id.MainMenuButton_EndGame);
        bEndGame = (Button) findViewById(R.id.EndButton_EndGame);

        bNewGame.setOnClickListener(bNewGameOnClickListener);
        bMainMenu.setOnClickListener(bMainMenuOnClickListener);
        bEndGame.setOnClickListener(bEndGameOnClickListener);

        tHighScore = (TextView) findViewById(R.id.HighScoreText);
        tScoreValue = (TextView) findViewById(R.id.ScoreValueText);
        tScore = (TextView) findViewById(R.id.ScoreText);

        //TODO: sb.deleteGame();
        Zmen();
    }

}
