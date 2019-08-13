package com.gedgonz.platzikgram.login.presenter;

import com.gedgonz.platzikgram.login.interactor.ILoginInteractor;
import com.gedgonz.platzikgram.login.interactor.LoginInteractor;
import com.gedgonz.platzikgram.login.view.ILoginView;

public class LoginPresenter implements ILoginPresenter{

    private ILoginView loginView;
    private ILoginInteractor loginInteractor;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        loginInteractor = new LoginInteractor(this);
    }

    @Override
    public void SignIn(String username, String password) {
        loginView.disableInputs();
        loginView.showPressBar();

        loginInteractor.sigIn(username, password);
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
