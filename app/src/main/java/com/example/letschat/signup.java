package com.example.letschat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.letschat.databinding.ActivitySignupBinding;
import com.example.letschat.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    ActivitySignupBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        getSupportActionBar().hide();
        // Initialize Firebase Auth
        pd=new ProgressDialog(signup.this);
        pd.setTitle("Creating Account");
        pd.setMessage("Please Wait...");
        mAuth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                mAuth.createUserWithEmailAndPassword
                        (binding.signupEmail.getText().toString(),binding.signupPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            pd.dismiss();
                            Users user=new Users(binding.signupEmail.getText().toString(),binding.signupPassword.getText().toString(),binding.userName.getText().toString());
                            String id=task.getResult().getUser().getUid();
                            database.getReference().child("users").child(id).setValue(user);
                            startActivity(new Intent(signup.this,signin.class));
                            Toast.makeText(signup.this, "Account Created Successfully...", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        binding.alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this,signin.class));
            }
        });
    }
}