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

public class Settings extends AppCompatActivity {
   private Data dt;
   private RadioGroup rGroup;
   private RadioButton rButton;
   private Button SVK,ENG,Back;
   private TextView LANG,GAME;

    void Zmen(){
        switch (dt.getJazyk()){
            case 1:
                SVK.setText("Slovak");
                ENG.setText("English");
                Back.setText("Back");
                LANG.setText("Language");
                GAME.setText("game size");
                break;
            case 2:
                SVK.setText("Slovensky");
                ENG.setText("Anglicky");
                Back.setText("Späť");
                LANG.setText("Jazyk");
                GAME.setText("Velkosť hry");
                break;
        }
    }


    private View.OnClickListener svkOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dt.setJazyk(2);
            Zmen();
        }};

    private View.OnClickListener engOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dt.setJazyk(1);
            Zmen();
        }};

    //TODO: navrat do hlavneho menu
    private  View.OnClickListener backListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            finish();
        }
    };

    @Override
    public void onBackPressed() { }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        SVK = (Button) findViewById(R.id.SlovakButton) ;
        ENG = (Button) findViewById(R.id.EnglishButton) ;
        Back = (Button) findViewById(R.id.BackButton_Settings) ;

        LANG = (TextView) findViewById(R.id.LanguageText);
        GAME = (TextView) findViewById(R.id.GameSizeText);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        dt = (Data) bundle.getSerializable("dtData");

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
