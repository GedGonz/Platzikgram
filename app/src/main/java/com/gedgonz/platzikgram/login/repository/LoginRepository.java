package com.gedgonz.platzikgram.login.repository;

import com.gedgonz.platzikgram.login.presenter.ILoginPresenter;

public class LoginRepository implements ILoginRepository {

    private ILoginPresenter loginPresenter;

    public LoginRepository(ILoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void SignIn(String username, String password) {

        boolean success = true;
        if(success)
        {
            loginPresenter.loginSuccess();
        }
        else
        {
            loginPresenter.loginError("Ocurrio un Error");
        }
    }
}
