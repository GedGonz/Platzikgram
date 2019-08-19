package com.gedgonz.platzikgram.login.presenter;

import android.app.Activity;

import com.gedgonz.platzikgram.login.interactor.ILoginInteractor;
import com.gedgonz.platzikgram.login.interactor.LoginInteractor;
import com.gedgonz.platzikgram.login.view.ILoginView;
import com.gedgonz.platzikgram.login.view.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements ILoginPresenter{



    private ILoginView loginView;
    private ILoginInteractor loginInteractor;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        loginInteractor = new LoginInteractor(this);
    }

    @Override
    public void SignIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth) {
        loginView.disableInputs();
        loginView.showPressBar();

        loginInteractor.sigIn(username, password, activity, firebaseAuth);
    }

    @Override
    public void loginSuccess() {
        loginView.goHome();

        loginView.hidePoressBar();
    }

    @Override
    public void loginError(String error) {
        loginView.enableInputs();
        loginView.hidePoressBar();
        loginView.loginError(error);
    }
}
