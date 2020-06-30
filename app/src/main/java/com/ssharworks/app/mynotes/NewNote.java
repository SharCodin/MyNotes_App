package com.ssharworks.app.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class NewNote extends AppCompatActivity {

    public static final String EXTRA_TITLE_TEXT = "com.ssharworks.app.mynotes.note.title";

    private AdView mAdView;
    private EditText newTitleName;
    private EditText noteContents;
    private Button saveButton;

    private static final String TAG = "NewNoteTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        newTitleName = (EditText) findViewById(R.id.newTitleName);
        noteContents = (EditText) findViewById(R.id.noteContents);

        saveButton = (Button) findViewById(R.id.btn_save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String final_title = newTitleName.getText().toString();
                noteContents.getText().toString();

                Log.d(TAG, "final_title is " + final_title);

                Intent intent = new Intent(NewNote.this, MainActivity.class);
                intent.putExtra(EXTRA_TITLE_TEXT, final_title);
                startActivity(intent);
            }
        });

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