package com.strobertchs.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
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

public class StudentSignIn extends AppCompatActivity {

    EditText editEmail;
    EditText editPassword;
    CardView signInCardView;
    CardView signUpCardView;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_in);

        // Initiate Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference usersRef = database.getReference("User");


        editEmail = (EditText) findViewById(R.id.EmailAddress);
        editPassword = (EditText) findViewById(R.id.Password);
        signInCardView = (CardView) findViewById(R.id.signInCardView);
        signUpCardView = (CardView)findViewById(R.id.signUpCardView);

        signInCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String txtEmail = editEmail.getText().toString().trim();
                final String txtPassword = editPassword.getText().toString().trim();

                //Validate email address
                if((txtEmail.length() <= Constants.VALID_EMAIL_SERVER.length())||
                        !txtEmail.toLowerCase().endsWith(Constants.VALID_EMAIL_SERVER.toLowerCase())) {
                    Toast.makeText(StudentSignIn.this, "Please use your school email", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Validate password
                if(txtPassword.equals("")){
                    Toast.makeText(StudentSignIn.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }

                final ProgressDialog mDialog = new ProgressDialog(StudentSignIn.this);
                mDialog.setMessage("Please wait a moment...");
                mDialog.show();

                usersRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mDialog.dismiss();
                        DataSnapshot ds = dataSnapshot.child(ViewUtils.encodeEmailAddress(txtEmail));
                        if(ds!=null && ds.exists() && ds.child("password").getValue().toString().equals(txtPassword)){
                            //Save user in Paper.book()
                            user = new User(ds.child("email").getValue().toString(),
                                    ds.child("firstName").getValue().toString(),
                                    ds.child("lastName").getValue().toString(), null);
                            SavedUsers.saveCurrentUser(user);

                            Intent i = new Intent(StudentSignIn.this, Home.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(StudentSignIn.this, "Not a valid user access!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        signUpCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentSignIn.this, StudentSignUp.class);
                startActivity(i);
            }
        });
    }
}