package com.ssharworks.app.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Edit_notes extends AppCompatActivity {

    private AdView mAdView;
    private EditText newTitleName;
    private EditText noteContents;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        newTitleName = (EditText) findViewById(R.id.newTitleName);
        noteContents = (EditText) findViewById(R.id.noteContents);
        saveButton = (Button) findViewById(R.id.btn_save);

        Intent checkingIntent = getIntent();
        String noteTitleFromIntent = checkingIntent.getStringExtra(MainActivity.itemNoteTitle);
        String noteContentFromIntent = checkingIntent.getStringExtra(MainActivity.itemNoteContent);
        if (noteTitleFromIntent != null) {
            newTitleName.setText(noteTitleFromIntent);
            noteContents.setText(noteContentFromIntent);
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