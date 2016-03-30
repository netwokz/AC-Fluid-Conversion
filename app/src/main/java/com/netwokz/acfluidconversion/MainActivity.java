package com.netwokz.acfluidconversion;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends Activity implements MyTextWatcher.OnTextChanged {

    EditText etViews[] = new EditText[4];
    private boolean DEBUG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    public void initViews() {
        log("initViews");
        etViews[0] = (EditText) findViewById(R.id.etGrams);
        etViews[1] = (EditText) findViewById(R.id.etKiloGrams);
        etViews[2] = (EditText) findViewById(R.id.etOunces);
        etViews[3] = (EditText) findViewById(R.id.etPounds);

        etViews[0].addTextChangedListener(mGramWatcher);
        etViews[1].addTextChangedListener(mKiloWatcher);
        etViews[2].addTextChangedListener(mOunceWatcher);
        etViews[3].addTextChangedListener(mPoundWatcher);
    }

    protected TextWatcher mGramWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (etViews[0].getText().toString().equals(".")){
                log("Period Detected and Replaced");
                etViews[0].setText("0.");
                updateViews(etViews[0].getId());
                etViews[0].setSelection(etViews[0].getText().length());
            }
            updateViews(etViews[0].getId());
            etViews[0].setSelection(etViews[0].getText().length());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    protected TextWatcher mKiloWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (etViews[1].getText().toString().equals(".")){
                log("Period Detected and Replaced");
                etViews[1].setText("0.");
                updateViews(etViews[1].getId());
                etViews[1].setSelection(etViews[1].getText().length());
            }
            updateViews(etViews[1].getId());
            etViews[1].setSelection(etViews[1].getText().length());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    protected TextWatcher mOunceWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (etViews[2].getText().toString().equals(".")){
                log("Period Detected and Replaced");
                etViews[2].setText("0.");
                updateViews(etViews[2].getId());
                etViews[2].setSelection(etViews[2].getText().length());
            }
            updateViews(etViews[2].getId());
            etViews[2].setSelection(etViews[2].getText().length());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    protected TextWatcher mPoundWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (etViews[3].getText().toString().equals(".")){
                log("Period Detected and Replaced");
                etViews[3].setText("0.");
                updateViews(etViews[3].getId());
                etViews[3].setSelection(etViews[3].getText().length());
            }
            updateViews(etViews[3].getId());
            etViews[3].setSelection(etViews[3].getText().length());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void log(String msg) {
        if (DEBUG) {
            Log.d("MainActivity", msg);
        }
    }

    public float convertOtoP(float ounces) {
        log("convertOtoP");
        float pounds = ounces / 16.0f;
        return pounds;
    }

    public float convertPtoO(float pounds) {
        log("convertPtoO");
        float ounces = pounds * 16.0f;
        return ounces;
    }

    public float convertOtoG(float ounces) {
        log("convertOtoG");
        float grams = ounces * 28.34952f;
        return grams;
    }

    public float convertGtoO(float grams) {
        log("convertGtoO");
        float ounces = grams / 28.34952f;
        return ounces;
    }

    public float convertGtoK(float grams) {
        log("convertGtoO");
        float kilo = grams / 1000f;
        return kilo;
    }

    public float convertKtoG(float kilo) {
        log("convertGtoO");
        float grams = kilo * 1000f;
        return grams;
    }

    private void clearAllFields() {
        for (int i = 0; i < etViews.length; i++) {
            etViews[i].setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
//            case R.id.action_settings:
//                return true;
            case R.id.action_clear:
                clearAllFields();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTextChanged(int id) {
        log("onTextChanged: " + " " + id);
        updateViews(id);
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public void updateViews(int id) {
        log("updateViews" + " " + id);
        etViews[0].removeTextChangedListener(mGramWatcher);
        etViews[1].removeTextChangedListener(mKiloWatcher);
        etViews[2].removeTextChangedListener(mOunceWatcher);
        etViews[3].removeTextChangedListener(mPoundWatcher);

        String mGramTemp = etViews[0].getText().toString().trim();
        String mKiloTemp = etViews[1].getText().toString().trim();
        String mOunceTemp = etViews[2].getText().toString().trim();
        String mPoundTemp = etViews[3].getText().toString().trim();

        switch (id) {
            case R.id.etGrams:
                if (!mGramTemp.equals("") && !mGramTemp.equals(".")){
                    log("etGrams" + " " + id);
                    log("Value" + " " + etViews[0].getText().toString());
                    String temp1 = String.format("%.2f", convertGtoO(Float.valueOf(etViews[0].getText().toString())));
                    String temp2 = String.format("%.2f", convertGtoK(Float.valueOf(etViews[0].getText().toString())));
                    String temp3 = String.format("%.2f", convertOtoP(Float.valueOf(temp1)));
                    etViews[0].setText(etViews[0].getText().toString());
                    etViews[1].setText(temp2);
                    etViews[2].setText(temp1);
                    etViews[3].setText(temp3);
                } else {
                    etViews[0].setText("");
                    etViews[1].setText("");
                    etViews[2].setText("");
                    etViews[3].setText("");
                }
                etViews[0].addTextChangedListener(mGramWatcher);
                etViews[1].addTextChangedListener(mKiloWatcher);
                etViews[2].addTextChangedListener(mOunceWatcher);
                etViews[3].addTextChangedListener(mPoundWatcher);

                break;
            case R.id.etKiloGrams:
                log("etKiloGrams");
                if (!mKiloTemp.equals("") && !mKiloTemp.equals(".")) {
                    String temp4 = String.format("%.2f", convertKtoG(Float.valueOf(etViews[1].getText().toString())));
                    String temp5 = String.format("%.2f", convertGtoO(Float.valueOf(temp4)));
                    String temp6 = String.format("%.2f", convertOtoP(Float.valueOf(temp5)));
                    etViews[0].setText(temp4);
                    etViews[1].setText(etViews[1].getText().toString());
                    etViews[2].setText(temp5);
                    etViews[3].setText(temp6);
                } else {
                    etViews[0].setText("");
                    etViews[1].setText("");
                    etViews[2].setText("");
                    etViews[3].setText("");
                }
                etViews[0].addTextChangedListener(mGramWatcher);
                etViews[1].addTextChangedListener(mKiloWatcher);
                etViews[2].addTextChangedListener(mOunceWatcher);
                etViews[3].addTextChangedListener(mPoundWatcher);
                break;
            case R.id.etOunces:
                log("etOunces");
                if (!mOunceTemp.equals("") && !mOunceTemp.equals(".")) {
                    String temp7 = String.format("%.2f", convertOtoP(Float.valueOf(etViews[2].getText().toString())));
                    String temp8 = String.format("%.2f", convertOtoG(Float.valueOf(etViews[2].getText().toString())));
                    String temp9 = String.format("%.2f", convertGtoK(Float.valueOf(temp8)));
                    etViews[0].setText(temp8);
                    etViews[1].setText(temp9);
                    etViews[2].setText(etViews[2].getText().toString());
                    etViews[3].setText(temp7);
                } else {
                    etViews[0].setText("");
                    etViews[1].setText("");
                    etViews[2].setText("");
                    etViews[3].setText("");
                }
                etViews[0].addTextChangedListener(mGramWatcher);
                etViews[1].addTextChangedListener(mKiloWatcher);
                etViews[2].addTextChangedListener(mOunceWatcher);
                etViews[3].addTextChangedListener(mPoundWatcher);
                break;
            case R.id.etPounds:
                log("etPounds");
                if (!mPoundTemp.equals("") && !mPoundTemp.equals(".")) {
                    String temp10 = String.format("%.2f", convertPtoO(Float.valueOf(etViews[3].getText().toString())));
                    String temp11 = String.format("%.2f", convertOtoG(Float.valueOf(temp10)));
                    String temp12 = String.format("%.2f", convertGtoK(Float.valueOf(temp11)));
                    etViews[0].setText(temp11);
                    etViews[1].setText(temp12);
                    etViews[2].setText(temp10);
                } else {
                    etViews[0].setText("");
                    etViews[1].setText("");
                    etViews[2].setText("");
                    etViews[3].setText("");
                }
                etViews[3].setText(etViews[3].getText().toString());
                etViews[0].addTextChangedListener(mGramWatcher);
                etViews[1].addTextChangedListener(mKiloWatcher);
                etViews[2].addTextChangedListener(mOunceWatcher);
                etViews[3].addTextChangedListener(mPoundWatcher);
                break;
        }
    }
}
