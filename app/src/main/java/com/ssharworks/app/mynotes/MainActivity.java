package com.ssharworks.app.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    private Button newNoteBtn;
    private ListView listView;
    private static final String TAG = "MyNoteActivity";

    public int iCounter = 0;

    static ArrayList<String> arrayList = new ArrayList<>();
    static ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,arrayList);


        Log.d(TAG, "newNoteBtn initialize");
        newNoteBtn = (Button) findViewById(R.id.newNote);
        newNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCounter = 0;
                Intent intent = new Intent(MainActivity.this, NewNote.class);
                startActivity(intent);
            }
        });


        Intent check_intent = getIntent();
        String title_temp = check_intent.getStringExtra(NewNote.EXTRA_TITLE_TEXT);
        if(title_temp != null && iCounter != 1) {
            arrayList.add(title_temp);
            listView.setAdapter(arrayAdapter);
            iCounter = 1;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}