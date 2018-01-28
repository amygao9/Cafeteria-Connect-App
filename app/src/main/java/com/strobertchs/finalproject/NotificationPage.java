package com.strobertchs.finalproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by jenny on 2018-01-26.
 */

public class NotificationPage extends AppCompatActivity {

    private static final String TAG = "NotificationPage";

    String dataTitle, dataMessage;
    EditText title, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        // Handle possible data accompanying notification message.
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                if (key.equals("title")) {
                    dataTitle = (String) getIntent().getExtras().get(key);
                }
                if (key.equals("message")) {
                    dataMessage = (String) getIntent().getExtras().get(key);
                }
            }

            showAlertDialog();

        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(dataTitle);
        builder.setMessage(dataMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                Intent intent = new Intent(NotificationPage.this, FinalProject.class);
                startActivity(intent);
            }
        });
        builder.show();
    }
}