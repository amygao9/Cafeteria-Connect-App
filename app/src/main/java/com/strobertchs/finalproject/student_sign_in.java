package com.strobertchs.finalproject;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class student_sign_in extends AppCompatActivity {

    EditText editFirstName;
    EditText editLastName;
    EditText editEmail;
    EditText editPassword;
    TextView editLoginButtonText;

    // Initiate Firebase
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_user = database.getReference("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_in);

        editFirstName = (EditText) findViewById(R.id.Name);
        editLastName = (EditText) findViewById(R.id.LastName);
        editEmail = (EditText) findViewById(R.id.EmailAddress);
        editPassword = (EditText) findViewById(R.id.Password);
        editLoginButtonText = (TextView) findViewById(R.id.Login_Button_Text);

        editLoginButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(student_sign_in.this);
                mDialog.setMessage("Please wait a moment...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mDialog.dismiss();

                        if (editEmail.getText().toString().substring(editEmail.getText().toString().length() - 12, editEmail.getText().toString().length() - 1).equals("@ycdsbk12.ca")) {
                            User user = new User(editEmail.getText().toString(), editPassword.getText().toString());
                            table_user.child(editLastName.getText().toString() + "," + editFirstName.getText().toString()).setValue(user);

                            Toast.makeText(student_sign_in.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Toast.makeText(student_sign_in.this, "Please use your school email", Toast.LENGTH_SHORT).show();
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
