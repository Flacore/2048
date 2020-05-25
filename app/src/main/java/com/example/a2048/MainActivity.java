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

/**
 *Hlavná trieda ktorá zobrzí menu (activity_main), a uživatel si pomocou tohto menu zvolí čo si želá aby sa dialo ďalej.
 *@Autor Michal Molitoris
 */
public class MainActivity extends AppCompatActivity {

    private static final int SETTINGS_REQUEST_CODE=0;

    private Button close,settings,newGame,loadGame;
    private ActionBar actionBar;

    private Data dt = new Data();

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

    /**
     * Funkcia slúžiaca na ukončenie aplikácie po stlačený príslušného tlačidla.
     */
    private View.OnClickListener closeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            System.exit(0);
        }
    };

    /**
     * Po stlačný tlačídla nastavenia sa zobrazí menu pre nastavenia.
     */
    private View.OnClickListener settingsOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            settingActivity();

        }
    };


    /**
     * Vytvorý novú hru z prázdnym herným polom.
     */
    private View.OnClickListener newGameOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dt.setExituje(false);
            openGame(dt.getType());
        }
    };

    /**
     * Ak máme predchádzajúcu nedohranú hru pre danú velkosť hracieho poľa, táto funkcia nám
     * zavolá hernú aktivitu a načíta do nej danú hru.
     * V prípade že sa jej to nepodarý a teda neexistuje hra ktorú by mohla načítať, informuje o
     * tom uživatela.
     */
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

    /**
    * Vytváranie novej aktivity ktorá nám slúži na definovanie nastavený aplikácie ako sú jazyk
     * a velkosť hracej plochy. Zároveň pri tomto vytváraný posúvame triedu dáta.
    */
    private void settingActivity(){
        Intent intent = new Intent(MainActivity.this, Settings.class);
        intent.putExtra("Data", dt);
        startActivityForResult(intent,SETTINGS_REQUEST_CODE);
    }

    /**
     * Funkcie onActiveResult slúži na ziskanie dát z activity result.
     * @param requestCode Špecifický kód ktorý slúži aby sme vedeli čo príjmame.
     * @param resultCode
     * @param data  Dáta ktoré získavame z ukončenej Activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SETTINGS_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            dt =(Data) data.getExtras().getSerializable("Data");
            Zmen();
        }
    }

    /**
     * Funkcia vytvorý novú aktivitu pre hru ktorá predstavuje hraciu plochu a zároveň
     * jej posunie triedu Data (dt) v ktorej sú uložené počas behu programu všetky dôležité
     * parametre.
     * @param Druh Určuje o akú hru sa jedná (Rozmer herného poľa)
     */
    private void openGame(int Druh){
        Intent intent =new Intent(MainActivity.this,Game.class);
        intent.putExtra("Data", dt);
        startActivity(intent);
    }

    /**
     *Pomocná funkcia ktorá nám zaisťuje volanie funkcie z parametrom v prípade že danú funkciu
     * voláme bez parametra. Potom táto funkcia zavolá funkciu openGame() z defaultným parametrom.
    */
    private void openGame(){
        openGame(3);
    }

    /**
     *Pomocná funkcia použitá z dôvodu deaktivácie spetného tlačidla (Hardware) na mobilnom zariadení.
     * Táto funkcia nemá žiadny vstupný parameter a zároveň nemá definovanú ani funkcionalitu a teda
     * pri jej vykonávaný ako keby sa dané volanie ignorovalo.
     * */
    @Override
    public void onBackPressed() { }

    /**
     *Funkcia onCreate nám zadefinuje čo sa má všetko vykonať pri spustený hlavnej triedy a ku
     * nej prislúchajúcej Activite.
     * @param savedInstanceState
     *
     **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        if(i.getSerializableExtra("Data")!=null)
            dt = (Data) i.getSerializableExtra("Data");

        close = (Button) findViewById(R.id.close) ;
        settings = (Button) findViewById(R.id.settings) ;
        newGame = (Button) findViewById(R.id.newGame) ;
        loadGame = (Button) findViewById(R.id.loadGame) ;

        Zmen();

        close.setOnClickListener(closeOnClickListener);
        settings.setOnClickListener(settingsOnClickListener);
        newGame.setOnClickListener(newGameOnClickListener);
        loadGame.setOnClickListener(loadGameOnClickListener);
    }



}
