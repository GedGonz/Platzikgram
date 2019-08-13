package com.gedgonz.platzikgram.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gedgonz.platzikgram.R;
import com.gedgonz.platzikgram.login.presenter.LoginPresenter;
import com.gedgonz.platzikgram.view.ContainerActivity;
import com.gedgonz.platzikgram.view.CreateAccountActivity;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity implements ILoginView {


    private TextInputEditText username, password;
    private Button login;
    private ProgressBar progresbarLogin;
    private LoginPresenter loginPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username    = (TextInputEditText) findViewById(R.id.username);
        password    = (TextInputEditText) findViewById(R.id.pasword);
        login       = (Button) findViewById(R.id.login);
        progresbarLogin = (ProgressBar) findViewById(R.id.progresbarLogin);
        loginPresenter = new LoginPresenter(this);
        hidePoressBar();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!username.getText().toString().equals("") && !password.getText().toString().equals("") )
                {
                    loginPresenter.SignIn(username.getText().toString(), password.getText().toString());
                }
                else
                {
                    loginError("Digite todos los Campos!");
                }


            }
        });
    }


    @Override
    public void enableInputs() {
        username.setEnabled(true);
        password.setEnabled(true);
        login.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        username.setEnabled(false);
        password.setEnabled(false);
        login.setEnabled(false);
    }

    @Override
    public void showPressBar() {
        progresbarLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePoressBar() {
        progresbarLogin.setVisibility(View.GONE);
    }

    @Override
    public void loginError(String error) {
        Toast.makeText(this,getString(R.string.login_error)+ error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goCreateAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void goHome() {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

}








