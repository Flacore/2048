package com.example.a2048;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

import java.io.Serializable;

/**
 * Daná trieda slúži na nastavenie parametrov aplikácie.
 * @autor Michal Molitoris
 */
public class Settings extends AppCompatActivity implements Serializable {
   private Data dt;
   private RadioGroup rGroup;
   private RadioButton rButton;
   private Button SVK,ENG,Back;
   private TextView LANG,GAME;

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
     * Funkcia načíta z triedy dáta nastavený (nami zvolený jazyk) a
     * nastavý prvky aplikácie aby sa v tomto jazyku zobrazovali.
     */
    void Zmen(){
        switch (dt.getJazyk()){
            case 1:
                SVK.setText("Slovak");
                ENG.setText("English");
                Back.setText("Back");
                LANG.setText("Language");
                GAME.setText("Game Size");
                break;
            case 2:
                SVK.setText("Slovensky");
                ENG.setText("Anglicky");
                Back.setText("Späť");
                LANG.setText("Jazyk");
                GAME.setText("Velkosť Hry");
                break;
        }
    }

    /**
     * Po stlačný tlačidla prislúchajucemu slovenskemu jazyku sa zmení jazyk v
     * aplikácii a zároveň sa hodnota pre daný jazyk uchová v triede Data.
     */
    private View.OnClickListener svkOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dt.setJazyk(2);
            Zmen();
        }};

     /**
     * Po stlačný tlačidla prislúchajucemu Anglickému jazyku sa zmení jazyk v
     * aplikácii a zároveň sa hodnota pre daný jazyk uchová v triede Data.
     */
    private View.OnClickListener engOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dt.setJazyk(1);
            Zmen();
        }};

    /**
     * Pri stlačný tlačidla späť sa nastavia dáta ktore sa vrátia do rodičovskej aktivity
     * a zároveň sa potom (táto aktivita) zruší.
     */
    private  View.OnClickListener backListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent output = new Intent();
            output.putExtra("Data", dt);
            setResult(RESULT_OK, output);
            finish();
        }
    };

    /**
     *Pomocná funkcia použitá z dôvodu deaktivácie spetného tlačidla (Hardware) na mobilnom zariadení.
     * Táto funkcia nemá žiadny vstupný parameter a zároveň nemá definovanú ani funkcionalitu a teda
     * pri jej vykonávaný ako keby sa dané volanie ignorovalo.
     * */
    @Override
    public void onBackPressed() { }


    /**
     *Funkcia onCreate nám zadefinuje čo sa má všetko vykonať pri spustený danej triedy a ku
     * nej prislúchajúcej Aktivite.
     * @param savedInstanceState
     *
     **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        Intent i = getIntent();
        dt = (Data) i.getSerializableExtra("Data");

        SVK = (Button) findViewById(R.id.SlovakButton) ;
        ENG = (Button) findViewById(R.id.EnglishButton) ;
        Back = (Button) findViewById(R.id.BackButton_Settings) ;

        LANG = (TextView) findViewById(R.id.LanguageText);
        GAME = (TextView) findViewById(R.id.GameSizeText);

        Intent intent = getIntent();

        Zmen();

        Back.setOnClickListener(backListener);
        SVK.setOnClickListener(svkOnClickListener);
        ENG.setOnClickListener(engOnClickListener);

        rGroup = findViewById(R.id.SizeGroup);
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton2x2:
                        dt.setType(2);
                        break;
                    case R.id.radioButton3x3:
                        dt.setType(3);
                        break;
                    case R.id.radioButton4x4:
                        dt.setType(4);
                        break;
                }
            }
        });
    }
}
