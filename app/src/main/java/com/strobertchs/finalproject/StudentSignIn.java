package com.strobertchs.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class StudentSignIn extends AppCompatActivity {

    EditText editFirstName;
    EditText editLastName;
    EditText editEmail;
    EditText editPassword;
    TextView editLoginButtonText;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_in);
        //Initiate Paper
        Paper.init(this);

        // Initiate Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        editFirstName = (EditText) findViewById(R.id.Name);
        editLastName = (EditText) findViewById(R.id.LastName);
        editEmail = (EditText) findViewById(R.id.EmailAddress);
        editPassword = (EditText) findViewById(R.id.Password);
        editLoginButtonText = (TextView) findViewById(R.id.Login_Button_Text);

        editLoginButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(StudentSignIn.this);
                mDialog.setMessage("Please wait a moment...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mDialog.dismiss();

                        if(!editFirstName.getText().toString().equals("") && !editLastName.getText().toString().equals("")
                                && editEmail.getText().toString().substring(editEmail.getText().toString().length() - 11)
                                .equals("ycdsbk12.ca") && !editPassword.getText().toString().equals("")) {
                            user = new User(editEmail.getText().toString(), editFirstName.getText().toString(), editLastName.getText().toString(), editPassword.getText().toString());
                            table_user.child(editLastName.getText().toString() + "," + editFirstName.getText().toString()).setValue(user);
                            Toast.makeText(StudentSignIn.this, "Registration successful!", Toast.LENGTH_SHORT).show();

                            // Save user and password
                            SavedUsers.currentUser = user;
                            Paper.book().write(SavedUsers.FIRSTNAME, editFirstName.getText().toString());
                            Paper.book().write(SavedUsers.LASTNAME, editLastName.getText().toString());
                            Paper.book().write(SavedUsers.USER, editLastName.getText().toString() + "," + editFirstName.getText().toString());

                            Intent i = new Intent(StudentSignIn.this, Home.class);
                            startActivity(i);
                        }

                        else if(editFirstName.getText().toString().equals("") || editLastName.getText().toString().equals("")) {
                            Toast.makeText(StudentSignIn.this, "Please fill out your name", Toast.LENGTH_SHORT).show();
                        }

                        else if(!editEmail.getText().toString().substring(editEmail.getText().toString().length() - 11).equals("ycdsbk12.ca")) {
                            Toast.makeText(StudentSignIn.this, "Please use your school email", Toast.LENGTH_SHORT).show();
                        }

                        else if(editPassword.getText().toString().equals("")){
                            Toast.makeText(StudentSignIn.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}