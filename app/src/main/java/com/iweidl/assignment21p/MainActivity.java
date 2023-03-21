package com.iweidl.assignment21p;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editTextValue;
    Button buttonConvert;
    Spinner spinnerSourceUnit;
    Spinner spinnerDestinationUnit;
    TextView labelConvertedValue;
    TextView labelConvertedUnit;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextValue = findViewById(R.id.editTextValue);
        buttonConvert = findViewById(R.id.buttonConvert);
        spinnerSourceUnit = findViewById(R.id.spinnerSourceUnit);
        spinnerDestinationUnit = findViewById(R.id.spinnerDestinationUnit);
        labelConvertedValue = findViewById(R.id.labelConvertedValue);
        labelConvertedUnit = findViewById(R.id.labelConvertedUnit);

        buttonConvert.setOnClickListener(view -> {
            labelConvertedValue.setText(Double.toString(ConvertUnit(
                    Double.parseDouble(editTextValue.getText().toString()),
                    spinnerSourceUnit.getSelectedItem().toString(),
                    spinnerDestinationUnit.getSelectedItem().toString())));

            labelConvertedUnit.setText(spinnerDestinationUnit.getSelectedItem().toString());
        });

    }

    private double ConvertUnit(double value, String sourceUnit, String destinationUnit)
    {
        if ("inches".equals(sourceUnit)) {
            switch (destinationUnit) {
                case "inches":
                    return value;
                case "centimeters":
                    return value * 2.54;
            }
        }
        else if ("centimeters".equals(sourceUnit)) {
            switch (destinationUnit) {
                case "inches":
                    return value / 2.54;
                case "centimeters":
                    return value;
            }
        }
        else if ("pounds".equals(sourceUnit)) {
            switch (destinationUnit) {
                case "pounds":
                    return value;
                case "kilograms":
                    return value * 0.453592;
            }
        }
        else if ("kilograms".equals(sourceUnit)) {
            switch (destinationUnit) {
                case "pounds":
                    return value / 0.453592;
                case "kilograms":
                    return value;
            }
        }
        else if ("Celsius".equals(sourceUnit)) {
            switch (destinationUnit) {
                case "Celsius":
                    return value;
                case "Fahrenheit":
                    return (value * 1.8) + 32;
            }
        }
        else if ("Fahrenheit".equals(sourceUnit)) {
            switch (destinationUnit) {
                case "Celsius":
                    return (value - 32) / 1.8;
                case "Fahrenheit":
                    return value;
            }
        }

        return 0;
    }
}