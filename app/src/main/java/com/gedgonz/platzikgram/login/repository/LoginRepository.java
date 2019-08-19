package com.gedgonz.platzikgram.login.repository;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.gedgonz.platzikgram.login.presenter.ILoginPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginRepository implements ILoginRepository {

    private ILoginPresenter loginPresenter;
    private static final String TAG ="LoginRepository" ;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    public LoginRepository(ILoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void SignIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth) {

        firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    loginPresenter.loginSuccess();
                }
                else
                    {
                        loginPresenter.loginError("Ocurrio un Error");
                    }
            }
        });
/*
        boolean success = true;
        if(success)
        {
            loginPresenter.loginSuccess();
        }
        else
        {
            loginPresenter.loginError("Ocurrio un Error");
        }
        */
    }
}
