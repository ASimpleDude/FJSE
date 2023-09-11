package com.example.japanesequizappversion2.StudyScreen;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.japanesequizappversion2.R;
import com.example.japanesequizappversion2.StudyScreen.Fragment.HandWritingFragment;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class PatternHandWritingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_japanese_hand_writing);
        HandWritingFragment fragment = new HandWritingFragment();

        ImageView imageView = findViewById(R.id.image_view);

// Get a reference to the Firebase Storage instance
        FirebaseStorage storage = FirebaseStorage.getInstance();
        String gifUrl = getIntent().getStringExtra("gifUrl");
        String textModel = getIntent().getStringExtra("textModel");
// Create a reference to the GIF image in Firebase Storage
        StorageReference ref = storage.getReference().child(gifUrl);

// Load the GIF image from Firebase Storage
        ref.getBytes(Long.MAX_VALUE).addOnSuccessListener(bytes -> {
            try {
                GifDrawable gifDrawable = new GifDrawable(bytes);
                imageView.setImageDrawable(gifDrawable);

                // Set the GIF image to automatically replay
                gifDrawable.setLoopCount(5);
                gifDrawable.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).addOnFailureListener(exception -> {
            // Handle any errors that may occur while loading the GIF image from Firebase Storage
        });
// Pass the textModel to the fragment
        Bundle bundle = new Bundle();
        bundle.putString("textModel", textModel);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}