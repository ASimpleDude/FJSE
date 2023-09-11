package com.example.japanesequizappversion2.StudyScreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.japanesequizappversion2.Database.SessionManager;
import com.example.japanesequizappversion2.MainScreen.MainActivity;
import com.example.japanesequizappversion2.Model.User;
import com.example.japanesequizappversion2.R;

import java.util.HashMap;

public class HiraganaKatakanaSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiragana_katakana_selection);
//        findViewById(R.id.viewBack).setOnClickListener(v->
//                startActivity(new Intent(HiraganaKatakanaSelectionActivity.this, MainActivity.class)));
    }
    public void chooseHiragana(View view){
        Intent intent = new Intent(this, HiraganaAlphabetActivity.class);
        startActivity(intent);
    }

    public void chooseKatakana(View view){
        Intent intent = new Intent(this, KatakanaAlphabetActivity.class);
        startActivity(intent);
    }
    public void returnButton(View view) {
        onBackPressed();
    }
    public void showUserInfoDialog(View view) {
        // Inflate the dialog layout
        View dialogView = getLayoutInflater().inflate(R.layout.user_info_dialog, null);

        // Find the views in the dialog layout
        TextView fullNameTextView = dialogView.findViewById(R.id.tvFullName);
        TextView userNameTextView = dialogView.findViewById(R.id.tvUserName);
        TextView emailTextView = dialogView.findViewById(R.id.tvEmail);
        TextView phoneNoTextView = dialogView.findViewById(R.id.tvPhoneNo);
        TextView dateTextView = dialogView.findViewById(R.id.tvDate);
        TextView genderTextView = dialogView.findViewById(R.id.tvGender);

        // Get the user information from your model
        User user = getUserInfo();

        // Set the text of the dialog views with the user information
        fullNameTextView.setText(getString(R.string.full_name)+": "+user.getFullName());
        userNameTextView.setText(getString(R.string.user_name)+": "+user.getUserName());
        emailTextView.setText(getString(R.string.email)+": "+user.getEmail());
        phoneNoTextView.setText(getString(R.string.phone_number)+": "+user.getPhoneNo());
        dateTextView.setText(getString(R.string.dob)+": "+user.getDate());
        genderTextView.setText(getString(R.string.gender)+": "+user.getGender());

        // Create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.user_info_title));
        builder.setView(dialogView);

        // Add a button to close the dialog
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Method to get the user information from your model
    private User getUserInfo() {
        // You'll need to implement this method to get the user information from your model
        // For this example, I'll just create a new user with some sample data
        // Create a new instance of the SessionManager class
        SessionManager sessionManager = new SessionManager(HiraganaKatakanaSelectionActivity.this, SessionManager.SESSION_USERSESSION);

// Get the user details from the session
        HashMap<String, String> userDetails = sessionManager.getUsersDetailFromSession();

// Extract the data from the HashMap
        String fullName = userDetails.get(SessionManager.KEY_FULLNAME);
        String userName = userDetails.get(SessionManager.KEY_USERNAME);
        String email = userDetails.get(SessionManager.KEY_EMAIL);
        String phoneNo = userDetails.get(SessionManager.KEY_PHONENUMBER);
        String dob = userDetails.get(SessionManager.KEY_DATE);
        String gender = userDetails.get(SessionManager.KEY_GENDER);

        return new User(fullName, userName, email, phoneNo, dob, gender);
    }
}