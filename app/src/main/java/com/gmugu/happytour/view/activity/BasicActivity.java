package com.gmugu.happytour.view.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.gmugu.happytour.application.AppComponent;
import com.gmugu.happytour.application.MyApplication;
import com.gmugu.happytour.comment.assist.Log;
import com.gmugu.happytour.view.IMessageView;

/**
 * Created by mugu on 16-4-2 下午5:52.
 */
public abstract class BasicActivity extends Activity {

    protected MyApplication getMyApplication() {
        return (MyApplication) getApplication();
    }

    protected AppComponent getAppComponent() {
        return getMyApplication().getAppComponent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(this, "onCreate");
        setupActivityComponent(getAppComponent());
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);


    public BasicActivity() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(this, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(this, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(this, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(this, "onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(this, "onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(this, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(this, "onDestroy");
    }



}
