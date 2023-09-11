package com.example.japanesequizappversion2.Dekiru.grama;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.japanesequizappversion2.Database.dekiru.DekiruManager;
import com.example.japanesequizappversion2.Model.Grama;
import com.example.japanesequizappversion2.R;

import java.util.ArrayList;

public class GramaNotMemorizedFragment extends Fragment {


    private ArrayList<String> mListName = new ArrayList<>();
    private DekiruManager mDekiruManager;
    private ListView mListView;
    private ArrayAdapter mArrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grama_not_memorized, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDekiruManager = ((GramaActivity) requireActivity()).mDekiruManager;
        mListView = view.findViewById(R.id.list);

        for (Grama grama : DekiruManager.mListGramaNotMemorized) {
            mListName.add("Từ mới: " + grama.getContent().getFirst() + " Nghĩa:" + grama.getMean());
        }

        mArrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, mListName);
        mListView.setAdapter(mArrayAdapter);

        view.findViewById(R.id.btnClear).setOnClickListener(v -> {
            mDekiruManager.clearGramaNotMemorized();
            mListName.clear();
            mArrayAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mListName.clear();

        for (Grama grama : DekiruManager.mListGramaNotMemorized) {
            mListName.add("Từ mới: " + grama.getContent().getFirst() + " Nghĩa:" + grama.getMean());
        }
        mArrayAdapter.notifyDataSetChanged();
    }
}