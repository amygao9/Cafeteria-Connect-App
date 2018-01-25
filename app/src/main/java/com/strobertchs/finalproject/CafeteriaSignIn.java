package com.strobertchs.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CafeteriaSignIn extends AppCompatActivity {
    TextView signInButton;
    EditText loginCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeteria_sign_in);

        signInButton = (TextView)findViewById(R.id.Login_Button_Text);
        loginCode = (EditText)findViewById(R.id.enterCode);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loginCode.getText().toString().equals("0000")) {
                    Toast.makeText(CafeteriaSignIn.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(CafeteriaSignIn.this, ServerOrderLay.class);
                    startActivity(i);
                }

                else{
                    Toast.makeText(CafeteriaSignIn.this, "Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
