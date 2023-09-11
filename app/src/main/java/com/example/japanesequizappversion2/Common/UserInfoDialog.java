package com.example.japanesequizappversion2.Common;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.japanesequizappversion2.Model.User;
import com.example.japanesequizappversion2.R;

public class UserInfoDialog extends Dialog {
    private Context context;

    public UserInfoDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.japanesequizappversion2.R.layout.user_info_dialog);

        // Initialize view components here
        TextView fullNameTextView = findViewById(R.id.tvFullName);
        TextView emailTextView = findViewById(R.id.tvEmail);

        // Set user information to view components
        User user = new User(); // Replace this with your own implementation to get user information
        fullNameTextView.setText(user.getFullName());
        emailTextView.setText(user.getEmail());
    }
}
