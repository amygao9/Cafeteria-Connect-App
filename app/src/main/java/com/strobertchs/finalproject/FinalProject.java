package com.strobertchs.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FinalProject extends AppCompatActivity implements View.OnClickListener {
    Button buttonWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_project);


        buttonWelcome = (Button) findViewById(R.id.button);
        buttonWelcome.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        i = new Intent(this, student_sign_in.class);
        startActivity(i);
    }
}
