package com.gedgonz.platzikgram.login.presenter;

public interface ILoginPresenter {

    void SignIn(String username, String password); //Interactor
    void loginSuccess();
    void loginError(String error);

}
