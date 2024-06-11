package com.example.practiceapi2march.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practiceapi2march.Model.Model_Read_Contect;
import com.example.practiceapi2march.R;
import com.example.practiceapi2march.Fragment.SMS;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Contect_Adopter extends RecyclerView.Adapter<Contect_Adopter.itemHOlder> {
    Context context;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    public Contect_Adopter(Context context, ArrayList<Model_Read_Contect> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    ArrayList<Model_Read_Contect> arrayList;

    @NonNull
    @Override
    public itemHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.contect_item, parent, false);
        return new itemHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemHOlder holder, int position) {
        Model_Read_Contect model_read_contect = arrayList.get(position);
        holder.setitem(model_read_contect);
        ImageView imageView=holder.itemView.findViewById(R.id.call);
        TextView textView=holder.itemView.findViewById(R.id.no);
        ImageView image_massage=holder.itemView.findViewById(R.id.massage);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//
//               context. startActivity(intent);
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(model_read_contect.getNo()));
//                context.startActivity(intent);
                Toast.makeText(context, "Call", Toast.LENGTH_SHORT).show();
            }


        });
        image_massage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Fragment fragment = new AttendanceFragment();
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, new SMS());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class itemHOlder extends RecyclerView.ViewHolder {
        TextView name, no;
        ImageView imageView;

        public itemHOlder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            no = itemView.findViewById(R.id.no);
            imageView = itemView.findViewById(R.id.image);
        }

        public void setitem(Model_Read_Contect model_read_contect) {
            name.setText(model_read_contect.getName());
            no.setText(model_read_contect.getNo());
            if (model_read_contect.getImage() != null) {
                Picasso.get().load(model_read_contect.getImage()).into(imageView);
            }
        }
    }

}
