package com.example.japanesequizappversion2.Adapter;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanesequizappversion2.Database.dekiru.DekiruManager;
import com.example.japanesequizappversion2.Model.Kanji;
import com.example.japanesequizappversion2.R;

import java.util.ArrayList;

public class DekiruKenjiAdapter extends RecyclerView.Adapter<DekiruKenjiAdapter.ViewHolder> {

    private ArrayList<KanjiWrapper> mDataSource;

    public DekiruKenjiAdapter(ArrayList<Pair<String, Kanji>> dataSource) {
        mDataSource = new ArrayList<>();
        for (int i = 0; i < dataSource.size(); i++) {
            mDataSource.add(new KanjiWrapper(i + 1, dataSource.get(i)));
        }
    }

    public ArrayList<KanjiWrapper> getDataSource() {
        return mDataSource;
    }

    public void updateNo() {
        for (int i = 0; i < mDataSource.size(); i++) {
            mDataSource.get(i).no = i + 1;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new DekiruKenjiAdapter.ViewHolder(inflater.inflate(R.layout.item_card_dekiru_kanji, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextView4.setVisibility(View.VISIBLE);
        holder.mTextView1.setText(mDataSource.get(position).mKanji.first);
        holder.mTextView2.setText(mDataSource.get(position).mKanji.second.getRead());
        holder.mTextView4.setText(mDataSource.get(position).mKanji.second.getMean());

        holder.mImageTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isDisplayTutorial = DekiruManager.getInstance(v.getContext()).isDisplayTutorialKenji();
                if (!isDisplayTutorial)
                    return;

                holder.mTutorialArea.setVisibility(View.VISIBLE);
            }
        });

        holder.mCheckBoxNotRedisplay.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        DekiruManager.getInstance(buttonView.getContext()).saveNotDisplayTutorialKenji();
                        holder.mTutorialArea.setVisibility(View.GONE);
                    }
                });

        holder.mRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mTutorialArea.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView1;
        private TextView mTextView2;
        private TextView mTextView4;

        private ImageView mImageTutorial;
        private LinearLayout mTutorialArea;
        private CheckBox mCheckBoxNotRedisplay;
        private ConstraintLayout mRoot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.tvTitle);
            mTextView2 = itemView.findViewById(R.id.tvContent);
            mTextView4 = itemView.findViewById(R.id.tvContent2);

            mRoot = itemView.findViewById(R.id.root);
            mImageTutorial = itemView.findViewById(R.id.tutorial);
            mTutorialArea = itemView.findViewById(R.id.tutorial_area);
            mCheckBoxNotRedisplay = itemView.findViewById(R.id.chk_not_redisplay);
        }
    }

    public class KanjiWrapper {
        int no;
        public Pair<String, Kanji> mKanji;

        public KanjiWrapper(int no, Pair<String, Kanji> kanji) {
            this.no = no;
            mKanji = kanji;
        }
    }
}
