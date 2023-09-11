package com.example.japanesequizappversion2.Common.LoginSignup;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.japanesequizappversion2.Model.User;
import com.example.japanesequizappversion2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChooseVerifyMethodActivity extends AppCompatActivity {
    ScrollView scrollView;
    private String fullName, email, userName, passWord, date, gender, phoneNo, whatToDo;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private TextView mobileNumber, emailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_verify_method);
        scrollView = findViewById(R.id.scrollview_choosemethod);
        mobileNumber = findViewById(R.id.mobile_number);
        emailAddress = findViewById(R.id.mail_address);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        fullName = getIntent().getStringExtra("fullName");
        email = getIntent().getStringExtra("email");
        userName = getIntent().getStringExtra("userName");
        passWord = getIntent().getStringExtra("passWord");
        date = getIntent().getStringExtra("date");
        gender = getIntent().getStringExtra("gender");
        phoneNo = getIntent().getStringExtra("phoneNo");
        whatToDo = getIntent().getStringExtra("whatToDo");

        mobileNumber.setText(phoneNo);
        emailAddress.setText(email);
    }

    public void chooseOtpMethod(View view) {
        Intent intent = new Intent(getApplicationContext(), VerifyOTP.class);
        intent.putExtra("fullName", fullName);
        intent.putExtra("email", email);
        intent.putExtra("userName", userName);
        intent.putExtra("passWord", passWord);
        intent.putExtra("date", date);
        intent.putExtra("gender", gender);
        intent.putExtra("phoneNo", phoneNo);
        if (userName.isEmpty()) {
            intent.putExtra("whatToDo", "updatePassword");
        } else {
            intent.putExtra("whatToDo", "newUser");
        }


        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(scrollView, "transition_OTP_screen");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ChooseVerifyMethodActivity.this, pairs);
        startActivity(intent, options.toBundle());
    }

    public void chooseEmailMethod(View view) {
        Intent intent = new Intent(getApplicationContext(), ResetPasswordSuccessfully.class);
        mAuth.createUserWithEmailAndPassword(email, passWord).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();

                user.sendEmailVerification().addOnCompleteListener(emailTask -> {
                    if (emailTask.isSuccessful()) {
                        String uid = user.getUid();
                        User newUser = new User(fullName,userName, email, phoneNo, passWord, date, gender, "0", "user", "1");
                        mDatabase.child("Users").child(uid).setValue(newUser);
                        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        Toast.makeText(this, "Verification email sent", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Failed to send verification email", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "Sign up failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(scrollView, "transition_OTP_screen");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ChooseVerifyMethodActivity.this, pairs);
        startActivity(intent, options.toBundle());
    }

    public void returnSignup3(View view) {
        onBackPressed();
    }
}