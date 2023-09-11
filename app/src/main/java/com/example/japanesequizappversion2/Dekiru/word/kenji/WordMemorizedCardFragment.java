package com.example.japanesequizappversion2.Dekiru.word.kenji;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.japanesequizappversion2.Database.dekiru.DekiruManager;
import com.example.japanesequizappversion2.Model.Word;
import com.example.japanesequizappversion2.R;

import java.util.ArrayList;

public class WordMemorizedCardFragment extends Fragment {

    private ArrayList<String> mListName = new ArrayList<>();
    private DekiruManager mDekiruManager;
    private ListView mListView;
    private ArrayAdapter mArrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kenji_memorized_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDekiruManager = ((WordActivity) requireActivity()).mDekiruManager;
        mListView = view.findViewById(R.id.list);

        if (DekiruManager.mListWordMemorized != null && DekiruManager.mListWordMemorized.size() != 0) {
            for (Pair<String, Word> word : DekiruManager.mListWordMemorized) {
                mListName.add("Từ mới: " + word.first + " - " + " Nghĩa: " + word.second.getMean());
            }
        }
        mArrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, mListName);
        mListView.setAdapter(mArrayAdapter);

        view.findViewById(R.id.btnClear).setOnClickListener(v -> {
            mDekiruManager.clearWordMemorized();
            mListName.clear();
            mArrayAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mListName.clear();
        for (Pair<String, Word> word : DekiruManager.mListWordMemorized) {
            mListName.add("Từ mới: " + word.first + " - " + " Nghĩa: " + word.second.getMean());
        }
        mArrayAdapter.notifyDataSetChanged();
    }
}