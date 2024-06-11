package com.example.practiceapi2march.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practiceapi2march.Adopter.Contect_Adopter;
import com.example.practiceapi2march.Model.Model_Read_Contect;
import com.example.practiceapi2march.R;

import java.util.ArrayList;

/**
 *
 */
public class Contect extends Fragment {
    RecyclerView recyclerView;
    Contect_Adopter contect_adopter;
    Context context;
    ArrayList<Model_Read_Contect> model_read_contectArrayList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.contect, container, false);
        recyclerView = view.findViewById(R.id.C_recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        contect_adopter = new Contect_Adopter(getContext(), model_read_contectArrayList);
        recyclerView.setAdapter(contect_adopter);
        getContact();
        return view;

    }

    @SuppressLint("NotifyDataSetChanged")

    private void getContact() {
//.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        Cursor phones = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            @SuppressLint("Range") String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            @SuppressLint("Range") String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            @SuppressLint("Range") String image = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
            model_read_contectArrayList.add(new Model_Read_Contect(name, phoneNumber, image));
        }
        contect_adopter.notifyDataSetChanged();
        phones.close();
    }
}