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

public class Result extends AppCompatActivity {
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textToSpeech=new TextToSpeech(Result.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if( status==TextToSpeech.SUCCESS)
                {
                    int result= textToSpeech.setLanguage(Locale.KOREA);

                    if(result==TextToSpeech.LANG_MISSING_DATA|| result==TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Toast.makeText(Result.this,"이 언어는 지원하지 않음.",Toast.LENGTH_SHORT).show();
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




        ImageButton button= (ImageButton) findViewById(R.id.rcamerabtn);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Result.this, Recognition.class);
                startActivity(intent);
            }

        });


        ImageButton button1= (ImageButton) findViewById(R.id.rtextbtn);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Result.this, Textedit.class);
                startActivity(intent);
            }

        });
        /*ImageButton button2= (ImageButton) findViewById(R.id.rvoicebtn);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Result.this, Selection2.class);
                startActivity(intent);
            }

        });*/
        ImageButton button3= (ImageButton) findViewById(R.id.rgobtn);
        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Result.this, Selection2.class);
                startActivity(intent);
            }

        });



    }
    private void sppech(){
        String text="결과는 이가탄 입니다.";
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH,null,null);
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