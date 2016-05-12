package com.gmugu.happytour.view.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import com.gmugu.happytour.view.IMessageView;

/**
 * Created by mugu on 16-4-22 上午10:06.
 */
public abstract class BaseFragment extends Fragment implements IMessageView {

    private ProgressDialog progressDialog;

    @Override
    public void showErrorMsgDialog(String msg) {
        new AlertDialog.Builder(getActivity()).setTitle("警告").setMessage(msg).show();
    }

    @Override
    public void showToase(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWaitingTip() {
        progressDialog = new ProgressDialog(getActivity(), ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在请求");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                onWaitingTipCancel();
            }
        });
        progressDialog.show();
    }

    @Override
    public void cancelWaitingTip() {
        progressDialog.cancel();
    }

    @Override
    public void onWaitingTipCancel() {

    }

}
