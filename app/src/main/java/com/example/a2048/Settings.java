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
import org.w3c.dom.Text;

public class Settings extends AppCompatActivity {
   private Data dt;

    private Button SVK,ENG,Back;
    private Text LANG,GAME;

    //TODO: zmen jazyk nastaveni
    void Zmen(){
        switch (dt.getJazyk()){
            case 1:
                SVK.setText("Slovak");
                ENG.setText("English");
                Back.setText("Back");
                //LANG.setTextContent("Language");
                //GAME.setTextContent("game size");
                break;
            case 2:
                SVK.setText("Slovensky");
                ENG.setText("Anglicky");
                Back.setText("Späť");
                //LANG.setTextContent("Jazyk");
                //GAME.setTextContent("Velkosť hry");
                break;
        }
    }


    //TODO: zmena jazyku na slovencinu
    private View.OnClickListener svkOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //dt.setJazyk(2);
            //Zmen();
        }};

    //TODO: zmena jazyku na anglictinu
    private View.OnClickListener engOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //dt.setJazyk(1);
            //Zmen();
        }};

    //TODO: navrat do hlavneho menu
    private  View.OnClickListener backListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            //Close activity
            //Save to Data...
        }
    };

    //Zrusenie navratu
    @Override
    public void onBackPressed() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        SVK = (Button) findViewById(R.id.SlovakButton) ;
        ENG = (Button) findViewById(R.id.EnglishButton) ;
        Back = (Button) findViewById(R.id.BackButton_Settings) ;

        //LANG = (Text) findViewById(R.id.LanguageText);
        //GAME = (Text) findViewById(R.id.GameSizeText);


        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        dt = (Data) bundle.getSerializable("dtData");

        Zmen();

        Back.setOnClickListener(backListener);
        SVK.setOnClickListener(svkOnClickListener);
        ENG.setOnClickListener(engOnClickListener);

        //TODO: ziskaj hodnotu hry a uloz ju
        //Radio button
    }
}
