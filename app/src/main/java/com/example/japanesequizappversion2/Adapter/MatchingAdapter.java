package com.example.japanesequizappversion2.Adapter;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanesequizappversion2.R;

import java.util.ArrayList;

public class MatchingAdapter extends RecyclerView.Adapter<MatchingAdapter.ViewHolder> {
    private ArrayList<Pair<String, String>> questionMatching = new ArrayList<>();
    private ArrayList<Pair<String, String>> answerMatching = new ArrayList<>();

    private I0nClickNextQuestion onClickNextQuestion;
    private Matching validAnswer;

    public MatchingAdapter(ArrayList<Pair<String, String>> questionMatching,
                           ArrayList<Pair<String, String>> answerMatching, Matching validAnswer, I0nClickNextQuestion onClickNextQuestion) {
        super();
        this.questionMatching.addAll(questionMatching);
        this.onClickNextQuestion = onClickNextQuestion;
        this.answerMatching.addAll(answerMatching);
        this.validAnswer = validAnswer;
    }

    @NonNull
    @Override
    public MatchingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MatchingAdapter.ViewHolder(inflater.inflate(R.layout.item_question_3_matching, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MatchingAdapter.ViewHolder holder, int position) {
        holder.btn.setText(questionMatching.get(position).second);
    }

    @Override
    public int getItemCount() {
        return questionMatching.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.tvQuestionThree);
        }
    }

    public void onMove(int fromPosition, int toPosition) {

        String keyFrom = questionMatching.get(fromPosition).first;
        String keyTo = questionMatching.get(toPosition).first;
        String dataFrom = questionMatching.get(fromPosition).second;
        String dataTo = questionMatching.get(toPosition).second;
        questionMatching.set(fromPosition, new Pair<>(keyFrom, dataTo));
        questionMatching.set(toPosition, new Pair<>(keyTo, dataFrom));

        Log.d("TAG", "onMove: " + questionMatching.get(0).first + "-" + questionMatching.get(0).second);
        Log.d("TAG", "onMove: " + questionMatching.get(1).first + "-" + questionMatching.get(1).second);
        Log.d("TAG", "onMove: " + questionMatching.get(2).first + "-" + questionMatching.get(2).second);
        Log.d("TAG", "onMove: " + questionMatching.get(3).first + "-" + questionMatching.get(3).second);

        Log.d("TAG", "onMove: Answer " + answerMatching.get(0).first + "-" + answerMatching.get(0).second);
        Log.d("TAG", "onMove: Answer " + answerMatching.get(1).first + "-" + answerMatching.get(1).second);
        Log.d("TAG", "onMove: Answer " + answerMatching.get(2).first + "-" + answerMatching.get(2).second);
        Log.d("TAG", "onMove: Answer " + answerMatching.get(3).first + "-" + answerMatching.get(3).second);

        notifyItemMoved(fromPosition, toPosition);

        if (answerMatching.get(0).first.trim().equals(questionMatching.get(0).first.trim()) &&
                answerMatching.get(1).first.trim().equals(questionMatching.get(1).first.trim()) &&
                answerMatching.get(2).first.trim().equals(questionMatching.get(2).first.trim()) &&
                answerMatching.get(3).first.trim().equals(questionMatching.get(3).first.trim()) &&

                answerMatching.get(0).second.trim().equals(questionMatching.get(0).second.trim()) &&
                answerMatching.get(1).second.trim().equals(questionMatching.get(1).second.trim()) &&
                answerMatching.get(2).second.trim().equals(questionMatching.get(2).second.trim()) &&
                answerMatching.get(3).second.trim().equals(questionMatching.get(3).second.trim())
        ) {
            validAnswer.onSuccess();
            onClickNextQuestion.onClick();
        }
    }

    public interface Matching {
        public void onSuccess();
    }
}
