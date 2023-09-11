package com.example.japanesequizappversion2.Common.LoginSignup;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.japanesequizappversion2.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {
    ImageView btnBack;
    Button btnNext, btnLogin;
    TextView textTitle;
    TextInputLayout fullName, userName, email, passWord;
    String _fullName, _email, _userName, _passWord;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();
        btnBack = findViewById(R.id.btn_signup1_back);
        btnNext = findViewById(R.id.btn_signup1_next);
        btnLogin = findViewById(R.id.btn_signup1_login);
        textTitle = findViewById(R.id.text_signup_title);

        fullName = findViewById(R.id.signup1_fullname);
        email = findViewById(R.id.signup1_email);
        userName = findViewById(R.id.signup1_username);
        passWord = findViewById(R.id.signup1_password);
    }

    public void callNextSignupScreen(View view) {
        _userName = userName.getEditText().getText().toString().trim();
        _email = email.getEditText().getText().toString().trim();
        checkIfUserExists(_userName, _email);
    }

    private boolean validateFullname() {
        String val = fullName.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            fullName.setError("Name can not be empty");
            return false;
        } else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {
        String val = userName.getEditText().getText().toString().trim();
        String checkSpaces = "\\A\\w{1,20}\\z";
        if (val.isEmpty()) {
            userName.setError("Username can not be empty");
            return false;
        } else if (val.length() > 20) {
            userName.setError("Username cannot longer than 20 characters");
            return false;
        } else if (!val.matches(checkSpaces)) {
            userName.setError("You cannot use white spaces");
            return false;
        } else {
            userName.setError(null);
            userName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "^[A-Za-z0-9]+@[A-Za-z]+(\\.[0-9A-Za-z]{2,4})+$";
        if (val.isEmpty()) {
            email.setError("Email can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            email.setError("Invalid email!");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = passWord.getEditText().getText().toString().trim();
        String checkPassword = "^" +
//                //"(?=.*[0-9])" +         //at least 1 digit
//                //"(?=.*[a-z])" +         //at least 1 lower case letter
//                //"(?=.*[A-Z])" +         //at least 1 upper case letter
//                "(?=.*[a-zA-Z])" +//any letter
//                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            passWord.setError("Password can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            passWord.setError("Password must have more than 4 characters!");
            return false;
        } else {
            passWord.setError(null);
            passWord.setErrorEnabled(false);
            return true;
        }
    }

    public void callLoginFromSignUp1(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.btn_signup1_login), "transition_login");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUpActivity.this, pairs);
        startActivity(intent, options.toBundle());
    }

    public void returnStartup(View view) {
        onBackPressed();
        finish();
    }

    private void  checkIfUserExists(final String username, final String email2) {
        mDatabase.child("Users").orderByChild("userName").equalTo(username)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            userName.setError("Username is not available");
                            userName.setErrorEnabled(true);
                            userName.requestFocus();
                            Toast.makeText(SignUpActivity.this, "This username is already taken", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (!validateEmail() | !validatePassword() | !validateUsername() | !validateFullname()) {
                            return;
                        } else {
                            // The username is available, now check if the email is already taken
                            mDatabase.child("Users").orderByChild("email").equalTo(email2)
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.exists()) {
                                                // The email is already taken
                                                email.setError("This email is already taken");
                                                email.setErrorEnabled(true);
                                                email.requestFocus();
                                                Toast.makeText(SignUpActivity.this, "This email is already taken", Toast.LENGTH_SHORT).show();
                                            } else {
                                                userName.setError(null);
                                                userName.setErrorEnabled(false);
                                                _fullName = fullName.getEditText().getText().toString();
                                                _email = email2;
                                                _passWord = passWord.getEditText().getText().toString();
                                                callSignup2();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            Toast.makeText(SignUpActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(SignUpActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void callSignup2() {
        Intent intent = new Intent(SignUpActivity.this, SignUp2.class);
        intent.putExtra("fullName", _fullName);
        intent.putExtra("email", _email);
        intent.putExtra("userName", _userName);
        intent.putExtra("passWord", _passWord);
//
//                                                Pair[] pairs = new Pair[4];
//
//                                                pairs[0] = new Pair<View, String>(btnBack, "transition_back_arrow_btn");
//                                                pairs[1] = new Pair<View, String>(btnNext, "transition_login_btn");
//                                                pairs[2] = new Pair<View, String>(btnLogin, "transition_login_btn");
//                                                pairs[3] = new Pair<View, String>(textTitle, "transition_title_text");

//                                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUpActivity.this, pairs);
        startActivity(intent);
    }
}