package com.example.a309project_leo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import com.example.a309project_leo.adapter.MyListAdapter;


/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    ListView listView;
    ArrayList<String> array = new ArrayList<>();
    EditText noteBox;
    Button addButton;
    //ArrayAdapter adapter;
    MyListAdapter adapter2;
    public SecondFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //adapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1,array);
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        listView = (ListView) view.findViewById(R.id.listview);
        //listView.setAdapter(adapter);
        noteBox = view.findViewById(R.id.shoppingItem);
        addButton = view.findViewById(R.id.add_button);

        adapter2 = new MyListAdapter(requireContext(),array);
        listView.setAdapter(adapter2);

        addButton.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        array.add(noteBox.getText().toString());
        noteBox.setText("");
        adapter2.notifyDataSetChanged();
    }
}