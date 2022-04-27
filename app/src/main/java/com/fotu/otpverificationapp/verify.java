package com.fotu.otpverificationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class verify extends AppCompatActivity {
EditText otp1,otp2,otp3,otp4,otp5,otp6;

String getBackendOtp;
TextView resend_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        final Button sumbit=findViewById(R.id.submit);
        otp1=findViewById(R.id.otp_1);
        otp2=findViewById(R.id.otp_2);
        otp3=findViewById(R.id.otp_3);
        otp4=findViewById(R.id.otp_4);
        otp5=findViewById(R.id.otp_5);
        otp6=findViewById(R.id.otp_6);
        resend_otp=findViewById(R.id.resend_otp);
        final ProgressBar progressBar=findViewById(R.id.progressBar_verify);

        TextView textView= findViewById(R.id.display_text_view);
        textView.setText(String.format("+91-%s",getIntent().getStringExtra("mobile")));
        getBackendOtp= getIntent().getStringExtra("backendOTP");

        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!otp1.getText().toString().trim().isEmpty()||!otp2.getText().toString().trim().isEmpty()||!otp3.getText().toString().trim().isEmpty()||
                !otp4.getText().toString().trim().isEmpty()||!otp5.getText().toString().trim().isEmpty()||!otp6.getText().toString().trim().isEmpty()
                ){
              String enteredOtp=otp1.getText().toString()+otp2.getText().toString()+otp3.getText().toString()+
                      otp4.getText().toString()+otp5.getText().toString()+otp6.getText().toString();
if(getBackendOtp!=null){
progressBar.setVisibility(View.VISIBLE);
sumbit.setVisibility(View.GONE);
    PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(getBackendOtp,enteredOtp);
    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            progressBar.setVisibility(View.GONE);
            sumbit.setVisibility(View.VISIBLE);
            if(task.isSuccessful()){
                Intent intent=new Intent(getApplicationContext(),dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }else{
                Toast.makeText(verify.this, "Please enter correct otp", Toast.LENGTH_SHORT).show();
            }
        }
    });
}else{
    Toast.makeText(verify.this, "please check your internet connection", Toast.LENGTH_SHORT).show();
}
                }else{
                    Toast.makeText(verify.this, "OTP blocks not filled", Toast.LENGTH_SHORT).show();
                }
            }
        });
        numberOtpMove();
        resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"), 60, TimeUnit.SECONDS, verify.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(verify.this, "sorry, verification failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String new_backend_otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(new_backend_otp, forceResendingToken);
                           getBackendOtp=new_backend_otp;
                                Toast.makeText(verify.this, "OTP sent successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });
    }

    private void numberOtpMove() {
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(!otp1.getText().toString().trim().isEmpty()){
        otp2.requestFocus();
       }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!otp2.getText().toString().trim().isEmpty()){
                    otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!otp3.getText().toString().trim().isEmpty()){
                    otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!otp4.getText().toString().trim().isEmpty()){
                    otp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!otp5.getText().toString().trim().isEmpty()){
                    otp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!otp6.getText().toString().trim().isEmpty()){
                    otp1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}