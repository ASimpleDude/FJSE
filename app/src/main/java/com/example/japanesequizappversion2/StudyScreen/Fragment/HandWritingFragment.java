package com.example.japanesequizappversion2.StudyScreen.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.japanesequizappversion2.DigitalInkRecognize.DrawView;
import com.example.japanesequizappversion2.DigitalInkRecognize.StrokeManager;
import com.example.japanesequizappversion2.R;

public class HandWritingFragment extends Fragment {
    private Button btnRecognize;
    private Button btnClear;
    private DrawView drawView;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_handwriting, container, false);

        btnRecognize = view.findViewById(R.id.btn_recognize);
        btnClear = view.findViewById(R.id.btn_clear);
        drawView = view.findViewById(R.id.draw_view);
        textView = view.findViewById(R.id.text_recognize);
//        hideTitleBar();

        StrokeManager.download();
        String textModel = getArguments().getString("textModel");
        btnRecognize.setOnClickListener(view1 -> StrokeManager.recognize(textView, textModel));

        btnClear.setOnClickListener(view12 -> {
            drawView.clear();
            StrokeManager.clear();
            textView.setText("");
        });

        return view;
    }

    private void hideTitleBar() {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        hideTitleBar();
    }

    @Override
    public void onPause() {
        super.onPause();
        hideTitleBar();
    }

    @Override
    public void onStop() {
        super.onStop();
        hideTitleBar();
    }
}