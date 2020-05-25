package com.example.a2048;

import android.app.ActionBar;
import android.app.NotificationChannel;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.NotificationManager;

import android.support.v7.app.AppCompatActivity;


/**
 * Konečná trieda v dedičnosti aktivít v ktorej sa rozhodujeme ako ďalej pokračovať.
 * @autor Michal Molitoris
 */
public class EndGame extends AppCompatActivity {

    private NotificationCompat.Builder nHScoreWJ;

    private Data dt;
    private Button bNewGame,bMainMenu,bEndGame;
    private TextView tHighScore,tScoreValue,tScore;

    /**
     * Inicializovanie dát pre novú aktivitu ktorá sa vytvorí
     * po otočený displeja. Slúži na uchovanie pôvodných nastavený aplikácie.
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("Data", dt);
    }

    /**
     * Získanie uchovaných dát po zmene orientácie displeja.
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        dt =(Data) savedInstanceState.getSerializable("Data");
        Zmen();
    }


    /**
     *Pomocná funkcia použitá z dôvodu deaktivácie spetného tlačidla (Hardware) na mobilnom zariadení.
     * Táto funkcia nemá žiadny vstupný parameter a zároveň nemá definovanú ani funkcionalitu a teda
     * pri jej vykonávaný ako keby sa dané volanie ignorovalo.
     * */
    @Override
    public void onBackPressed() { }

    /**
     *Zmení jazyk danej aktivity a zároveň nastavý všetky polia ako sú
     * skóre a ukazovatel či sme prekročili doposial najväčšie skóre a ak
     * áno zagratuluje nám formou achivementu (Notifikácia).
     */
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
            if(!dt.getZolik()) {
                NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(100,nHScoreWJ.build());
            }
        }else{
            if(!(tmp == dt.getScore()))
                Toast.makeText(EndGame.this,text2+tmp,Toast.LENGTH_SHORT).show();
            tHighScore.setVisibility(View.INVISIBLE);
        }
    }

    /**
     *Po stlačený tlačidla nová hra sa vrátime o jednu aktivitu naspäť
     * a prestavíme jej parametre aby sa tvárila ako keby začneme novú hru a
     * tým pádom aj novú aktivitu.
     */
    private View.OnClickListener bNewGameOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent output = new Intent();
            output.putExtra("Data", dt);
            setResult(RESULT_OK, output);
            finish();
        }
    };

    /**
     *Zruší všetky aktivity a inicializuje novú (Hlavné Menu) čo vo
     * finále vyzerá ako keby sme sa vrátili na začiatok a aj sa tak potom chová.
     */
    private View.OnClickListener bMainMenuOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent  = new Intent(EndGame.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Data", dt);
            startActivity(intent);
        }
    };

    /**
     *Zruší všetky doposial vytvorené aktivity a  ukončí beh aplikácie.
     */
    private View.OnClickListener bEndGameOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finishAffinity();
            System.exit(0);
        }
    };

    /**
     *Funkcia onCreate nám zadefinuje čo sa má všetko vykonať pri spustený danej triedy a ku
     * nej prislúchajúcej Aktivite.
     * @param savedInstanceState
     *
     **/
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

        createNotificationChannelHS();
        notificationHighScore();

        Zmen();
    }

    /**
     *Nastavenie parametrov notifikácie (Ako bude notifikácia vyzerať).
     */
    protected void notificationHighScore(){
        String text1 = "",text2="";
        switch (dt.getJazyk()){
            case 2:
                text1="Ty si král!";
                text2="Práve si prekonal svôj rekord bez Žolíka!";
                break;
            default:
                text1="You are the King!";
                text2="You break your record without using Joker!";
        }
        nHScoreWJ = new NotificationCompat.Builder(this, "HighScoreID")
                .setSmallIcon(R.drawable.ic_king)
                .setContentTitle(text1)
                .setContentText(text2)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }

    /**
     *Nastavenie notifikácie.
     */
    private void createNotificationChannelHS() {
        CharSequence name ="HS_without_Jouker";
        String description = "Chanel for High Score without using Joker notification";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("HighScoreID", name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

}
