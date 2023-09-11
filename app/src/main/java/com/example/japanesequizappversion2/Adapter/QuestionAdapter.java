package com.example.japanesequizappversion2.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanesequizappversion2.Database.DataManager;
import com.example.japanesequizappversion2.Database.quizz.QuestionDetail;
import com.example.japanesequizappversion2.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<QuestionDetail> questionDetailArrayList = new ArrayList<>();
    public ArrayList<QuestionDetail> validAnswer = new ArrayList<>();
    private Context mContext;
    private I0nClickNextQuestion onClickNextQuestion;

    public QuestionAdapter(Context mContext, ArrayList<QuestionDetail> questionDetailArrayList, I0nClickNextQuestion onNext) {
        this.mContext = mContext;
        this.questionDetailArrayList.addAll(questionDetailArrayList);
        this.onClickNextQuestion = onNext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        switch (viewType) {
            case 2:
                return new ViewHolder2(inflater.inflate(R.layout.item_question_2, parent, false));
            case 3:
                return new ViewHolder3(inflater.inflate(R.layout.item_question_3, parent, false));
            default:
                return new ViewHolder1(inflater.inflate(R.layout.item_question_1, parent, false));
        }
    }

    Integer number = 0;

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        QuestionDetail questionDetail = questionDetailArrayList.get(position);
        switch (holder.getItemViewType()) {
            case 2:
                number = 0;
                ViewHolder2 viewHolder2 = (ViewHolder2) holder;
                viewHolder2.text1.setText("");
//                viewHolder2.text2.setText("");
//                viewHolder2.text3.setText("");
//                viewHolder2.text4.setText("");
                viewHolder2.question.setText(questionDetail.getQuestion());
                viewHolder2.btn1.setText(questionDetail.getAnswerChose().get(0));
                viewHolder2.btn2.setText(questionDetail.getAnswerChose().get(1));
                viewHolder2.btn3.setText(questionDetail.getAnswerChose().get(2));
                viewHolder2.btn4.setText(questionDetail.getAnswerChose().get(3));

                viewHolder2.btn1.setOnClickListener(v -> {
                    if (number >= 4) return;
                    number = number + 1;
                    viewHolder2.text1.setText(viewHolder2.text1.getText() + "" + viewHolder2.btn1.getText());
                    Boolean isValid = questionDetail.getAnswer().equals(viewHolder2.text1.getText());
                    handleValid(isValid, questionDetail);
                });

                viewHolder2.btn2.setOnClickListener(v -> {
                    if (number >= 4) return;
                    number = number + 1;
                    viewHolder2.text1.setText(viewHolder2.text1.getText() + "" + viewHolder2.btn2.getText());
                    Boolean isValid = questionDetail.getAnswer().equals(viewHolder2.text1.getText());
                    handleValid(isValid, questionDetail);
                });

                viewHolder2.btn3.setOnClickListener(v -> {
                    if (number >= 4) return;
                    number = number + 1;
                    viewHolder2.text1.setText(viewHolder2.text1.getText() + "" + viewHolder2.btn3.getText());
                    Boolean isValid = questionDetail.getAnswer().equals(viewHolder2.text1.getText());
                    handleValid(isValid, questionDetail);
                });

                viewHolder2.btn4.setOnClickListener(v -> {
                    if (number >= 4) return;
                    number = number + 1;
                    viewHolder2.text1.setText(viewHolder2.text1.getText() + "" + viewHolder2.btn4.getText());
                    Boolean isValid = questionDetail.getAnswer().equals(viewHolder2.text1.getText());
                    handleValid(isValid, questionDetail);
                });
                break;
            case 3:
                ViewHolder3 viewHolder3 = (ViewHolder3) holder;

                viewHolder3.btn1.setText(questionDetail.getQuestionMatching().get(0).first);
                viewHolder3.btn2.setText(questionDetail.getQuestionMatching().get(1).first);
                viewHolder3.btn3.setText(questionDetail.getQuestionMatching().get(2).first);
                viewHolder3.btn4.setText(questionDetail.getQuestionMatching().get(3).first);

                MatchingAdapter matchingAdapter = new MatchingAdapter(
                        questionDetail.getQuestionMatching(),
                        questionDetail.getAnswerMatching(),
                        () -> validAnswer.add(questionDetail),
                        onClickNextQuestion);

                ItemTouchHelper.Callback callback = new MatchingItemTouchHelperCallback(matchingAdapter::onMove);
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
                itemTouchHelper.attachToRecyclerView(viewHolder3.rc);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);

                viewHolder3.rc.setLayoutManager(linearLayoutManager);
                viewHolder3.rc.setAdapter(matchingAdapter);

                break;
            default:
                ViewHolder1 viewHolder0 = (ViewHolder1) holder;

                viewHolder0.question.setText(questionDetail.getQuestion());

                viewHolder0.btn1.setText(questionDetail.getAnswerChose().get(0));
                viewHolder0.btn2.setText(questionDetail.getAnswerChose().get(1));
                viewHolder0.btn3.setText(questionDetail.getAnswerChose().get(2));
                viewHolder0.btn4.setText(questionDetail.getAnswerChose().get(3));
                setBackgroundUnChoose(viewHolder0.btn1, "#fe7c73");
                setBackgroundUnChoose(viewHolder0.btn2, "#fe7c73");
                setBackgroundUnChoose(viewHolder0.btn3, "#fe7c73");
                setBackgroundUnChoose(viewHolder0.btn4, "#fe7c73");

                viewHolder0.btn1.setOnClickListener(v -> {
                    Boolean isValid = viewHolder0.btn1.getText().equals(questionDetail.getAnswer());
                    handleValid(isValid, questionDetail);
                    setBackgroundChoose(viewHolder0.btn1, R.drawable.border_question_1);
                    setBackgroundUnChoose(viewHolder0.btn2, "#fe7c73");
                    setBackgroundUnChoose(viewHolder0.btn3, "#fe7c73");
                    setBackgroundUnChoose(viewHolder0.btn4, "#fe7c73");
                });

                viewHolder0.btn2.setOnClickListener(v -> {
                    Boolean isValid = viewHolder0.btn2.getText().equals(questionDetail.getAnswer());
                    handleValid(isValid, questionDetail);
                    setBackgroundUnChoose(viewHolder0.btn1, "#fe7c73");
                    setBackgroundChoose(viewHolder0.btn2, R.drawable.border_question_2);
                    setBackgroundUnChoose(viewHolder0.btn3, "#fe7c73");
                    setBackgroundUnChoose(viewHolder0.btn4, "#fe7c73");
                });

                viewHolder0.btn3.setOnClickListener(v -> {
                    Boolean isValid = viewHolder0.btn3.getText().equals(questionDetail.getAnswer());
                    handleValid(isValid, questionDetail);
                    setBackgroundUnChoose(viewHolder0.btn1, "#fe7c73");
                    setBackgroundUnChoose(viewHolder0.btn2, "#fe7c73");
                    setBackgroundChoose(viewHolder0.btn3, R.drawable.border_question_3);
                    setBackgroundUnChoose(viewHolder0.btn4, "#fe7c73");
                });

                viewHolder0.btn4.setOnClickListener(v -> {
                    Boolean isValid = viewHolder0.btn4.getText().equals(questionDetail.getAnswer());
                    handleValid(isValid, questionDetail);
                    setBackgroundUnChoose(viewHolder0.btn1, "#fe7c73");
                    setBackgroundUnChoose(viewHolder0.btn2, "#fe7c73");
                    setBackgroundUnChoose(viewHolder0.btn3, "#fe7c73");
                    setBackgroundChoose(viewHolder0.btn4, R.drawable.border_question_4);
                });
        }
    }

    public void setBackgroundChoose(AppCompatButton view, int id) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(ContextCompat.getDrawable(mContext, id));
        } else {
            view.setBackground(ContextCompat.getDrawable(mContext, id));
        }
    }

    public void setBackgroundUnChoose(AppCompatButton view, String color) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundColor(Color.parseColor(color));
        } else {
            view.setBackgroundColor(Color.parseColor(color));
        }
    }


    public void handleValid(Boolean isValid, QuestionDetail questionDetail) {
        if (isValid) {
            boolean isExit = false;
            for (QuestionDetail data : validAnswer) {
                if (data.equals(questionDetail)) {
                    isExit = true;
                }
            }
            if (!isExit) {
                validAnswer.add(questionDetail);
            }
            onClickNextQuestion.onClick();
        } else {
            validAnswer.remove(questionDetail);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return questionDetailArrayList.get(position).getTypeQuestion();
    }

    @Override
    public int getItemCount() {
        return questionDetailArrayList.size();
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        public TextView question;
        public AppCompatButton btn1;
        public AppCompatButton btn2;
        public AppCompatButton btn3;
        public AppCompatButton btn4;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.questionOne);
            btn1 = itemView.findViewById(R.id.buttonQuestion1);
            btn2 = itemView.findViewById(R.id.buttonQuestion2);
            btn3 = itemView.findViewById(R.id.buttonQuestion3);
            btn4 = itemView.findViewById(R.id.buttonQuestion4);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        public TextView question;
        public TextView text1;
        public TextView text2;
        public TextView text3;
        public TextView text4;
        public MaterialButton btn1;
        public MaterialButton btn2;
        public MaterialButton btn3;
        public MaterialButton btn4;

        public ViewHolder2(View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.questionTwo);
            text1 = itemView.findViewById(R.id.text1);
//            text2 = itemView.findViewById(R.id.text2);
//            text3 = itemView.findViewById(R.id.text3);
//            text4 = itemView.findViewById(R.id.text4);
            btn1 = itemView.findViewById(R.id.buttonQuestion2_1);
            btn2 = itemView.findViewById(R.id.buttonQuestion2_2);
            btn3 = itemView.findViewById(R.id.buttonQuestion2_3);
            btn4 = itemView.findViewById(R.id.buttonQuestion2_4);
        }
    }

    class ViewHolder3 extends RecyclerView.ViewHolder {

        public TextView question;
        public MaterialButton btn1;
        public MaterialButton btn2;
        public MaterialButton btn3;
        public MaterialButton btn4;
        public RecyclerView rc;

        public ViewHolder3(View itemView) {
            super(itemView);
            btn1 = itemView.findViewById(R.id.buttonQuestion3_1);
            btn2 = itemView.findViewById(R.id.buttonQuestion3_2);
            btn3 = itemView.findViewById(R.id.buttonQuestion3_3);
            btn4 = itemView.findViewById(R.id.buttonQuestion3_4);
            rc = itemView.findViewById(R.id.rcQuestionThree);
        }
    }

    public void updateData(ArrayList<QuestionDetail> questionDetails) {
        questionDetailArrayList.clear();
        questionDetailArrayList.addAll(questionDetails);
        notifyDataSetChanged();
    }

    public String getValidAnswer() {
        return "Số đáp án đúng là :" + validAnswer.size() + "/" + DataManager.questionDetailArrayList.size() +
                "\nSố đáp án sai là: " + (DataManager.questionDetailArrayList.size() - validAnswer.size()) + "/" + DataManager.questionDetailArrayList.size();
    }
}

