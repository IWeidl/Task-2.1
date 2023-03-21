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

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        double result = 0;
        if ("inches".equals(sourceUnit)) {
            switch (destinationUnit) {
                case "inches":
                    result = value;
                    break;
                case "centimeters":
                    result = value * 2.54;
                    break;
            }
        }
        else if ("centimeters".equals(sourceUnit)) {
            switch (destinationUnit) {
                case "inches":
                    result = value / 2.54;
                    break;
                case "centimeters":
                    result = value;
                    break;
            }
        }
        else if ("pounds".equals(sourceUnit)) {
            switch (destinationUnit) {
                case "pounds":
                    result = value;
                    break;
                case "kilograms":
                    result = value * 0.453592;
                    break;
            }
        }
        else if ("kilograms".equals(sourceUnit)) {
            switch (destinationUnit) {
                case "pounds":
                    result = value / 0.453592;
                    break;
                case "kilograms":
                    result = value;
                    break;
            }
        }
        else if ("Celsius".equals(sourceUnit)) {
            switch (destinationUnit) {
                case "Celsius":
                    result = value;
                    break;
                case "Fahrenheit":
                    result = (value * 1.8) + 32;
                    break;
            }
        }
        else if ("Fahrenheit".equals(sourceUnit)) {
            switch (destinationUnit) {
                case "Celsius":
                    result = (value - 32) / 1.8;
                    break;
                case "Fahrenheit":
                    result = value;
                    break;
            }
        }

        // Setting precision of a double is very hard to do, because it's stored in binary.
        // Convert to BigDecimal so we can easily round.
        BigDecimal bigDecimal = BigDecimal.valueOf(result);
        bigDecimal = bigDecimal.setScale(3, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}