package com.gedgonz.platzikgram.login.interactor;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

public interface ILoginInteractor {

    void sigIn(String username, String password, Activity activity,  FirebaseAuth firebaseAuth);
}
