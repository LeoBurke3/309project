package com.example.a309project_leo.adapter;// com.example.a309project_leo.adapter.MyListAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.example.a309project_leo.R;

import java.util.List;

public class MyListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> values;

    public MyListAdapter(Context context, List<String> values) {
        super(context, R.layout.list_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView textView = rowView.findViewById(R.id.textViewItem);
        Button deleteButton = rowView.findViewById(R.id.buttonDelete);

        textView.setText(values.get(position));

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                values.remove(position);
                notifyDataSetChanged();
            }
        });

        return rowView;
    }
}
