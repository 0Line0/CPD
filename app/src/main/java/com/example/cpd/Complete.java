package com.example.cpd;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Complete extends AppCompatActivity {
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if( status==TextToSpeech.SUCCESS)
                {
                    int result= textToSpeech.setLanguage(Locale.KOREA);

                    if(result==TextToSpeech.LANG_MISSING_DATA|| result==TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Toast.makeText(Complete.this,"이 언어는 지원하지 않음.",Toast.LENGTH_SHORT).show();
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
    }
    private void sppech(){
        String text="완료되었습니다.";
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