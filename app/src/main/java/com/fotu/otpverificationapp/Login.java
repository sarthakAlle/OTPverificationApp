package com.fotu.otpverificationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {
Button button;
ProgressBar progressBar;
EditText phone_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button=findViewById(R.id.get_otp_button);
        phone_no=findViewById(R.id.editTextPhone);
        progressBar=findViewById(R.id.progressBar_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if(!phone_no.getText().toString().trim().isEmpty()){
if(phone_no.getText().toString().trim().length()==10){
progressBar.setVisibility(View.VISIBLE);
button.setVisibility(View.GONE);
PhoneAuthProvider.getInstance().verifyPhoneNumber(
        "+91" + phone_no.getText().toString(), 60, TimeUnit.SECONDS, Login.this,
        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                progressBar.setVisibility(View.GONE);
                button.setVisibility(View.VISIBLE);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                progressBar.setVisibility(View.GONE);
                button.setVisibility(View.VISIBLE);
                Toast.makeText(Login.this, "sorry, verification failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String backend_otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(backend_otp, forceResendingToken);
                progressBar.setVisibility(View.GONE);
                button.setVisibility(View.VISIBLE);
                  Intent intent= new Intent(getApplicationContext(),verify.class);
                   intent.putExtra("mobile",phone_no.getText().toString());
                intent.putExtra("backendOTP",backend_otp);
                     startActivity(intent);
            }
        }
);

}
else{
    Toast.makeText(Login.this, "Phone number not valid", Toast.LENGTH_SHORT).show();
}
}else{
    Toast.makeText(Login.this, "Please provide phone number", Toast.LENGTH_SHORT).show();
}
            }
        });
    }

}