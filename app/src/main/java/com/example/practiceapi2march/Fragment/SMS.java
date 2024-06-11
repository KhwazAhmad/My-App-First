package com.example.practiceapi2march.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.practiceapi2march.R;


public class SMS extends Fragment {

    EditText number, mass;
    TextView send_button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_s_m_s, container, false);
        number = view.findViewById(R.id.textView2);
        mass = view.findViewById(R.id.textView3);
        send_button = view.findViewById(R.id.button1);
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    sendSMS();
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, 10);
                }
            }


        });
        return view;
    }

    private void sendSMS() {
        String phone = number.getText().toString();
        String massage = mass.getText().toString();
        if (!phone.isEmpty() && !massage.isEmpty()) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, massage, null, null);
            Toast.makeText(getActivity(), "send massage", Toast.LENGTH_SHORT).show();
            mass.getText().clear();
        } else {
            Toast.makeText(getActivity(), "Please enter phone no ", Toast.LENGTH_SHORT).show();
        }
    }
}