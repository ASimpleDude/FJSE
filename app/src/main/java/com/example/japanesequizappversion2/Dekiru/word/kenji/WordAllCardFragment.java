package com.example.japanesequizappversion2.Dekiru.word.kenji;

import android.os.Bundle;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanesequizappversion2.Adapter.DekiruWordAdapter;
import com.example.japanesequizappversion2.Database.DataManager;
import com.example.japanesequizappversion2.Dekiru.LessionDekiruActivity;
import com.example.japanesequizappversion2.Model.Word;
import com.example.japanesequizappversion2.R;
import com.example.japanesequizappversion2.cardswipelayout.CardConfig;
import com.example.japanesequizappversion2.cardswipelayout.CardItemTouchHelperCallback;
import com.example.japanesequizappversion2.cardswipelayout.CardLayoutManager;
import com.example.japanesequizappversion2.cardswipelayout.MyGestureDetector;
import com.example.japanesequizappversion2.cardswipelayout.OnSwipeListener;

import java.util.ArrayList;
import java.util.Collections;

public class WordAllCardFragment extends Fragment {

    private GestureDetector mGestureDetector;
    private View.OnTouchListener mGestureListener;
    private DekiruWordAdapter mDekiruWordAdapter;
    private ArrayList<Pair<String, Word>> mWordArrayList;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kenji_all_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.rv_vocabulary);
        mWordArrayList = new ArrayList<>();
        ((WordActivity) requireActivity()).mDekiruManager.refresh();
        mWordArrayList.addAll(DataManager.lesionDekiruArrayList.get(LessionDekiruActivity.positionLesion).getNewWordDekiru());

        mDekiruWordAdapter = new DekiruWordAdapter(mWordArrayList);
        mGestureDetector = new GestureDetector(new MyGestureDetector(
                new MyGestureDetector.ISwipeGesture() {
                    @Override
                    public void onSwipeBottom() {
                        int fromPosition = mDekiruWordAdapter.getItemCount() - 1;
                        int toPosition = 0;
                        for (int i = fromPosition; i > toPosition; i--) {
                            Collections.swap(mDekiruWordAdapter.getDataSource(), i, i - 1);
                        }
                        mDekiruWordAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onSwipeTop() {
                        int fromPosition = 0;
                        int toPosition = mDekiruWordAdapter.getItemCount() - 1;
                        for (int i = fromPosition; i < toPosition; i++) {
                            Collections.swap(mDekiruWordAdapter.getDataSource(), i, i + 1);
                        }
                        mDekiruWordAdapter.notifyDataSetChanged();
                    }
                }));

        mGestureListener = (v, event) -> mGestureDetector.onTouchEvent(event);

        mRecyclerView.setAdapter(mDekiruWordAdapter);
        mRecyclerView.setOnTouchListener(mGestureListener);

        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(
                mRecyclerView.getAdapter(), mDekiruWordAdapter.getDataSource());
        ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        CardLayoutManager cardLayoutManager = new CardLayoutManager(mRecyclerView, touchHelper);
        mRecyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(mRecyclerView);
        cardCallback.setOnSwipedListener(new OnSwipeListener() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {

            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, Object o, int direction) {
                DekiruWordAdapter.WordWrapper wordWrapper = (DekiruWordAdapter.WordWrapper) o;
                switch (direction) {
                    case CardConfig.SWIPED_RIGHT:
                        ((WordActivity) requireActivity()).mDekiruManager.addWordMemorized(
                                wordWrapper.mWord);
                        break;
                    case CardConfig.SWIPED_LEFT:
                        ((WordActivity) requireActivity()).mDekiruManager.addWordNotMemorized(
                                wordWrapper.mWord);
                        break;
                }
            }

            @Override
            public void onSwipedClear() {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}