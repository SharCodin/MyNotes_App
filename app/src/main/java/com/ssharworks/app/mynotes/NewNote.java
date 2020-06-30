package com.ssharworks.app.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NewNote extends AppCompatActivity {

    public static final String EXTRA_TITLE_TEXT = "com.ssharworks.app.mynotes.note.title";

    private AdView mAdView;
    private EditText newTitleName;
    private EditText noteContents;
    private Button saveButton;

    private String final_title;
    private String final_content;

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

                final_title = newTitleName.getText().toString();
                final_content = noteContents.getText().toString();

                saveContent();

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

    private void saveContent() {
        try {
            Log.d(TAG, "Trying to write file");
            FileOutputStream fileOutputStream = getApplicationContext().openFileOutput(final_title, Context.MODE_PRIVATE);
            fileOutputStream.write(final_content.getBytes());
            Log.d(TAG, "writing completed");
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "file not found exception");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d(TAG, "io exception");
            e.printStackTrace();
        }
    }

}