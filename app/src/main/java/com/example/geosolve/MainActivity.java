package com.example.geosolve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.geosolve.ActivityFragment.SolveActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton solve_button = findViewById(R.id.solve_button);
        solve_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 RuleBase.solve(DrawFragment.drawCanvas);
                Intent intent = new Intent(MainActivity.this, SolveActivity.class);
                startActivity(intent);
            }
        });
    }
}
