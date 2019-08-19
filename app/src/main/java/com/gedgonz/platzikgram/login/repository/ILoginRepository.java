package com.gedgonz.platzikgram.login.repository;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

public interface ILoginRepository {

    void SignIn(String username, String password, Activity activity,  FirebaseAuth firebaseAuth);
}
