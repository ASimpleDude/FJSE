package com.example.japanesequizappversion2.MainScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.japanesequizappversion2.Adapter.DekiruListAdapter;
import com.example.japanesequizappversion2.Model.DekiruListModel;
import com.example.japanesequizappversion2.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DekiruListActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView mListView;
    private List<DekiruListModel> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dekiru_list);

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Initialize ListView
        mListView = findViewById(R.id.list_view);

        // Get data from Firebase Database
        mDataList = new ArrayList<>();
        mDatabase.child("Nhat1").child("Dekiru").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                // Convert DataSnapshot to MyData object
                DekiruListModel data = dataSnapshot.getValue(DekiruListModel.class);
                mDataList.add(data);

                // Update ListView adapter
                DekiruListAdapter adapter = new DekiruListAdapter(DekiruListActivity.this, mDataList);
                mListView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                // Handle data change
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // Handle data removal
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                // Handle data movement
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
            }
        });

        // Handle ListView item clicks
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item click
                DekiruListModel data = mDataList.get(position);
                Toast.makeText(DekiruListActivity.this, "Clicked item: " + data.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}