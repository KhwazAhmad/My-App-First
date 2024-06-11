package com.example.practiceapi2march.Fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.practiceapi2march.R;

public class Multiple extends Fragment {
    TextView start;
    EditText editText, editText2;
    TextView textView;
    String text = "";
    String tv = null;
    ImageButton image_copy, image_share;
    int no = 0;
    ImageButton buttonSpeak;
    private TextToSpeech tts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.multiple, container, false);
        image_copy = view.findViewById(R.id.copy);

        buttonSpeak = view.findViewById(R.id.mic);
        image_share = view.findViewById(R.id.share);
        editText = view.findViewById(R.id.textView1);
        editText2 = view.findViewById(R.id.textView2);
        start = view.findViewById(R.id.button1);
        textView = view.findViewById(R.id.text);
        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                start();
            }
        });
        image_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = text;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        image_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(getContext().CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", text);
                clipboard.setPrimaryClip(clip);
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e_text = editText.getText().toString();
                String e_no = editText2.getText().toString();
                Animation animation = new TranslateAnimation(-20.0f, 20.f, 0.f, 0.f);
                animation.setDuration(1000);
                if (e_text.isEmpty()) {
                    editText.setError("Text is empty");
                    editText.setAnimation(animation);
                    editText.requestFocus();
                } else if (e_no.isEmpty()) {
                    editText2.setError("No is empty");
                    editText2.setAnimation(animation);
                    editText2.requestFocus();
                } else {

                    no = Integer.parseInt(e_no);
                    if (text != null) {
                        text = "";
                    }
                    for (int i = 1; i <= no; i++) {
                        text = text + e_text + "\n";
                    }
                    textView.setText(text);
                }
            }
        });

        return view;
    }
//    private void start() {
//        String text = editText.getText().toString();
//        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
//);
//    }
//    public void onInit(int status) {
//        if (status == TextToSpeech.SUCCESS) {
//            int result = tts.setLanguage(Locale.US);
//
//            if (result == TextToSpeech.LANG_MISSING_DATA
//                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//                Log.e("TTS", "This Language is not supported");
//            } else {
//                buttonSpeak.setEnabled(true);
//                start();
//            }

//        } else {
//            Log.e("TTS", "Initilization Failed!");
//        }
}
