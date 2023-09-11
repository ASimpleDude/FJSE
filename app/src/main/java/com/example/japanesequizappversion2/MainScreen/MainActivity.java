package com.example.japanesequizappversion2.MainScreen;

import static android.util.Log.d;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.japanesequizappversion2.Database.DataManager;
import com.example.japanesequizappversion2.Database.SessionManager;
import com.example.japanesequizappversion2.Database.quizz.Lesson;
import com.example.japanesequizappversion2.Database.quizz.QuestionDetail;
import com.example.japanesequizappversion2.Dekiru.LessionDekiruActivity;
import com.example.japanesequizappversion2.Model.Content;
import com.example.japanesequizappversion2.Model.Grama;
import com.example.japanesequizappversion2.Model.Kanji;
import com.example.japanesequizappversion2.Model.LessionDekiru;
import com.example.japanesequizappversion2.Model.Sentence;
import com.example.japanesequizappversion2.Model.User;
import com.example.japanesequizappversion2.Model.Word;
import com.example.japanesequizappversion2.R;
import com.example.japanesequizappversion2.StudyScreen.HiraganaKatakanaSelectionActivity;
import com.example.japanesequizappversion2.StudyScreen.PracticeHandWritingActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    ImageView infoImageView;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        infoImageView = findViewById(R.id.infomation);
        TextView textView = findViewById(R.id.textView8);
        mAuth = FirebaseAuth.getInstance();

        SessionManager sessionManager = new SessionManager(MainActivity.this, SessionManager.SESSION_USERSESSION);
        HashMap<String, String> usersDetails = sessionManager.getUsersDetailFromSession();

        String fullName = usersDetails.get(SessionManager.KEY_FULLNAME);
        String phoneNumber = usersDetails.get(SessionManager.KEY_PHONENUMBER);

        textView.setText(fullName);
        fetchData();
    }

    public void showUserInfoDialog(View view) {
        // Inflate the dialog layout
        View dialogView = getLayoutInflater().inflate(R.layout.user_info_dialog, null);

        // Find the views in the dialog layout
        TextView fullNameTextView = dialogView.findViewById(R.id.tvFullName);
        TextView userNameTextView = dialogView.findViewById(R.id.tvUserName);
        TextView emailTextView = dialogView.findViewById(R.id.tvEmail);
        TextView phoneNoTextView = dialogView.findViewById(R.id.tvPhoneNo);
        TextView dateTextView = dialogView.findViewById(R.id.tvDate);
        TextView genderTextView = dialogView.findViewById(R.id.tvGender);

        // Get the user information from your model
        User user = getUserInfo();

        // Set the text of the dialog views with the user information
        fullNameTextView.setText(getString(R.string.full_name)+": "+user.getFullName());
        userNameTextView.setText(getString(R.string.user_name)+": "+user.getUserName());
        emailTextView.setText(getString(R.string.email)+": "+user.getEmail());
        phoneNoTextView.setText(getString(R.string.phone_number)+": "+user.getPhoneNo());
        dateTextView.setText(getString(R.string.dob)+": "+user.getDate());
        genderTextView.setText(getString(R.string.gender)+": "+user.getGender());

        // Create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.user_info_title));
        builder.setView(dialogView);

        // Add a button to close the dialog
        builder.setNegativeButton(getString(R.string.close), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(getString(R.string.logout), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onBackPressed();
                    }
                })
        ;

        // Show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Method to get the user information from your model
    private User getUserInfo() {
        // You'll need to implement this method to get the user information from your model
        // For this example, I'll just create a new user with some sample data
        // Create a new instance of the SessionManager class
        SessionManager sessionManager = new SessionManager(MainActivity.this, SessionManager.SESSION_USERSESSION);

// Get the user details from the session
        HashMap<String, String> userDetails = sessionManager.getUsersDetailFromSession();

// Extract the data from the HashMap
        String fullName = userDetails.get(SessionManager.KEY_FULLNAME);
        String userName = userDetails.get(SessionManager.KEY_USERNAME);
        String email = userDetails.get(SessionManager.KEY_EMAIL);
        String phoneNo = userDetails.get(SessionManager.KEY_PHONENUMBER);
        String dob = userDetails.get(SessionManager.KEY_DATE);
        String gender = userDetails.get(SessionManager.KEY_GENDER);

        return new User(fullName, userName, email, phoneNo, dob, gender);
    }


    public void fetchData() {
        mDatabase.child("questions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Lesson> lessonArrayList = new ArrayList<>();
                for (DataSnapshot lessonList : dataSnapshot.getChildren()) {
                    ArrayList<QuestionDetail> questionDetailArrayList = new ArrayList<>();
                    for (DataSnapshot questionDetail : lessonList.getChildren()) {
                        String answer = "";
                        String question = "";
                        ArrayList<String> chooses;
                        ArrayList<String> answerChoose = new ArrayList<>();
                        ArrayList<Pair<String, String>> answerMatching = new ArrayList<>();
                        ArrayList<Pair<String, String>> questionMatching = new ArrayList<>();
                        d("TAG", "onDataChange: " + questionDetail.getKey());
                        switch (Integer.parseInt(Objects.requireNonNull(questionDetail.child("typequestion").getValue()).toString())) {
                            case 3:
                                for (DataSnapshot item : questionDetail.child("answermatching").getChildren()) {
                                    answerMatching.add(answerMatching.size(), new Pair(item.getKey(), item.getValue()));
                                }
                                for (DataSnapshot item : questionDetail.child("questionmatching").getChildren()) {
                                    questionMatching.add(questionMatching.size(), new Pair(item.getKey(), item.getValue()));
                                }
                                break;

                            default:
                                answer = Objects.requireNonNull(questionDetail.child("answer").getValue()).toString();
                                question = Objects.requireNonNull(questionDetail.child("question").getValue()).toString();
                                if (questionDetail.child("answerchose").getValue() != null) {
                                    chooses = (ArrayList<String>) questionDetail.child("answerchose").getValue();
                                    answerChoose.addAll(chooses);
                                }
                                break;
                        }
                        questionDetailArrayList.add(new QuestionDetail(
                                answer,
                                question,
                                answerMatching,
                                questionMatching,
                                answerChoose,
                                Integer.parseInt(Objects.requireNonNull(questionDetail.child("typequestion").getValue()).toString())));

                    }
                    lessonArrayList.add(new Lesson(questionDetailArrayList));
                }
                DataManager.questions.setLessons(lessonArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
            }
        });

        mDatabase.child("learndekiru").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<LessionDekiru> lessionDekiruArrayList = new ArrayList<>();
                for (DataSnapshot lessonList : snapshot.getChildren()) {

                    ArrayList<Pair<String, Kanji>> listKanji = new ArrayList<>();
                    ArrayList<Pair<String, Word>> newWordList = new ArrayList<>();
                    ArrayList<Grama> gramaArrayList = new ArrayList<>();
                    for (DataSnapshot lesionDetail : lessonList.getChildren()) {
                        for (DataSnapshot data : lesionDetail.getChildren()) {
                            d("TAG", "learndekiru: " + data);
                            if (Objects.equals(lesionDetail.getKey(), "kanji")) {
                                String mean = "";
                                String read = "";
                                for (DataSnapshot item : data.getChildren()) {
                                    if (Objects.equals(item.getKey(), "mean")) {
                                        mean = item.getValue().toString();
                                    }
                                    if (Objects.equals(item.getKey(), "read")) {
                                        read = item.getValue().toString();
                                    }
                                }
                                listKanji.add(new Pair<>(data.getKey(), new Kanji(mean, read)));
                            }

                            if (Objects.equals(lesionDetail.getKey(), "newword")) {
                                d("TAG", "learndekiru: " + data);
                                String mean ="";
                                for (DataSnapshot item : data.getChildren()){
                                    if (Objects.equals(item.getKey(), "mean")) {
                                        mean = item.getValue().toString();
                                    }
                                }
                                newWordList.add(new Pair<>(data.getKey(), new Word(mean)));
                            }

                            if (Objects.equals(lesionDetail.getKey(), "nguphap")) {
                                d("TAG", "learndekiru: " + data);
                                String note = "";
                                String mean = "";
                                Sentence sentence = new Sentence();
                                Content content = new Content();
                                for (DataSnapshot gramar : data.getChildren()) {
                                    if (Objects.equals(gramar.getKey(), "note")) {
                                        note = gramar.getValue().toString();
                                    }

                                    if (Objects.equals(gramar.getKey(), "mean")) {
                                        mean = gramar.getValue().toString();
                                    }
                                    if (Objects.equals(gramar.getKey(), "sentence")) {
                                        String vi = "";
                                        String jp = "";
                                        for (DataSnapshot itemContent : gramar.getChildren()) {
                                            if (Objects.equals(itemContent.getKey(), "vi")) {
                                                vi = itemContent.getValue().toString();
                                            }

                                            if (Objects.equals(itemContent.getKey(), "jp")) {
                                                jp = itemContent.getValue().toString();
                                            }
                                        }
                                        sentence.setJp(jp);
                                        sentence.setVi(vi);
                                    }

                                    if (Objects.equals(gramar.getKey(), "content")) {
                                        String first="";
                                        String second="";
                                        for (DataSnapshot itemContent : gramar.getChildren()) {
                                            if (Objects.equals(itemContent.getKey(), "first")) {
                                                first = itemContent.getValue().toString();
                                            }
                                            if (Objects.equals(itemContent.getKey(), "second")) {
                                                second = itemContent.getValue().toString();
                                            }
                                            //content = new Pair<>(itemContent.getKey(), itemContent.getValue().toString());
                                        }
                                        content.setFirst(first);
                                        content.setSecond(second);
                                    }
                                }
                                gramaArrayList.add(new Grama(content, mean, note, sentence));
                            }
                        }
                    }
                    lessionDekiruArrayList.add(new LessionDekiru(listKanji, newWordList, gramaArrayList));
                }
                DataManager.lesionDekiruArrayList.addAll(lessionDekiruArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void handWriting(View view) {
        Intent intent = new Intent(getApplicationContext(), PracticeHandWritingActivity.class);
        startActivity(intent);
    }

    public void flashCardDekiru(View view) {
        Intent intent = new Intent(getApplicationContext(), LessionDekiruActivity.class);
        startActivity(intent);
    }
    public void btn_hiragana_katakana(View view) {
        Intent intent = new Intent(getApplicationContext(), HiraganaKatakanaSelectionActivity.class);
        startActivity(intent);
    }

    public void trainingSelection(View view) {
        Intent intent = new Intent(getApplicationContext(), TrainingActivity.class);
        startActivity(intent);
    }
    public void returnButton(View view) {
        onBackPressed();
    }




}