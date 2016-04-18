package com.gmugu.happytour.view.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gmugu.happytour.R;
import com.gmugu.happytour.application.AppComponent;
import com.gmugu.happytour.presenter.IRetrievePasswordPresenter;
import com.gmugu.happytour.view.IRetrievePasswordView;
import com.gmugu.happytour.view.activity.component.DaggerRetrievePasswordComponent;
import com.gmugu.happytour.view.activity.module.RetrievePasswordModule;

import javax.inject.Inject;

/**
 * Created by mugu on 16-4-9 下午2:02.
 */
public class RetrievePasswordActivity extends BasicActivity implements IRetrievePasswordView, View.OnClickListener {

    private EditText emailEt;
    private Button nextBn;
    private Button backBn;

    @Inject
    protected IRetrievePasswordPresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_password);
        initView();
    }

    private void initView() {
        emailEt = (EditText) findViewById(R.id.activity_retreve_password_email_et);
        nextBn = (Button) findViewById(R.id.activity_retreve_password_next_bn);
        nextBn.setOnClickListener(this);
        backBn = (Button) findViewById(R.id.activity_retreve_password_back_bn);
        backBn.setOnClickListener(this);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerRetrievePasswordComponent.builder().appComponent(appComponent).retrievePasswordModule(new RetrievePasswordModule(this)).build().inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_retreve_password_next_bn:
                presenter.onRetrievePasswdBnPressed();
                break;
            case R.id.activity_retreve_password_back_bn:
                presenter.onBackBnPressed();
                break;
            default:
                break;
        }
    }

    @Override
    public void back() {
        onBackPressed();
    }

    @Override
    public String getEMail() {
        return emailEt.getText().toString();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMsgDialog(String msg) {
        new AlertDialog.Builder(this).setTitle("警告").setMessage(msg).show();
    }

    @Override
    public void showwaitingTip() {
        progressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在发送请求");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                presenter.onRequestCancel();
            }
        });
        progressDialog.show();
    }

    @Override
    public void cancelWaitingTip() {
        progressDialog.cancel();
    }

    @Override
    public void showMsgDialog(String msg) {
        new AlertDialog.Builder(this).setTitle("提示").setMessage(msg).show();
    }
}
