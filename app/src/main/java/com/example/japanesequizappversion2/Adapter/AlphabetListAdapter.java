package com.example.japanesequizappversion2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.japanesequizappversion2.Model.KanamojiModel;
import com.example.japanesequizappversion2.R;
import com.example.japanesequizappversion2.StudyScreen.PatternHandWritingActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlphabetListAdapter extends ArrayAdapter<KanamojiModel> {

    // constructor for our list view adapter.
    public AlphabetListAdapter(@NonNull Context context, ArrayList<KanamojiModel> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // below line is use to inflate the
        // layout for our item of list view.
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.image_alphabet_item, parent, false);
        }

        // after inflating an item of listview item
        // we are getting data from array list inside
        // our modal class.
        KanamojiModel dataModel = getItem(position);

        // initializing our UI components of list view item.
        TextView textView = listitemView.findViewById(R.id.textView);
        ImageView imageView = listitemView.findViewById(R.id.image);

        // after initializing our items we are
        // setting data to our view.
        // below line is use to set data to our text view.
        textView.setText(dataModel.getTextModel());

        // in below line we are using Picasso to load image
        // from URL in our Image VIew.
        Picasso.get().load(dataModel.getImgUrl()).into(imageView);

        // below line is use to add item
        // click listener for our item of list view.
        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on the item click on our list view.
                // we are displaying a toast message.
//                Toast.makeText(getContext(), "Item clicked is : " + dataModel.getTextModel(), Toast.LENGTH_SHORT).show();
                if (dataModel.getGifUrl() == null) {

                } else {
                    Intent intent = new Intent(getContext(), PatternHandWritingActivity.class);
                    intent.putExtra("gifUrl", dataModel.getGifUrl());
                    intent.putExtra("textModel", dataModel.getTextModel());
                    getContext().startActivity(intent);
                }
            }
        });
        return listitemView;
    }
}
