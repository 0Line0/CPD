package com.example.cpd;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private TextToSpeech textToSpeech;

    public static final int sub=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if( status==TextToSpeech.SUCCESS)
                {
                    int result= textToSpeech.setLanguage(Locale.KOREA);

                    if(result==TextToSpeech.LANG_MISSING_DATA|| result==TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Toast.makeText(MainActivity.this,"이 언어는 지원하지 않음.",Toast.LENGTH_SHORT).show();
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

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), ActivityWay0.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

    }

    private void sppech(){
        String text="어닷 매니저에 오신것을 환영합니다.";
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

    protected void onPause(){
        super.onPause();
        finish();
    }




}
