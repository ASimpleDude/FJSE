package com.example.japanesequizappversion2.Common.LoginSignup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.japanesequizappversion2.R;

import java.util.Calendar;

public class SignUp2 extends AppCompatActivity {
    ImageView btnBack;
    Button btnNext, btnLogin;
    TextView textTitle;
    RadioGroup radioGroup;
    RadioButton selectedGender;
    DatePicker datePicker;
    String _gender, _date, _fullName, _email, _userName,_passWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up2);
        btnBack = findViewById(R.id.btn_signup2_back);
        btnNext = findViewById(R.id.btn_signup2_next);
        btnLogin = findViewById(R.id.btn_signup_login);
        radioGroup = findViewById(R.id.radio_group);
        datePicker = findViewById(R.id.date_picker);
        textTitle = findViewById(R.id.text_signup_title);

        _fullName = getIntent().getStringExtra("fullName");
        _email = getIntent().getStringExtra("email");
        _userName = getIntent().getStringExtra("userName");
        _passWord = getIntent().getStringExtra("passWord");

    }

    public void callSignup3Screen(View view) {
        if (!validateGender() | !validateDate()) {
            return;
        }
        selectedGender = findViewById(radioGroup.getCheckedRadioButtonId());

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        _gender= selectedGender.getText().toString();
        _date = day + "/" + month + "/" + year;

        Intent intent = new Intent(getApplicationContext(), SignUp3.class);

        intent.putExtra("fullName", _fullName);
        intent.putExtra("email", _email);
        intent.putExtra("userName", _userName);
        intent.putExtra("passWord", _passWord);
        intent.putExtra("gender", _gender);
        intent.putExtra("date", _date);
//
//        Pair[] pairs = new Pair[4];
//
//        pairs[0] = new Pair<View, String>(btnBack, "transition_back_arrow_btn");
//        pairs[1] = new Pair<View, String>(btnNext, "transition_login_btn");
//        pairs[2] = new Pair<View, String>(btnLogin, "transition_login_btn");
//        pairs[3] = new Pair<View, String>(textTitle, "transition_title_text");
//
//
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2.this, pairs);
        startActivity(intent);
    }

    private boolean validateGender() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateDate() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker.getYear();
        int isAgeValid = currentYear - userAge;
        if (isAgeValid < 2) {
            Toast.makeText(this, "You are too young", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

    public void returnSignup1(View view) {
        onBackPressed();
    }
}