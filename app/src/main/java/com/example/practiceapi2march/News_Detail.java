package com.example.practiceapi2march;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Locale;

public class News_Detail extends AppCompatActivity implements TextToSpeech.OnInitListener {
    TextView title, des, content, Urll;
    ImageView image, buttonSpeak;
    ;
    String Data_title;
    String Data_content;
    private TextToSpeech tts;
    String Data_des;
    private static String speed = "Normal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title = findViewById(R.id.N_title);
        des = findViewById(R.id.N_descrip);
        content = findViewById(R.id.N_content);
        tts = new TextToSpeech(this, this);
        buttonSpeak = findViewById(R.id.mice);
        image = findViewById(R.id.image);
        Urll = findViewById(R.id.N_Url);
        String Data_Url = getIntent().getStringExtra("Url");
        Urll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlString = Data_Url;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.android.chrome");
                startActivity(intent);
            }
        });
        Data_title = getIntent().getStringExtra("title");
        Data_des = getIntent().getStringExtra("des");
        Data_content = getIntent().getStringExtra("content");

        String Data_image = getIntent().getStringExtra("image");
        title.setText(Data_title);
        des.setText(Data_des);
        content.setText(Data_content);
//        Urll.setText(Data_Url);
        Picasso.get().load(Data_image).into(image);
        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
                setSpeed();
            }
        });
    }

    private void start() {
        String all = "Title" + "" + Data_title + "" + "Content" + "" + Data_content + "" + "Description" + "" + Data_des + " For Full Read Click the below button";
        tts.speak(all, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void onDestroy() {
// Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                buttonSpeak.setEnabled(true);
//                start();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    private void setSpeed() {
//        if(speed.equals("Very Slow")){
//            tts.setSpeechRate(0.1f);
//        }
//        if(speed.equals("Slow")){
//            tts.setSpeechRate(0.5f);
//        }
//        if(speed.equals("Normal")){
//            tts.setSpeechRate(1.0f);//default 1.0
//        }
//        if(speed.equals("Fast")){
//            tts.setSpeechRate(1.5f);
//        }
//        if(speed.equals("Very Fast")){


        tts.setSpeechRate(0.6f);
//        }
        //for setting pitch you may call
        //tts.setPitch(1.0f);//default 1.0
    }
}