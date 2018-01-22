package com.strobertchs.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        final String firstName = Paper.book().read(savedUser.FIRSTNAME);
        final String lastName = Paper.book().read(savedUser.LASTNAME);
        final String username = Paper.book().read(savedUser.USER);

        // Initiate Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        studentButton = (Button) findViewById(R.id.studentButton);
        staffButton = (Button) findViewById(R.id.staffButton);

        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(FinalProject.this);
                mDialog.setMessage("Please wait a moment...");
                mDialog.show();
                if (firstName != null && lastName != null) {
                    if(!firstName.isEmpty() && !lastName.isEmpty()) {
                        table_user.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.child(username).exists()) {
                                    mDialog.dismiss();
                                    Intent i = new Intent(FinalProject.this, Home.class);
                                    startActivity(i);
                                }
                                else {
                                    mDialog.dismiss();
                                    Intent i = new Intent(FinalProject.this, student_sign_in.class);
                                    startActivity(i);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }
                else{
                    mDialog.dismiss();
                    Intent i = new Intent(FinalProject.this, student_sign_in.class);
                    startActivity(i);
                }
            }
        });

        staffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent i = new Intent(FinalProject.this, cafeteria_sign_in.class);
                startActivity(i);
            }
        });
    }
}
