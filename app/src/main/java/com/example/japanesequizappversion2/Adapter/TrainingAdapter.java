package com.example.japanesequizappversion2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanesequizappversion2.Database.quizz.Lesson;
import com.example.japanesequizappversion2.R;

import java.util.ArrayList;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder> {

    private ArrayList<Lesson> lessonArrayList = new ArrayList<>();
    private Context mContext;

    public ArrayList<Integer> numberLesson = new ArrayList<>();

    public TrainingAdapter(Context mContext, ArrayList<Lesson> lessonArrayList) {
        this.mContext = mContext;
        this.lessonArrayList.addAll(lessonArrayList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public CheckBox cb;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            cb = itemView.findViewById(R.id.checkbox);
        }
    }

    @NonNull
    @Override
    public TrainingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_lesson, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText("Bài " + (position + 1));
        holder.cb.setOnClickListener(v -> {
            if (!numberLesson.contains(position)) {
                numberLesson.add(position);
            } else {
                Integer i = numberLesson.indexOf(position);
                numberLesson.remove(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lessonArrayList.size();
    }

    public ArrayList<Integer> getNumberLesson() {
        return numberLesson;
    }
}