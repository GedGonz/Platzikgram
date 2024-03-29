package com.gedgonz.platzikgram.login.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gedgonz.platzikgram.R;
import com.gedgonz.platzikgram.login.presenter.LoginPresenter;
import com.gedgonz.platzikgram.view.ContainerActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements ILoginView {


    private TextInputEditText username, password;
    private Button login;
    private LoginButton loginButtonFacebook;
    private ProgressBar progresbarLogin;
    private LoginPresenter loginPresenter;

    private static final String TAG ="CreateAccountActivity" ;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        // Verifca el HASH que tiene la aplicacion
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.gedgonz.platzikgram",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }


        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        firebaseAuth =  FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser!=null)
                {
                    Log.w(TAG,"Usuario Logeado" +  firebaseUser.getEmail());
                    goHome();
                }
                else
                {
                    Log.w(TAG,"Usuario Logeado");
                }
            }
        };

        username    = (TextInputEditText) findViewById(R.id.username);
        password    = (TextInputEditText) findViewById(R.id.pasword);
        login       = (Button) findViewById(R.id.login);
        loginButtonFacebook = (LoginButton) findViewById(R.id.login_faceboobk);
        progresbarLogin = (ProgressBar) findViewById(R.id.progresbarLogin);
        loginPresenter = new LoginPresenter(this);

        hidePoressBar();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!username.getText().toString().equals("") && !password.getText().toString().equals("") )
                {
                    sigIn(username.getText().toString(), password.getText().toString());

                }
                else
                {
                    loginError("Digite todos los Campos!");
                }


            }
        });

        loginButtonFacebook.setReadPermissions(Arrays.asList("email"));
        loginButtonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.w(TAG,"Facebook Login Token " + loginResult.getAccessToken().getApplicationId());
                sigInFacebookFirebase(loginResult.getAccessToken());


            }

            @Override
            public void onCancel() {
                Log.w(TAG,"Facebook Login Cancelado");
            }

            @Override
            public void onError(FacebookException error) {
                Log.w(TAG,"Facebook Login Error " + error.toString());
                error.printStackTrace();
            }
        });
    }

    private void sigInFacebookFirebase(AccessToken accessToken) {
        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());

        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    goHome();
                    Toast.makeText(LoginActivity.this,"Login Facebook Exitoso", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(LoginActivity.this,"Login Facebook No Exitoso", Toast.LENGTH_LONG).show();

                }
            }
        });
    }


    private void sigIn(String username, String password) {
        loginPresenter.SignIn(username, password, this,firebaseAuth);
    }


    public void goCreateAccount(View view)
    {
        goCreateAccount();

    }

    @Override
    public void enableInputs() {
        username.setEnabled(true);
        password.setEnabled(true);
        login.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        username.setEnabled(false);
        password.setEnabled(false);
        login.setEnabled(false);
    }

    @Override
    public void showPressBar() {
        progresbarLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePoressBar() {
        progresbarLogin.setVisibility(View.GONE);
    }

    @Override
    public void loginError(String error) {
        Toast.makeText(this,getString(R.string.login_error)+ error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goCreateAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void goHome() {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);


    }

}








