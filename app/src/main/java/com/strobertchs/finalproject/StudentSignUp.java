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
import com.strobertchs.finalproject.model.SavedUsers;
import com.strobertchs.finalproject.model.User;
import com.strobertchs.finalproject.utils.Constants;
import com.strobertchs.finalproject.utils.ViewUtils;

public class StudentSignUp extends AppCompatActivity {

    EditText editFirstName;
    EditText editLastName;
    EditText editEmail;
    EditText editPassword;
    TextView editLoginButtonText;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        // Initiate Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference usersRef = database.getReference("User");

        editFirstName = (EditText) findViewById(R.id.Name);
        editLastName = (EditText) findViewById(R.id.LastName);
        editEmail = (EditText) findViewById(R.id.EmailAddress);
        editPassword = (EditText) findViewById(R.id.Password);
        editLoginButtonText = (TextView) findViewById(R.id.Login_Button_Text);

        editLoginButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String txtEmail = editEmail.getText().toString().trim();
                final String txtFirstName = editFirstName.getText().toString().trim();
                final String txtLastName = editLastName.getText().toString().trim();
                final String txtPassword = editPassword.getText().toString().trim();

                //Validate first name and last name
                if(txtFirstName.equals("") || txtLastName.equals("")) {
                    Toast.makeText(StudentSignUp.this, "Please fill out your name", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Validate email address
                if((txtEmail.length() <= Constants.VALID_EMAIL_SERVER.length())||
                        !txtEmail.toLowerCase().endsWith(Constants.VALID_EMAIL_SERVER.toLowerCase())) {
                    Toast.makeText(StudentSignUp.this, "Please use your school email", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Validate password
                if(txtPassword.equals("")){
                    Toast.makeText(StudentSignUp.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Save into Firebase database
                final ProgressDialog mDialog = new ProgressDialog(StudentSignUp.this);
                mDialog.setMessage("Please wait a moment...");
                mDialog.show();

                usersRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mDialog.dismiss();

                        if(!txtFirstName.equals("") &&
                                !txtLastName.equals("") &&
                                (txtEmail.length()>Constants.VALID_EMAIL_SERVER.length()) &&
                                txtEmail.toLowerCase().endsWith(Constants.VALID_EMAIL_SERVER.toLowerCase()) &&
                                !txtPassword.equals("")) {
                            user = new User(txtEmail, txtFirstName, txtLastName, txtPassword);
                            usersRef.child(ViewUtils.encodeEmailAddress(txtEmail)).setValue(user);

                            Toast.makeText(StudentSignUp.this, "Registration successful!", Toast.LENGTH_SHORT).show();

                            // Save user and password
                            SavedUsers.saveCurrentUser(user);

                            //Start Home page
                            Intent i = new Intent(StudentSignUp.this, Home.class);
                            startActivity(i);
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