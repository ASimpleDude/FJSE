package com.example.japanesequizappversion2.Common.LoginSignup;

import static com.example.japanesequizappversion2.Database.DataManager.lesionDekiruArrayList;
import static com.example.japanesequizappversion2.Database.DataManager.questionDetailArrayList;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.japanesequizappversion2.Database.CheckInternet;
import com.example.japanesequizappversion2.Database.SessionManager;
import com.example.japanesequizappversion2.MainScreen.MainActivity;
import com.example.japanesequizappversion2.Model.User;
import com.example.japanesequizappversion2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class LoginActivity extends AppCompatActivity {
    private TextInputLayout userName, passWord;
    private TextInputEditText etUserName, etPassWord;
    private CheckBox rememberMe;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
//    RelativeLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        userName = findViewById(R.id.username_login);
        passWord = findViewById(R.id.password_login);
        rememberMe = findViewById(R.id.remember_me);
        etUserName = findViewById(R.id.etUserName);
        etPassWord = findViewById(R.id.etPassWord);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //check if username and password is already saved in shared preferences
        SessionManager sessionManager = new SessionManager(LoginActivity.this, SessionManager.SESSION_REMEMBERME);
        if (sessionManager.checkRememberMe()) {
            HashMap<String, String> rememberMeDetals = sessionManager.getRememberMesDetailFromSession();
            etUserName.setText(rememberMeDetals.get(SessionManager.KEY_USERNAME));
            etPassWord.setText(rememberMeDetals.get(SessionManager.KEY_PASSWORD));
        } else {
            etUserName.setText("");
            etPassWord.setText("");
        }
    }

    public void letTheUserLoggedIn(View view) {
        questionDetailArrayList.clear();
        lesionDekiruArrayList.clear();
        CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)) {
            showCustomDialog();
        }
        if (!validateUsername() && !validatePassword()) {
            return;
        }
//        progressBar.setVisibility(View.VISIBLE);
        String _userName = userName.getEditText().getText().toString().trim();
        String _passWord = passWord.getEditText().getText().toString().trim();

        if (rememberMe.isChecked()) {
            SessionManager sessionManager = new SessionManager(LoginActivity.this, SessionManager.SESSION_REMEMBERME);
            sessionManager.createRememberMeSession(_userName, _passWord);
        } else {
            SessionManager sessionManager = new SessionManager(LoginActivity.this, SessionManager.SESSION_REMEMBERME);
            sessionManager.createRememberMeSession("", "");
        }
//test

        if (_userName.contains("@")) {
            mAuth.signInWithEmailAndPassword(_userName, _passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        String uid = user.getUid();
                        DatabaseReference userRef = mDatabase.child("Users").child(uid);
                        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    User user = snapshot.getValue(User.class);
                                    String fullName = user.getFullName();
                                    String userName = user.getUserName();
                                    String email = user.getEmail();
                                    String phoneNo = user.getPhoneNo();
                                    String dob = user.getDate();
                                    String gender = user.getGender();
                                    String otp = user.getOtp();
                                    String status= user.getStatus();
                                    if (otp.contains("1") || mAuth.getCurrentUser().isEmailVerified()) {
                                        if (status.equals("1")){
                                            SessionManager sessionManager = new SessionManager(LoginActivity.this, SessionManager.SESSION_USERSESSION);
                                            sessionManager.createLoginSession(fullName, userName, email, phoneNo, _passWord, dob, gender);
                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//
                                            Toast.makeText(LoginActivity.this, fullName + "\n" + phoneNo + "\n" + email + "\n" + dob + "\n", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(LoginActivity.this, "Your account is disable!", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Please verify your account!", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                        // If sign up fails, display a message to the user.
                        Toast.makeText(LoginActivity.this, "Please check your email or password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            mDatabase.child("Users").orderByChild("userName").equalTo(_userName).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            User user = childSnapshot.getValue(User.class);
                            mAuth.signInWithEmailAndPassword(user.getEmail(), _passWord)
                                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                FirebaseUser user = mAuth.getCurrentUser();
                                                String uid = user.getUid();
                                                DatabaseReference userRef = mDatabase.child("Users").child(uid);
                                                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        if (snapshot.exists()) {
                                                            User user = snapshot.getValue(User.class);
                                                            String fullName = user.getFullName();
                                                            String userName = user.getUserName();
                                                            String email = user.getEmail();
                                                            String phoneNo = user.getPhoneNo();
                                                            String dob = user.getDate();
                                                            String gender = user.getGender();
                                                            String otp = user.getOtp();
                                                            String status = user.getStatus();
                                                            if (otp.contains("1") || mAuth.getCurrentUser().isEmailVerified()) {
                                                                if (status.equals("1")){
                                                                    SessionManager sessionManager = new SessionManager(LoginActivity.this, SessionManager.SESSION_USERSESSION);
                                                                    sessionManager.createLoginSession(fullName, userName, email, phoneNo, _passWord, dob, gender);
                                                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//
                                                                    Toast.makeText(LoginActivity.this, fullName + "\n" + phoneNo + "\n" + email + "\n" + dob + "\n", Toast.LENGTH_SHORT).show();
                                                                }else{
                                                                    Toast.makeText(LoginActivity.this, "Your account is disable!", Toast.LENGTH_SHORT).show();
                                                                }
                                                            } else {
                                                                Toast.makeText(LoginActivity.this, "Please verify your email!", Toast.LENGTH_SHORT).show();
                                                            }
                                                        } else {
                                                            Toast.makeText(LoginActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            } else {
                                                // If sign in fails, display a message to the user.
                                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Wrong email or password",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void callSignUpFromLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }

    private boolean validateUsername() {
//        String val = userName.getEditText().getText().toString().trim();
//        String checkSpaces = "\\A\\w{1,20}\\z";
//        if (val.isEmpty()) {
//            userName.setError("Username can not be empty");
//            return false;
//        } else if (val.length() > 20) {
//            userName.setError("Username cannot longer than 20 characters");
//            return false;
//        } else if (!val.matches(checkSpaces)) {
//            userName.setError("You cannot use white spaces");
//            return false;
//        } else {
//            userName.setError(null);
//            userName.setErrorEnabled(false);
//            return true;
//        }
        return true;
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

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
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

    public void callForgetPassword(View view) {
        startActivity(new Intent(getApplicationContext(), ForgetPasswordActivity.class));
    }

    public void returnStartup(View view) {
        onBackPressed();
    }


}