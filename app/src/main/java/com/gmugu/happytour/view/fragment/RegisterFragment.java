package com.gmugu.happytour.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gmugu.happytour.R;
import com.gmugu.happytour.presenter.IRegisterPresenter;
import com.gmugu.happytour.view.IRegisterView;


public class RegisterFragment extends BaseFragment implements View.OnClickListener, IRegisterView {

    private OnRegisterFragmentInteractionListener mListener;

    private IRegisterPresenter registerPresenter;
    private View mView;
    private EditText emailEt;
    private EditText tokenEt;
    private Button getTokenBn;
    private Button backBn;
    private Button nextBn;
    private EditText passwdEt;
    private EditText passwdRepaetEt;
    private ProgressDialog progressDialog;


    public RegisterFragment() {
    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_register, container, false);
        initView();
        return mView;
    }

    private void initView() {
        emailEt = (EditText) findViewById(R.id.fragment_register_email_et);
        tokenEt = (EditText) findViewById(R.id.fragment_register_token_et);
        getTokenBn = (Button) findViewById(R.id.fragment_register_get_token_bn);
        getTokenBn.setOnClickListener(this);
        backBn = (Button) findViewById(R.id.fragment_register_back_bn);
        backBn.setOnClickListener(this);
        nextBn = (Button) findViewById(R.id.fragment_register_next_bn);
        nextBn.setOnClickListener(this);
        passwdEt = (EditText) findViewById(R.id.fragment_register_passwd_et);
        passwdRepaetEt = (EditText) findViewById(R.id.fragment_register_passwd_repeat_et);

    }

    private View findViewById(int id) {
        return mView.findViewById(id);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnRegisterFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnRegisterFragmentInteractionListener");
        }
        registerPresenter = mListener.getRegisterPresenter();
        if (registerPresenter == null) {
            throw new NullPointerException("registerPresenter must be not null");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_register_get_token_bn:
                registerPresenter.onGetTokenBnPressed();
                break;
            case R.id.fragment_register_back_bn:
                registerPresenter.onBackBnPressed();
                break;
            case R.id.fragment_register_next_bn:
                registerPresenter.onNextBnPressed();
                break;
            default:
                break;
        }
    }

    @Override
    public void back() {
        getActivity().onBackPressed();
    }

    @Override
    public String getEMail() {
        return emailEt.getText().toString();
    }

    @Override
    public String getToken() {
        return tokenEt.getText().toString();
    }

    @Override
    public String getPasswd() {
        return passwdEt.getText().toString();
    }

    @Override
    public String getPasswdRepate() {
        return passwdRepaetEt.getText().toString();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cancelWaitingTip() {
        progressDialog.cancel();
    }

    @Override
    public void showWaitingTip() {
        progressDialog = new ProgressDialog(getActivity(), ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在发送请求");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                registerPresenter.onRequestCancal();
            }
        });
        progressDialog.show();
    }

    @Override
    public void showErrorMsgDialog(String msg) {
        new AlertDialog.Builder(getActivity()).setTitle("警告").setMessage(msg).show();
    }

    @Override
    public void showMsgDialog(String msg) {
        new AlertDialog.Builder(getActivity()).setTitle("提示").setMessage(msg).show();
    }

    @Override
    public void toModifyUserInfo() {
        mListener.toModifyUserInfo();
    }


    public interface OnRegisterFragmentInteractionListener {

        IRegisterPresenter getRegisterPresenter();

        void toModifyUserInfo();
    }

}
