package com.gmugu.happytour.view.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gmugu.happytour.R;
import com.gmugu.happytour.application.AppComponent;
import com.gmugu.happytour.presenter.ILoginPresenter;
import com.gmugu.happytour.view.ILoginView;
import com.gmugu.happytour.view.activity.component.DaggerLoginComponent;
import com.gmugu.happytour.view.activity.module.LoginModule;

import javax.inject.Inject;

import static com.gmugu.happytour.comment.constant.ActivityActionName.*;
import static com.gmugu.happytour.comment.constant.ActivityActionName.MAIN_ACTIVITY_ACTION;
import static com.gmugu.happytour.comment.constant.ActivityActionName.REGISTER_ACTIVITY_ACTION;
import static com.gmugu.happytour.comment.constant.ActivityActionName.RETRIEVE_PASSWORD_ACTIVITY_ACTION;

/**
 * Created by mugu on 16-4-9 下午12:47.
 */
public class LoginActivity extends BasicActivity implements ILoginView, View.OnClickListener {

    private Button loginBn;
    private Button signupBn;
    private Button findPasswdBn;
    private EditText usernameEt;
    private EditText passwordEt;
    private ProgressDialog progressDialog;

    @Inject
    protected ILoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder().loginModule(new LoginModule(this)).appComponent(appComponent).build().inject(this);
    }

    private void initViews() {
        loginBn = (Button) findViewById(R.id.login_login_bn);
        loginBn.setOnClickListener(this);
        signupBn = (Button) findViewById(R.id.login_signup_bn);
        signupBn.setOnClickListener(this);
        findPasswdBn = (Button) findViewById(R.id.login_find_passwd_bn);
        findPasswdBn.setOnClickListener(this);
        usernameEt = (EditText) findViewById(R.id.login_username_et);
        passwordEt = (EditText) findViewById(R.id.login_password_et);


    }

    @Override
    public String getUserName() {
        return usernameEt.getText().toString();
    }

    @Override
    public String getPasswd() {
        return passwordEt.getText().toString();
    }


    @Override
    public void close() {
        finish();
    }

    @Override
    public void toMainView() {
        Intent intent = new Intent(MAIN_ACTIVITY_ACTION);
        startActivity(intent);
    }

    @Override
    public void toRegister() {
        Intent intent = new Intent(REGISTER_ACTIVITY_ACTION);
        startActivity(intent);
    }

    @Override
    public void toRetrievePassword() {
        Intent intent = new Intent(RETRIEVE_PASSWORD_ACTIVITY_ACTION);
        startActivity(intent);
    }

    @Override
    public void showToase(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMsgDialog(String message) {
        new AlertDialog.Builder(this).setTitle("警告").setMessage(message).show();
    }

    @Override
    public void showLoginingTip() {
        progressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在登录");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                loginPresenter.onLoginCancel();
            }
        });
        progressDialog.show();
    }

    @Override
    public void cancelLoginingTip() {
        progressDialog.cancel();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_login_bn:
                loginPresenter.onLoginBnPressed();
                break;
            case R.id.login_signup_bn:
                loginPresenter.onRegisterBnPressed();
                break;
            case R.id.login_find_passwd_bn:
                loginPresenter.onRetrievePasswordBnPressed();
            default:
                break;
        }
    }
}
