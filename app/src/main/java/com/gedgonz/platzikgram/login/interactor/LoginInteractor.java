package com.gedgonz.platzikgram.login.interactor;

import com.gedgonz.platzikgram.login.presenter.ILoginPresenter;
import com.gedgonz.platzikgram.login.repository.ILoginRepository;
import com.gedgonz.platzikgram.login.repository.LoginRepository;

public class LoginInteractor implements ILoginInteractor {


    private ILoginPresenter loginPresenter;
    private ILoginRepository loginRepository;

    public LoginInteractor(ILoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
        loginRepository = new LoginRepository(loginPresenter);
    }

    @Override
    public void sigIn(String username, String password) {
        loginRepository.SignIn(username,password);
    }
}
