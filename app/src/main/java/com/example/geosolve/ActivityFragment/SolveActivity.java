package com.example.geosolve.ActivityFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.geosolve.R;
import com.example.geosolve.RecycleAdapter;
import com.example.geosolve.StepSlove;

public class SolveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve);

        ImageButton back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecycleAdapter.addItem(new StepSlove("Выражение", "Правило"));
        for (int i = 1; i < 100; i += i) {
            RecycleAdapter.addItem(new StepSlove("%s + %s = %s", "Сложение",
                    Integer.toString(i), Integer.toString(i), Integer.toString(i+i)));
        }
    }
}
