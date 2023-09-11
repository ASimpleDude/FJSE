package com.example.japanesequizappversion2.Common.LoginSignup;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.japanesequizappversion2.Database.CheckInternet;
import com.example.japanesequizappversion2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.List;

public class ForgetPasswordActivity extends AppCompatActivity {
    ScrollView scrollView;
    TextInputLayout email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        scrollView = findViewById(R.id.scrollview_forgetpassword);
        email = findViewById(R.id.username_forget_password);
    }

    public void verifyUserName(View view) {
        CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)) {
            showCustomDialog();
        }
        if (!validateEmail()) {
            return;
        }
        String _email = email.getEditText().getText().toString();
        FirebaseAuth.getInstance().fetchSignInMethodsForEmail(_email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                if (task.isSuccessful()) {
                    SignInMethodQueryResult result = task.getResult();
                    List<String> signInMethods = result.getSignInMethods();
                    if (signInMethods != null && signInMethods.contains(EmailAuthProvider.EMAIL_PASSWORD_SIGN_IN_METHOD)) {
                        // The email exists and can be used for password reset
                        email.setError(null);
                        email.setErrorEnabled(false);
                        Toast.makeText(ForgetPasswordActivity.this, "Get email success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgetPasswordActivity.this, ResetPasswordActivity.class);
                        intent.putExtra("email", email.getEditText().getText().toString());
                        startActivity(intent);
                    } else {
                        // The email does not exist or cannot be used for password reset
                        email.setError("Email does not exist");
                        email.requestFocus();
                    }
                } else {
                    email.setError("Email does not exist");
                    email.requestFocus();
                }
            }
        });
    }


    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ForgetPasswordActivity.this);
        builder.setMessage("Please connect to the internet to proceed further")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(), StartUpActivity.class));
                        finish();
                    }
                });
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

    public void returnStartup(View view) {
        onBackPressed();
    }
}