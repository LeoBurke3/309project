package com.example.a309project_leo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;


public class ThirdFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String PREFS_NAME = "MyAppPreferences";
    private static final String KEY_GOAL_CAL = "key_goal_calories";


    Spinner genderSpin;
    Spinner activitySpin;
    EditText weightInput;
    EditText ageInput;
    EditText heightInput;
    Button update;
    public ThirdFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters


    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.third_fragment, container, false);

        genderSpin = view.findViewById(R.id.genderSpinner);
        activitySpin = view.findViewById(R.id.activitySpinner);

        weightInput = view.findViewById(R.id.weightInput);
        heightInput = view.findViewById(R.id.heightInput);
        ageInput = view.findViewById(R.id.ageInput);

        update = view.findViewById(R.id.updateButton);

        update.setOnClickListener(this::OnClick);
        setSpinners();
        return view;
    }

    public void OnClick(View view){
        if(TextUtils.isEmpty(weightInput.getText()) || TextUtils.isEmpty(ageInput.getText()) || TextUtils.isEmpty(heightInput.getText())){
            Toast.makeText(requireContext(), "Please fill all the fields :)", Toast.LENGTH_SHORT).show();
        } else {
            double weight = Integer.parseInt(weightInput.getText().toString());
            double age = Integer.parseInt(ageInput.getText().toString());
            double height = Integer.parseInt(heightInput.getText().toString());
            String gender = genderSpin.getSelectedItem().toString();
            String activity = activitySpin.getSelectedItem().toString();
            calculateCalories(height,weight,age,gender,activity);
        }

    }

    public void setSpinners(){
        ArrayAdapter<CharSequence> adapterActi = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.activity,
                android.R.layout.simple_spinner_item
        );
// Specify the layout to use when the list of choices appears.
        adapterActi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
        activitySpin.setAdapter(adapterActi);

        ArrayAdapter<CharSequence> adapterGen = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.gender,
                android.R.layout.simple_spinner_item
        );
// Specify the layout to use when the list of choices appears.
        adapterGen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
        genderSpin.setAdapter(adapterGen);
    }

    public void calculateCalories(double height, double weight, double age, String gender, String activity ){
        double calories = 0;
        if(Objects.equals(gender, "Male")){
           calories = (10 * weight) + (6.25 * height) - 5 * (age + 5);
        } else if(Objects.equals(gender, "Female")) {
            calories = (10 * weight) + (6.25 * height) - 5 * (age - 161);
        }
        switch(activity){
            case "Sedentary":
                calories = calories * 1.2;
                break;
            case "Lightly Active":
                calories = calories * 1.375;
                break;
            case "Moderately Active":
                calories = calories * 1.55;
                break;
            case "Very Active":
                calories = calories * 1.725;
                break;
        }
        setGoalCal(calories);
    }
    public void setGoalCal(double goalCalories){
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_GOAL_CAL, (int) goalCalories);
        editor.apply();

        Toast.makeText(getActivity(), "Goal calories set to " + (int)goalCalories, Toast.LENGTH_SHORT).show();
    }
}