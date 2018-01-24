package com.strobertchs.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.paperdb.Paper;

public class FinalProject extends AppCompatActivity {
    Button studentButton;
    Button staffButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_project);
        //Initiate Paper
        Paper.init(this);

        final String firstName = Paper.book().read(SavedUsers.FIRSTNAME);
        final String lastName = Paper.book().read(SavedUsers.LASTNAME);
        final String username = Paper.book().read(SavedUsers.USER);

        studentButton = (Button) findViewById(R.id.studentButton);
        staffButton = (Button) findViewById(R.id.staffButton);

        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstName != null && lastName != null) {
                    if (!firstName.isEmpty() && !lastName.isEmpty()) {
                        Intent i = new Intent(FinalProject.this, Home.class);
                        startActivity(i);
                    }
                }
                else {
                    Intent i = new Intent(FinalProject.this, StudentSignIn.class);
                    startActivity(i);
                }
            }
        });

        staffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent i = new Intent(FinalProject.this, CafeteriaSignIn.class);
                startActivity(i);
            }
        });


        if(firstName != null && lastName != null)
        {
            final ProgressDialog mDialog = new ProgressDialog(FinalProject.this);
            mDialog.setMessage("Please wait a moment...");
            mDialog.show();
            if(!firstName.isEmpty() && !lastName.isEmpty()) {
                mDialog.dismiss();
                Intent i = new Intent(FinalProject.this, Home.class);
                startActivity(i);
            }
        }
    }
}




