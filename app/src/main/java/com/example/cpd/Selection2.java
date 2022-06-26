package com.example.cpd;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Selection2 extends AppCompatActivity {
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection2);

        textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if( status==TextToSpeech.SUCCESS)
                {
                    int result= textToSpeech.setLanguage(Locale.KOREA);

                    if(result==TextToSpeech.LANG_MISSING_DATA|| result==TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Toast.makeText(Selection2.this,"이 언어는 지원하지 않음.",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        textToSpeech.setPitch(1.1f);
                        textToSpeech.setSpeechRate(1.2f);
                        sppech();
                    }
                }

            }
        });


        ImageButton button= (ImageButton) findViewById(R.id.brailebtn);
        button.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Selection2.this, BraileLoding.class);
                startActivity(intent);
            }

        });

        ImageButton button2= (ImageButton) findViewById(R.id.labelbtn);
        button2.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v1) {
                Intent intent2 = new Intent(Selection2.this, BraileLoding.class);
                startActivity(intent2);
            }

        });

        ImageButton button3= (ImageButton) findViewById(R.id.bothbtn);
        button3.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v2) {
                Intent intent3 = new Intent(Selection2.this, BraileLoding.class);
                startActivity(intent3);
            }

        });

    }
    private void sppech(){
        String text="점자확인, 라벨출력, 동시실행 중 하나를 고르세요";
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
        else
            textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }
    protected void OnStop(){
        super.onStop();
        if(textToSpeech!=null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}