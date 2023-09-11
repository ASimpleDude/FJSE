package com.example.japanesequizappversion2.Common.LoginSignup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.japanesequizappversion2.R;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class SignUp3 extends AppCompatActivity {
    ScrollView scrollView;
    TextInputLayout phoneNumber;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up3);

        scrollView = findViewById(R.id.scrollview_signup3);
        countryCodePicker = findViewById(R.id.country_code_picker_signup);
        phoneNumber = findViewById(R.id.signup_phone_number);
    }

    public void callChooseVerifyScreen(View view) {
        if (!validatePhoneNumber()) {
            return;
        }
        String _fullName = getIntent().getStringExtra("fullName");
        String _email = getIntent().getStringExtra("email");
        String _userName = getIntent().getStringExtra("userName");
        String _passWord = getIntent().getStringExtra("passWord");
        String _date = getIntent().getStringExtra("date");
        String _gender = getIntent().getStringExtra("gender");

        String _getPhoneNumber = phoneNumber.getEditText().getText().toString().trim();
        if (_getPhoneNumber.charAt(0) == '0') {
            _getPhoneNumber = _getPhoneNumber.substring(1);
        }
        String carrierNumber = countryCodePicker.getSelectedCountryCodeWithPlus();
        final String _phoneNo = carrierNumber + _getPhoneNumber;


        Intent intent = new Intent(getApplicationContext(), ChooseVerifyMethodActivity.class);
        intent.putExtra("fullName", _fullName);
        intent.putExtra("email", _email);
        intent.putExtra("userName", _userName);
        intent.putExtra("passWord", _passWord);
        intent.putExtra("date", _date);
        intent.putExtra("gender", _gender);
        intent.putExtra("phoneNo", _phoneNo);
//
//        Pair[] pairs = new Pair[1];
//        pairs[0] = new Pair<View, String>(scrollView, "transition_VerifyMethod_screen");
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp3.this, pairs);
        startActivity(intent);
    }

    private boolean validatePhoneNumber() {
        String val = phoneNumber.getEditText().getText().toString().trim();
        String checkspaces = "^[0-9]{9,11}$";
        if (val.isEmpty()) {
            phoneNumber.setError("Enter valid phone number");
            return false;
        } else if (!val.matches(checkspaces)) {
            phoneNumber.setError("Please input valid phone number");
            return false;
        } else {
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;
        }
    }

    public void returnSignup2(View view) {
        onBackPressed();
    }
}