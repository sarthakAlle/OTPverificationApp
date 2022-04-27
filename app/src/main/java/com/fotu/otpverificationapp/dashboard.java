package com.fotu.otpverificationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class dashboard extends AppCompatActivity {

        private TextView myTextView;
Button read_sms;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            myTextView = findViewById(R.id.sms_display_textview);
            ActivityCompat.requestPermissions(dashboard.this, new String[]{Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
            read_sms=findViewById(R.id.read_sms);
            read_sms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Read_SMS();
                }
            });
        }

        public void Read_SMS(){

            Cursor cursor = getContentResolver().query(Uri.parse("content://sms"), null, null,null,null);
            cursor.moveToFirst();

            myTextView.setText(cursor.getString(12));

        }
    }
