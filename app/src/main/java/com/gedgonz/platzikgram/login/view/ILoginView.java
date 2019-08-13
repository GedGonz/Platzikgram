package com.gedgonz.platzikgram.login.view;

public interface ILoginView {

    void enableInputs();
    void disableInputs();

    void showPressBar();
    void hidePoressBar();

    void loginError(String error);

    void goCreateAccount();
    void goHome();
}
