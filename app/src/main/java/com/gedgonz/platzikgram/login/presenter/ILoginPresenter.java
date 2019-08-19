package com.gedgonz.platzikgram.login.presenter;

import android.app.Activity;

import com.gedgonz.platzikgram.login.view.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public interface ILoginPresenter {

    void SignIn(String username, String password, Activity activity,  FirebaseAuth firebaseAuth); //Interactor
    void loginSuccess();
    void loginError(String error);

}
