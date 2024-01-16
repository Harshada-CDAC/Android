package com.example.myapplication;

import static android.provider.SyncStateContract.Helpers.update;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input;
    Spinner unit;
    TextView km, m, cm, mm, microm, nm, mile, yard, foot, inch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.input);
        unit = findViewById(R.id.unit);
        km = findViewById(R.id.km);
        m = findViewById(R.id.m);
        cm = findViewById(R.id.cm);
        mm = findViewById(R.id.mm);


        String[] arr = {"km", "m", "cm", "mm"};
        unit.setAdapter(new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arr));

        unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                update();
            }
        });
    }

    private void update() {
        if (!input.getText().toString().equals("") && !unit.getSelectedItem().toString().equals("")) {
            double in = Double.parseDouble(input.getText().toString());
            switch (unit.getSelectedItem().toString()) {
                case "km":
                    setKm(in);
                    break;
                case "m":
                    setKm(in/1000);
                    break;
                case "cm":
                    setKm(in/100000);
                    break;
                case "mm":
                    setKm(in/1000000);
                    break;

            }
        }
    }

    private void setKm(double km_in) {
        km.setText(String.valueOf(km_in));
        m.setText(String.valueOf(km_in*1000));
        cm.setText(String.valueOf(km_in*100000));
        mm.setText(String.valueOf(km_in*1000000));

    }

}