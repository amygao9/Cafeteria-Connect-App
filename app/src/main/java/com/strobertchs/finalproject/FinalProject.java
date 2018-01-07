package com.strobertchs.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinalProject extends AppCompatActivity {
    Button studentButton;
    Button staffButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_project);

        studentButton = (Button) findViewById(R.id.studentButton);
        staffButton = (Button) findViewById(R.id.staffButton);

        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (savedUser.currentUser == null) {
                    Intent i = new Intent(FinalProject.this, student_sign_in.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(FinalProject.this, Home.class);
                    startActivity(i);
                }
            }
        });
    }
}
