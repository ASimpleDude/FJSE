package com.example.japanesequizappversion2.Adapter;

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
import com.example.japanesequizappversion2.Model.Grama;
import com.example.japanesequizappversion2.Model.Kanji;
import com.example.japanesequizappversion2.R;

import java.util.ArrayList;

public class DekiruGramaAdapter extends RecyclerView.Adapter<DekiruGramaAdapter.ViewHolder> {

    private ArrayList<GramaWrapper> mDataSource;

    public DekiruGramaAdapter(ArrayList<Grama> dataSource) {
        mDataSource = new ArrayList<>();
        for (int i = 0; i < dataSource.size(); i++) {
            mDataSource.add(new GramaWrapper(i + 1, dataSource.get(i)));
        }
    }

    public void updateNo() {
        for (int i = 0; i < mDataSource.size(); i++) {
            mDataSource.get(i).no = i + 1;
        }
    }

    public ArrayList<GramaWrapper> getDataSource() {
        return mDataSource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new DekiruGramaAdapter.ViewHolder(inflater.inflate(R.layout.item_card_dekiru_grama, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GramaWrapper gramaWrapper = mDataSource.get(position);
        holder.mContentF.setText(gramaWrapper.grama.getContent().getFirst());
        holder.mContentS.setText(gramaWrapper.grama.getContent().getSecond());
        holder.mMean.setText(gramaWrapper.grama.getMean());
        holder.mNote.setText(gramaWrapper.grama.getNote());
        holder.mSentenceJP.setText(gramaWrapper.grama.getSentence().getJp());
        holder.mSentenceVI.setText(gramaWrapper.grama.getSentence().getVi());

        holder.mImageTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isDisplayTutorial = DekiruManager.getInstance(v.getContext()).isDisplayTutorialGrama();
                if (!isDisplayTutorial)
                    return;

                holder.mTutorialArea.setVisibility(View.VISIBLE);
            }
        });

        holder.mCheckBoxNotRedisplay.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        DekiruManager.getInstance(buttonView.getContext()).saveNotDisplayTutorialGrama();
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

        private TextView mContentF;
        private TextView mContentS;
        private TextView mMean;
        private TextView mNote;
        private TextView mSentenceVI;
        private TextView mSentenceJP;

        private ImageView mImageTutorial;
        private LinearLayout mTutorialArea;
        private CheckBox mCheckBoxNotRedisplay;
        private ConstraintLayout mRoot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mContentF = itemView.findViewById(R.id.tvContentF);
            mContentS = itemView.findViewById(R.id.tvContentS);
            mMean = itemView.findViewById(R.id.tvMean);
            mNote = itemView.findViewById(R.id.tvNote);
            mSentenceVI = itemView.findViewById(R.id.tvSentenceVI);
            mSentenceJP = itemView.findViewById(R.id.tvSentenceJP);

            mRoot = itemView.findViewById(R.id.root);
            mImageTutorial = itemView.findViewById(R.id.tutorial);
            mTutorialArea = itemView.findViewById(R.id.tutorial_area);
            mCheckBoxNotRedisplay = itemView.findViewById(R.id.chk_not_redisplay);
        }
    }

    public class GramaWrapper {
        public int no;
        public Grama grama;

        public GramaWrapper(int no, Grama grama) {
            this.no = no;
            this.grama = grama;
        }
    }
}
