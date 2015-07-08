package com.netwokz.acfluidconversion;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    TextView mGramsView, mOuncesView, mPoundsView;
    EditText mInitAmountView;
    Button mConvertBtn;

    RadioGroup mType;

    boolean mGrams = false;
    boolean mOunces = true;
    boolean mPounds = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private void checkFieldsForEmptyValues(){
        String s1 = mInitAmountView.getText().toString();
        if(s1.equals("")) {
            mGramsView.setText("");
            mOuncesView.setText("");
            mPoundsView.setText("");
        } else {
            updateViews();
        }
    }

    private void initViews() {
        mGramsView = (TextView) findViewById(R.id.tvGrams);
        mOuncesView = (TextView) findViewById(R.id.tvOunces);
        mPoundsView = (TextView) findViewById(R.id.tvPounds);

        mInitAmountView = (EditText) findViewById(R.id.tvInitAmount);
        mInitAmountView.addTextChangedListener(watcher);

        mConvertBtn = (Button) findViewById(R.id.btnConvert);
        mConvertBtn.setOnClickListener(this);

        mType = (RadioGroup) findViewById(R.id.rgMeasurements);
        mType.check(R.id.rbOunces);
    }

    public float convertOtoP(float ounces){
        float pounds = ounces / 16.0f;
        return pounds;
    }

    public float convertPtoO(float pounds){
        float ounces = pounds * 16.0f;
        return ounces;
    }

    public float convertOtoG(float ounces){
        float grams = ounces * 28.34952f;
        return grams;
    }

    public float convertGtoO(float grams){
        float ounces = grams / 28.34952f;
        return ounces;
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateViews() {
        if (!mInitAmountView.getText().toString().equals("")) {
            if (mGrams) {
                String temp1 = String.format("%.2f", convertGtoO(Float.valueOf(mInitAmountView.getText().toString())));
                String temp2 = String.format("%.2f", convertOtoP(Float.valueOf(temp1)));
                mGramsView.setText(mInitAmountView.getText().toString());
                mOuncesView.setText(temp1);
                mPoundsView.setText(temp2);
            } else if (mOunces) {
                String temp1 = String.format("%.2f", convertOtoP(Float.valueOf(mInitAmountView.getText().toString())));
                String temp2 = String.format("%.2f", convertOtoG(Float.valueOf(mInitAmountView.getText().toString())));
                mGramsView.setText(temp2);
                mOuncesView.setText(mInitAmountView.getText().toString());
                mPoundsView.setText(temp1);
            } else if (mPounds) {
                String temp1 = String.format("%.2f", convertPtoO(Float.valueOf(mInitAmountView.getText().toString())));
                String temp2 = String.format("%.2f", convertOtoG(Float.valueOf(temp1)));
                mGramsView.setText(temp2);
                mOuncesView.setText(temp1);
                mPoundsView.setText(mInitAmountView.getText().toString());
            }
        }
    }

    @Override
    public void onClick(View v) {
        updateViews();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbGrams:
                if (checked)
                    mGrams = true;
                    mOunces = false;
                    mPounds = false;
                    updateViews();
                    break;
            case R.id.rbOunces:
                if (checked)
                    mGrams = false;
                    mOunces = true;
                    mPounds = false;
                    updateViews();
                    break;
            case R.id.rbPounds:
                if (checked)
                    mGrams = false;
                    mOunces = false;
                    mPounds = true;
                    updateViews();
                    break;
        }
    }
}
