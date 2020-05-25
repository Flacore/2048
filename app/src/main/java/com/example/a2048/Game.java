package com.example.a2048;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import java.util.Random;
import java.*;

public class Game extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private static final int SETTINGS_REQUEST_CODE=0;

    private Button newGame,endGame;
    private Data dt;
    private Subor sb;
    private int hraSize=0;
    private TextView score,best,cScore,cBest;
    private TextView[][] gameFieldTest;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("Data", dt);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        dt =(Data) savedInstanceState.getSerializable("Data");
        upravZobrazovaciuPlochu();
        Zmen();
    }

    private Activity activity;
    static final int MIN_DISTANCE = 100;
    private float downX, downY, upX, upY;
    private GestureDetector gestureDetector;

    public void pridajPrvok(){
        boolean paPravda=true;
        do{
            int paHelp1 = new Random().nextInt(2);
            int paHelp2 = new Random().nextInt(2);
            if(dt.getHraProk(paHelp1,paHelp2) == 0){
                dt.setPrvokPola(2,paHelp1,paHelp2);
                paPravda = false;
            }
        }while(paPravda);
    }

    //Test na koniec hry
    public boolean testKoniecHry(){
        for (int i = 0; i < dt.getType(); i++) {
            for (int j = 0; j < dt.getType(); j++) {
                if(dt.getHraProk(i,j)==0){
                    return false;
                }
            }
        }
        return true;
    }

    //Skontrolavanie ci moze vykonat pohyb v danom smere
    public boolean swictcher(int paVolba){
        boolean paMoze=false;
        switch(paVolba){
            case 8:
                paMoze=smerHoreTest();
                break;
            case 2:
                paMoze=smerDoleTest();
                break;
            case 6:
                paMoze=smerPravaTest();
                break;
            case 4:
                paMoze=smerLavaTest();
                break;
        }
        return paMoze;
    }


    private boolean smerHoreTest(){
        boolean moznPohyb = false;
        for (int i = 0; i < dt.getType()-1; i++) {
            for (int j = 0; j < dt.getType(); j++) {
                if((dt.getHraProk(i,j) == dt.getHraProk(i+1,j) && dt.getHraProk(i,j)!=0)||(dt.getHraProk(i,j) == 0 && dt.getHraProk(i+1,j)>0)){
                    moznPohyb = true;
                }
            }
        }
        return moznPohyb;
    }

    private boolean smerDoleTest(){
        boolean moznPohyb = false;
        for (int i = dt.getType()-1; i > 0; i--) {
            for (int j = 0; j < dt.getType(); j++) {
                if((dt.getHraProk(i,j) == dt.getHraProk(i-1,j) && dt.getHraProk(i,j) != 0) || (dt.getHraProk(i,j) == 0 && dt.getHraProk(i-1,j)>0)){
                    moznPohyb=true;
                }
            }
        }
        return moznPohyb;
    }

    private boolean smerPravaTest(){
        boolean moznPohyb = false;
        for (int i = 0; i < dt.getType(); i++) {
            for (int j = dt.getType()-1; j > 0; j--) {
                if((dt.getHraProk(i,j) == dt.getHraProk(i,j-1) && dt.getHraProk(i,j)!=0) || (dt.getHraProk(i,j) ==0  && dt.getHraProk(i,j-1)>0)){
                    moznPohyb = true;
                }
            }
        }
        return moznPohyb;
    }

    private boolean smerLavaTest(){
        boolean moznPohyb = false;
        for (int i = 0; i < dt.getType(); i++) {
            for (int j = 0; j < dt.getType()-1; j++) {
                if((dt.getHraProk(i,j) == dt.getHraProk(i,j+1) && dt.getHraProk(i,j)!= 0) ||( dt.getHraProk(i,j) == 0 && dt.getHraProk(i,j+1)>0)){
                    moznPohyb=true;
                }
            }
        }
        return moznPohyb;
    }

    //Uprava smery
    public void doSmer(int paVolba){
        switch(paVolba){
            case 8:
                smerHore();
                break;
            case 2:
                smerDole();
                break;
            case 6:
                smerPrava();
                break;
            case 4:
                smerLava();
                break;
        }
    }

    private void smerHore(){
        for(int opakuj = 0;opakuj<dt.getType()-1;opakuj++){
            for (int i = 0; i < dt.getType()-1; i++) {
                for (int j = 0; j < dt.getType(); j++) {
                    if(dt.getHraProk(i,j) == dt.getHraProk(i+1,j) || dt.getHraProk(i,j) == 0){
                        if(dt.getHraProk(i,j) == dt.getHraProk(i+1,j)){
                            upravScore(dt.getHraProk(i,j) , dt.getHraProk(i+1,j));
                        }
                        dt.setPrvokPola(dt.getHraProk(i,j)+ dt.getHraProk(i+1,j),i,j);
                        dt.setPrvokPola(0,i+1,j);
                    }
                }
            }
        }
    }

    private void smerDole(){
        for(int opakuj = 0;opakuj<dt.getType()-1;opakuj++){
            for (int i = dt.getType()-1; i > 0; i--) {
                for (int j = 0; j < dt.getType(); j++) {
                    if(dt.getHraProk(i,j) == dt.getHraProk(i-1,j) || dt.getHraProk(i,j) == 0){
                        if(dt.getHraProk(i,j) == dt.getHraProk(i-1,j)){
                            upravScore(dt.getHraProk(i,j) , dt.getHraProk(i-1,j));
                        }
                        dt.setPrvokPola(dt.getHraProk(i,j)+ dt.getHraProk(i-1,j),i,j);
                        dt.setPrvokPola(0,i-1,j);
                    }
                }
            }
        }
    }

    private void smerPrava(){
        for(int opakuj = 0;opakuj<dt.getType()-1;opakuj++){
            for (int i = 0; i < dt.getType(); i++) {
                for (int j = dt.getType()-1; j >0; j--) {
                    if(dt.getHraProk(i,j) == dt.getHraProk(i,j-1) || dt.getHraProk(i,j) == 0){
                        if(dt.getHraProk(i,j) == dt.getHraProk(i,j-1)){
                            upravScore(dt.getHraProk(i,j), dt.getHraProk(i,j-1));
                        }
                        dt.setPrvokPola(dt.getHraProk(i,j)+ dt.getHraProk(i,j-1),i,j);
                        dt.setPrvokPola(0,i,j-1);
                    }
                }
            }
        }
    }

    private void smerLava(){
        for(int opakuj = 0;opakuj<dt.getType()-1;opakuj++){
            for (int i = 0; i < dt.getType(); i++) {
                for (int j = 0; j < dt.getType()-1; j++) {
                    if(dt.getHraProk(i,j) == dt.getHraProk(i,j+1) || dt.getHraProk(i,j) == 0){
                        if(dt.getHraProk(i,j) == dt.getHraProk(i,j+1)){
                            upravScore(dt.getHraProk(i,j) , dt.getHraProk(i,j+1));
                        }
                        dt.setPrvokPola(dt.getHraProk(i,j)+ dt.getHraProk(i,j+1),i,j);
                        dt.setPrvokPola(0,i,j+1);
                    }
                }
            }
        }
    }

    //Ziskanie skore
    private void upravScore(int paA, int paB){
        if(paA != 0 && paB != 0)
            upravScore(paA+paB);
    }

    public void upravScore(int paZvacsiO){
        int tmp = dt.getScore();
        tmp+=paZvacsiO;
        dt.setScore(tmp);
    }

    public void setGame(){
        int size_ = dt.getType();

        gameFieldTest = new TextView[size_][size_];

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
                gameFieldTest[i][j].setGravity(Gravity.CENTER);
                Layout_.addView(gameFieldTest[i][j]);
            }
        }
        if(!dt.getExistuje())
            vytvorNovu();
        upravZobrazovaciuPlochu();
    }

    public void vytvorNovu(){
        dt.vytvorPoleHry();
        int size_ = dt.getType();
        for(int i = 0 ; i<size_ ; i++){
            for(int j = 0 ; j<size_ ; j++){
                dt.setPrvokPola(0,i,j);
            }
        }
        pridajPrvok();
        pridajPrvok();
        dt.setScore(0);
        dt.setExituje(true);
    }

    public void upravZobrazovaciuPlochu(){
        int size_ = dt.getType();
        for(int i = 0 ; i<size_ ; i++){
            for(int j = 0 ; j<size_ ; j++){
              gameFieldTest[i][j].setText(String.valueOf(dt.getHraProk(i,j)));
            }
        }
         score.setText(String.valueOf(dt.getScore()));
        switch (dt.getType()){
            case 2:
                 best.setText(String.valueOf(dt.getHigh2x2()));
                break;
            case 3:
                best.setText(String.valueOf(dt.getHigh3x3()));
                break;
            default:
                 best.setText(String.valueOf(dt.getHigh4x4()));
        }
    }

    public void vykonaj(int tmp){
        if(swictcher(tmp)) {
            doSmer(tmp);
            this.upravZobrazovaciuPlochu();
            //TODO: sb.saveGame();
            if (testKoniecHry())
                ukonc();
            pridajPrvok();
        }
    }

    void Zmen(){
        switch (dt.getJazyk()){
            case 1:
                cScore.setText("Score");
                cBest.setText("Best");
                newGame.setText("New Game");
                endGame.setText("End Game");
                break;
            case 2:
                cScore.setText("Skóre");
                cBest.setText("Najlepšie");
                newGame.setText("Nová Hra");
                endGame.setText("Ukonč Hru");
                break;
        }
    }

    private View.OnClickListener newGameListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            vytvorNovu();
            upravZobrazovaciuPlochu();
        }
    };

    public void ukonc(){
            Intent intent =new Intent(Game.this,EndGame.class);
            intent.putExtra("Data", dt);
            startActivityForResult(intent,SETTINGS_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SETTINGS_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            dt =(Data) data.getExtras().getSerializable("Data");
            vytvorNovu();
            upravZobrazovaciuPlochu();
        }
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

        Intent i = getIntent();
        dt = (Data) i.getSerializableExtra("Data");

        score = (TextView) findViewById(R.id.valueScore);
        best = (TextView) findViewById(R.id.valueBestScore);
        cScore = (TextView) findViewById(R.id.constScore);
        cBest = (TextView) findViewById(R.id.constBest);

        newGame = (Button) findViewById(R.id.bGame_NEW);
        endGame = (Button) findViewById(R.id.bGame_END);

        newGame.setOnClickListener(newGameListener);
        endGame.setOnClickListener(endGameListener);

        gestureDetector = new GestureDetector(this);

        Zmen();
        setGame();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
        boolean result =false;
        float diffY = moveEvent.getY()-downEvent.getY();
        float diffX = moveEvent.getX()-downEvent.getX();
        if(Math.abs(diffX)>Math.abs(diffY)){
            if(Math.abs(diffX)>300&& Math.abs(velocityX)>300)
                if(diffX >0){
                    //V pravo
                    vykonaj(6);
                }else{
                    //Do lava
                    vykonaj(4);
                }
            result = true;
        }else{
            if(Math.abs(diffY)>300&& Math.abs(velocityY)>300)
                if(diffY >0){
                    //Dole
                    vykonaj(2);
                }else{
                    //Hore
                    vykonaj(8);
                }
            result = true;
        }

        return result;
    }
}
