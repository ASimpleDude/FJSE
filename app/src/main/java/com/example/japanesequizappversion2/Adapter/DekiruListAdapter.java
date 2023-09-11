package com.example.japanesequizappversion2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.japanesequizappversion2.Model.DekiruListModel;
import com.example.japanesequizappversion2.R;

import java.util.List;

public class DekiruListAdapter extends ArrayAdapter<DekiruListModel> {

    private Context mContext;
    private List<DekiruListModel> mDataList;

    public DekiruListAdapter(Context context, List<DekiruListModel> dataList) {
        super(context, R.layout.dekiru_list_item, dataList);
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.dekiru_list_item, parent, false);

        // Get MyData object at this position
        DekiruListModel data = mDataList.get(position);

        // Set title and description in TextViews
        TextView titleTextView = itemView.findViewById(R.id.title_text_view);
        TextView descriptionTextView = itemView.findViewById(R.id.description_text_view);
        titleTextView.setText(data.getTitle());
        descriptionTextView.setText(data.getDescription());

        return itemView;
    }
}