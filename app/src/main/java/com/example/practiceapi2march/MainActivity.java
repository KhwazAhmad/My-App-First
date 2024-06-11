package com.example.practiceapi2march;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.practiceapi2march.Fragment.Contect;
import com.example.practiceapi2march.Fragment.Multiple;
import com.example.practiceapi2march.Fragment.News;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DrawerLayout drawerLayout;
    BottomNavigationView bottom;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView imageView;
    AlertDialog alert;
    FloatingActionButton floatingActionButton;
    private TextToSpeech tts;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame, new News());
        fragmentTransaction.commit();
        drawerLayout = findViewById(R.id.my_drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolBare);
        imageView = findViewById(R.id.imBack);
        bottom = findViewById(R.id.bottomNavigationView);
        floatingActionButton = findViewById(R.id.fab);
        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 11);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "https://play.google.com/store/apps/details?id=my packagename" + "\n" + " " + "the app is available soon";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, new News());
                fragmentTransaction.commit();
            }
        });
        bottom.setBackground(null);
        setSupportActionBar(toolbar);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FAF5F5"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        bottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                fragmentTransaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.News:
                        onOptionsItemSelected(item);
                        fragmentTransaction.replace(R.id.frame, new News());

                        break;
                    case R.id.Memes:
                        onOptionsItemSelected(item);
                        fragmentTransaction.replace(R.id.frame, new Memes());

                        break;
                    case R.id.Contact:
                        onOptionsItemSelected(item);
                        fragmentTransaction.replace(R.id.frame, new Contect());

                        break;
                    case R.id.Bprofile:
                        onOptionsItemSelected(item);
                        fragmentTransaction.replace(R.id.frame, new Multiple());

                        break;
                }
                fragmentTransaction.commit();

                return true;
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                fragmentTransaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.D_News:
                        onOptionsItemSelected(item);
                        fragmentTransaction.replace(R.id.frame, new News());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.D_Memes:
                        onOptionsItemSelected(item);
                        fragmentTransaction.replace(R.id.frame, new Memes());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.D_Contect:
                        onOptionsItemSelected(item);
                        fragmentTransaction.replace(R.id.frame, new Contect());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.D_profile:
                        onOptionsItemSelected(item);
                        fragmentTransaction.replace(R.id.frame, new Multiple());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.D_share:
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = "https://play.google.com/store/apps/details?id=my packagename" + "\n" + " " + "the app is available soon";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        break;
                    case R.id.D_Ratting:
                        Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                        i.setData(Uri.parse("https://play.google.com/store/apps/details?id=my packagename "));
                        startActivity(i);
                        break;
                }
                fragmentTransaction.commit();
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?").setTitle("Exit Box").setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        alert = builder.create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.green));
                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.red));
            }
        });
        alert.show();
    }

}