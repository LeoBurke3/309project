package com.example.a309project_leo;

import android.app.AlertDialog;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    TextView calorieNum;
    ProgressBar progressBar;
    Button carbButton;
    Button protButton;
    Button fatButton;
    int fatTotal;
    int proteinTotal;
    int carbTotal;

    private static final String PREFS_NAME = "MyAppPreferences";
    private static final String KEY_PROTEIN = "key_protein";
    private static final String KEY_CARBS = "key_carbs";
    private static final String KEY_FAT = "key_fat";
    private static final String KEY_CAL = "key_calories";
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
        carbButton = view.findViewById(R.id.button);
        protButton = view.findViewById(R.id.button2);
        fatButton = view.findViewById(R.id.button3);

        progressBar = view.findViewById(R.id.progressBar);


        protButton.setOnClickListener(buttonClickListener);
        carbButton.setOnClickListener(buttonClickListener);
        fatButton.setOnClickListener(buttonClickListener);

        protein(view);
        fat(view);
        carb(view);
        calories(view);

        progressBar.setProgress(getNumberFromPreferences(KEY_CAL));
        return view;
    }

    public void calories(View view){
        int currentCalories = (proteinTotal*4)+(carbTotal*4)+(fatTotal*9);
        saveNumberToPreferences(KEY_CAL,currentCalories);
        int goalCalories = getResources().getInteger(R.integer.goalCalories);
        calorieNum = view.findViewById(R.id.calorieNum);
        calorieNum.setText(String.valueOf(currentCalories) + '/' + String.valueOf(goalCalories));
    }
    public void protein(View view){
        proteinTotal = getNumberFromPreferences(KEY_PROTEIN);
        TextView proteinNum = view.findViewById(R.id.proteinNum);
        proteinNum.setText(String.valueOf(proteinTotal) + "/140");
    }
    public void fat(View view){
        fatTotal = getNumberFromPreferences(KEY_FAT);
        TextView fatNum = view.findViewById(R.id.fatNum);
        fatNum.setText(String.valueOf(fatTotal) + "/62");
    }
    public void carb(View view){
        carbTotal = getNumberFromPreferences(KEY_CARBS);
        TextView carbNum = view.findViewById(R.id.carbNum);
        carbNum.setText(String.valueOf(carbTotal) + "/280");
    }

    public View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.button){
               showDialog(KEY_CARBS);
            } else if(v.getId() == R.id.button2){
                showDialog(KEY_PROTEIN);
            } else if (v.getId() == R.id.button3) {
                showDialog(KEY_FAT);
            }
        }
    };

    public void showDialog(final String key){
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_input, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();

        EditText inputNumber = dialogView.findViewById(R.id.input_number);
        Button submitButton = dialogView.findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputNumber.getText().toString();
                if (!input.isEmpty()) {
                    int number = Integer.parseInt(input);
                    saveNumberToPreferences(key, number);
                    // Handle the submitted number here
                    /*protein(getView());
                    fat(getView());
                    carb(getView());
                    calories(getView());*/
                    updateView();
                    updateWidgets();
                    dialog.dismiss();
                } else {
                    Toast.makeText(requireContext(), "Please enter a number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }
    private void saveNumberToPreferences(String key, int number) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, number);
        editor.apply();
    }

    private int getNumberFromPreferences(String key) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0); // Default value is 0 if not found
    }

    public void updateView(){
        protein(getView());
        fat(getView());
        carb(getView());

        calories(getView());

        progressBar.setProgress(getNumberFromPreferences(KEY_CAL));
    }

    private void updateWidgets() {
        Context context = requireContext();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisWidget = new ComponentName(context, CaloriesWidget.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int appWidgetId : appWidgetIds) {
            CaloriesWidget.updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

}