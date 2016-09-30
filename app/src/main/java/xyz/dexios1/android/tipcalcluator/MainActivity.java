package xyz.dexios1.android.tipcalcluator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
//editText event vale and listener
// value formatting
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

//    vars to handle various widget
    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    double billAmount = 0.00;
    private double percent = 0.15;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getting reference to the various textViews
        amountTextView = (TextView)findViewById(R.id.amountTextView);
        percentTextView = (TextView)findViewById(R.id.percentTextView);
        tipTextView = (TextView)findViewById(R.id.tipTextView);
        totalTextView= (TextView)findViewById(R.id.totalTextView);
//        set the tip and total to zero
        tipTextView.setText(currencyFormat.format(0));
        totalTextView.setText(currencyFormat.format(0));
//        create and get references for the textField and Seekbar
        EditText amountEditText = (EditText) findViewById(R.id.amountEditText);
        SeekBar percentSeekbar = (SeekBar) findViewById(R.id.percentSeekBar);   //camel casing dey pap waa

//        setting up listeners
        amountEditText.addTextChangedListener(amountEditTextWatcher);
        percentSeekbar.setOnSeekBarChangeListener(seekBarChangeListener);


    }

//    method to compute restaurant bill and tip to be called in event listener
    private void calculateTip(){
//        show with the percent sign
        percentTextView.setText(percentFormat.format(percent));
        double tip = billAmount * percent;
        double total = billAmount + tip;
//        set the values of the text views
        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));
    }
//    charle go and read about anonymous inner classes ok? this is an example
    private final SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percent = progress /100.00;
            calculateTip();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
//                initialize the
//                convert to decimal value
                billAmount = Double.parseDouble(s.toString()) / 100.00;
//                set the amountTextView to the bill amount
                amountTextView.setText(currencyFormat.format(billAmount));

            }
            catch (NumberFormatException e){
                amountTextView.setText("");
                billAmount = 0.0;
            }
            calculateTip();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    }
