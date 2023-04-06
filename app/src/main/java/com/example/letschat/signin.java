package com.example.letschat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.letschat.databinding.ActivitySigninBinding;
import com.example.letschat.databinding.ActivitySignupBinding;
import com.example.letschat.models.Users;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

public class signin extends AppCompatActivity {
    ActivitySigninBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog pd;
    SignInClient oneTapClient;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    int RC_SIGN_IN=65;
    private static final String TAG = "GoogleActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        pd=new ProgressDialog(signin.this);
        pd.setTitle("Authenticating");
        pd.setMessage("Please Wait...");
        binding.signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                mAuth.signInWithEmailAndPassword
                        (binding.email.getText().toString(),binding.password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pd.dismiss();
                        if(task.isSuccessful()){
                            startActivity(new Intent(signin.this,MainActivity.class));
                        }
                        else{
                            Toast.makeText(signin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

//        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
//        gsc = GoogleSignIn.getClient(this,gso);
//        binding.google.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivityForResult(gsc.getSignInIntent(),RC_SIGN_IN);
//            }
//        });
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(signin.this,MainActivity.class));
        }
        binding.clickHereForSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signin.this,signup.class));
            }
        });

    }
    //fb auth

    // Initialize Facebook Login button
//    CallbackManager mCallbackManager = CallbackManager.Factory.create();
//
//binding.facebook.setReadPermissions("email", "public_profile");
//loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//        @Override
//        public void onSuccess(LoginResult loginResult) {
//            Log.d(TAG, "facebook:onSuccess:" + loginResult);
//            handleFacebookAccessToken(loginResult.getAccessToken());
//        }
//
//        @Override
//        public void onCancel() {
//            Log.d(TAG, "facebook:onCancel");
//        }
//
//        @Override
//        public void onError(FacebookException error) {
//            Log.d(TAG, "facebook:onError", error);
//        }
//    });
//    // ...
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Pass the activity result back to the Facebook SDK
//        mCallbackManager.onActivityResult(requestCode, resultCode, data);
//    }


//google auth
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==RC_SIGN_IN){
//            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                GoogleSignInAccount account=task.getResult(ApiException.class);
//                auth(account.getIdToken());
//            } catch (ApiException e) {
//                Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }

//        SignInCredential googleCredential = null;
//        try {
//            googleCredential = oneTapClient.getSignInCredentialFromIntent(data);
//        } catch (ApiException e) {
//            throw new RuntimeException(e);
//        }
//        String idToken = googleCredential.getGoogleIdToken();

//    }
//
//    private void auth(String idToken) {
//            // Got an ID token from Google. Use it to authenticate
//            // with Firebase.
//            AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
//            mAuth.signInWithCredential(firebaseCredential)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                // Sign in success, update UI with the signed-in user's information
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                Users users=new Users();
//                                users.setUser_id(user.getUid());
//                                users.setUser_name(user.getDisplayName());
//                                users.setMail(user.getEmail());
//                                users.setProfile(user.getPhotoUrl().toString());
//                                database.getReference().child("Users").child(user.getUid()).setValue(users);
//                                startActivity(new Intent(signin.this,MainActivity.class));
////                            updateUI(user);
//                            } else {
//                                // If sign in fails, display a message to the user.
//                                Log.w(TAG, "signInWithCredential:failure", task.getException());
////                            updateUI(null);
//                            }
//                        }
//                    });
//
//    }


}