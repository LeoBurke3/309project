package com.example.a309project_leo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    TextView calorieNum;
    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.first_fragment, container, false);

        calories(view);
        return view;
    }

    public void calories(View view){
        int currentCalories = getResources().getInteger(R.integer.currentCalories);
        int goalCalories = getResources().getInteger(R.integer.goalCalories);
        calorieNum = view.findViewById(R.id.calorieNum);
        calorieNum.setText(String.valueOf(currentCalories) + '/' + String.valueOf(goalCalories));
    }
}