package com.coda.burnedcaloriescalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView.OnEditorActionListener;
import org.w3c.dom.Text;
import android.widget.ArrayAdapter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class BurnCaloriesCalculatorActivity extends AppCompatActivity implements OnEditorActionListener {

    private SharedPreferences sharedValues;
    private EditText weightET;
    private TextView milesTV;
    private SeekBar milesSeekBar;
    private TextView caloriesBurnedTV;
    private Spinner feetSpinner;
    private Spinner inchesSpinner;
    private TextView bmiTV;
    private EditText nameET;
    private int miles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burn_calories_calculator);


        //get refs to widgets
        weightET = (EditText) findViewById(R.id.weightET);
        milesTV = (TextView) findViewById(R.id.milesTV);
        milesSeekBar = (SeekBar) findViewById(R.id.milesSeekbar);
        caloriesBurnedTV = (TextView) findViewById(R.id.caloriesBurnedTV);
        feetSpinner = (Spinner) findViewById((R.id.feetSpinner));
        inchesSpinner = (Spinner) findViewById(R.id.inchesSpinner);
        bmiTV = (TextView) findViewById(R.id.bmiTV);
        nameET = (EditText) findViewById(R.id.nameET);

        //set listeners
        weightET.setOnEditorActionListener(this);
        caloriesBurnedTV.setOnEditorActionListener(this);
        bmiTV.setOnEditorActionListener(this);
        nameET.setOnEditorActionListener(this);
        milesTV.setOnEditorActionListener(this);
        milesSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                milesTV.setText(progress +" mi");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                miles = seekBar.getProgress();
            }
        });

        //Spinner feet array adapter
        ArrayAdapter<CharSequence> feetAdapter = ArrayAdapter.createFromResource(
                this, R.array.feet,android.R.layout.simple_spinner_item
        );
        //layout for items in list
        feetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //set adapter for feet spinner
        feetSpinner.setAdapter(feetAdapter);

        //Spinner feet array adapter
        ArrayAdapter<CharSequence> inchesAdapter = ArrayAdapter.createFromResource(
                this, R.array.inches,android.R.layout.simple_spinner_item
        );
        //layout for items in list
        inchesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //set adapter for feet spinner
        inchesSpinner.setAdapter(inchesAdapter);

        sharedValues = getSharedPreferences("Shared Values", MODE_PRIVATE);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        int weight;
        double caloriesBurned;

        //convert to number
        String weightSTR = weightET.getText().toString();

        if(weightSTR.equals("")){
            weight=0;
        }
        else {
            weight = Integer.parseInt(weightSTR);
        }

        //calc
        caloriesBurned = 0.75 * weight * miles;

        //display result
        caloriesBurnedTV.setText(Double.toString(caloriesBurned));

        return false;
    }

    @Override
    public void onPause(){
        Editor editor = sharedValues.edit();
        //editor.putString("weight", weightET);

        super.onPause();
    }

    public void onResume(){

        super.onResume();

    }


}
