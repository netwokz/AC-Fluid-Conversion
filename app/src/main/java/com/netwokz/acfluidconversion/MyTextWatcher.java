package com.netwokz.acfluidconversion;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

/**
 * Created by netwokz on 7/9/2015.
 */
public class MyTextWatcher extends Activity implements TextWatcher {

    OnTextChanged mTextListener;

    public interface OnTextChanged {
        void onTextChanged(int id);
    }

    public int view;

    public MyTextWatcher(int id, OnTextChanged listener) {
        this.view = id;
        this.mTextListener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mTextListener.onTextChanged(view);
        Log.d(getClass().toString(), String.valueOf(view));
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
