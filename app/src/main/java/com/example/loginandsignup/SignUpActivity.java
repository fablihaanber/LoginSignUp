package com.example.loginandsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    TextView loginNeeded;
    EditText passwordSignUp,confirmPasswordSignUp,emailSignUp;
    Button signUp;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        loginNeeded=findViewById(R.id.LoginNeededID);
        passwordSignUp=findViewById(R.id.userpasswordSignUpID);
        confirmPasswordSignUp=findViewById(R.id.userConfirmPasswordSignUpID);
        emailSignUp=findViewById(R.id.userSignUpEmailID);
        signUp=findViewById(R.id.signUpID);



        mFirebaseAuth=FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailSignUp.getText().toString();
                String password=passwordSignUp.getText().toString();
                String confirmPassword=confirmPasswordSignUp.getText().toString();

                if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(SignUpActivity.this,"Fields are empty",Toast.LENGTH_SHORT).show();

                }
                else if(email.isEmpty()){
                    emailSignUp.setError("Please enter email ID");
                    emailSignUp.requestFocus();
                }
                else if(password.isEmpty()){
                    passwordSignUp.setError("Please enter your password");
                    passwordSignUp.requestFocus();
                }
                else if(confirmPassword.isEmpty()){
                    confirmPasswordSignUp.setError("Please confirm your password");
                    confirmPasswordSignUp.requestFocus();


                }
                else if(!confirmPassword.equals(password)){
                    Toast.makeText(SignUpActivity.this,"Password do not match",Toast.LENGTH_SHORT).show();

                }
                else if(!(email.isEmpty() && password.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                FirebaseAuthException e = (FirebaseAuthException )task.getException();
                                Toast.makeText(SignUpActivity.this,"Signup Failed!",Toast.LENGTH_SHORT).show();
                                Log.e("LoginActivity", "Failed Registration", e);


                            }
                            else {



                                startActivity(new Intent(SignUpActivity.this,HomeActivity.class));


                            }

                        }
                    });

                }
                else{
                    Toast.makeText(SignUpActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }


            }
        });

        loginNeeded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

    }
}
