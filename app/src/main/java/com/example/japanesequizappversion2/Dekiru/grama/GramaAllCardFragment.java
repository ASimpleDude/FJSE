package com.example.japanesequizappversion2.Dekiru.grama;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanesequizappversion2.Adapter.DekiruGramaAdapter;
import com.example.japanesequizappversion2.Database.DataManager;
import com.example.japanesequizappversion2.Dekiru.LessionDekiruActivity;
import com.example.japanesequizappversion2.Model.Grama;
import com.example.japanesequizappversion2.R;
import com.example.japanesequizappversion2.cardswipelayout.CardConfig;
import com.example.japanesequizappversion2.cardswipelayout.CardItemTouchHelperCallback;
import com.example.japanesequizappversion2.cardswipelayout.CardLayoutManager;
import com.example.japanesequizappversion2.cardswipelayout.MyGestureDetector;
import com.example.japanesequizappversion2.cardswipelayout.OnSwipeListener;

import java.util.ArrayList;
import java.util.Collections;

public class GramaAllCardFragment extends Fragment {

    private GestureDetector mGestureDetector;
    private View.OnTouchListener mGestureListener;
    private DekiruGramaAdapter mDekiruGramaAdapter;
    private ArrayList<Grama> mGramaArrayList;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grama_all_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.rv_grama);
        mGramaArrayList = DataManager.lesionDekiruArrayList.get(LessionDekiruActivity.positionLesion).getGrama();
        mDekiruGramaAdapter = new DekiruGramaAdapter(mGramaArrayList);

        mGestureDetector = new GestureDetector(new MyGestureDetector(
                new MyGestureDetector.ISwipeGesture() {
                    @Override
                    public void onSwipeBottom() {
                        int fromPosition = mDekiruGramaAdapter.getItemCount() - 1;
                        int toPosition = 0;
                        for (int i = fromPosition; i > toPosition; i--) {
                            Collections.swap(mDekiruGramaAdapter.getDataSource(), i, i - 1);
                        }
                        mDekiruGramaAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onSwipeTop() {
                        int fromPosition = 0;
                        int toPosition = mDekiruGramaAdapter.getItemCount() - 1;
                        for (int i = fromPosition; i < toPosition; i++) {
                            Collections.swap(mDekiruGramaAdapter.getDataSource(), i, i + 1);
                        }
                        mDekiruGramaAdapter.notifyDataSetChanged();
                    }
                }));

        mGestureListener = (v, event) -> mGestureDetector.onTouchEvent(event);

        mRecyclerView.setAdapter(mDekiruGramaAdapter);
        mRecyclerView.setOnTouchListener(mGestureListener);

        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(
                mDekiruGramaAdapter ,mDekiruGramaAdapter.getDataSource());
        cardCallback.setOnTouchListener(mGestureListener);

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
                DekiruGramaAdapter.GramaWrapper gramaWrapper = (DekiruGramaAdapter.GramaWrapper) o;
                switch (direction) {
                    case CardConfig.SWIPED_RIGHT:
                        ((GramaActivity) requireActivity()).mDekiruManager.addGramaMemorized(
                                gramaWrapper.grama);
                        break;
                    case CardConfig.SWIPED_LEFT:
                        ((GramaActivity) requireActivity()).mDekiruManager.addGramaNotMemorized(
                                gramaWrapper.grama);
                        break;
                }
            }

            @Override
            public void onSwipedClear() {

            }
        });
    }
}