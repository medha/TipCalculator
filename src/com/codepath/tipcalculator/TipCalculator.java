package com.codepath.tipcalculator;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class TipCalculator extends Activity {

	private EditText billAmountText;
	private double billAmount;
	private TextView tipAmountText;
	private TextView totalAmountText;
	private SeekBar seekBar;
	private TextView seekBarText;
	private String tipFinal;
	private String totalFinal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calculator_fancy);

		billAmountText = (EditText) findViewById(R.id.editText1);
		tipAmountText = (TextView) findViewById(R.id.textView1);
		totalAmountText = (TextView) findViewById(R.id.textView4);
		seekBarText = (TextView) findViewById(R.id.textView5);
		seekBar = (SeekBar) findViewById(R.id.seekBar1);

		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				seekBar.setProgress(progress);
				double tipPercentage = (double) progress / 100;
				calculateTipAndTotal(tipPercentage);

				seekBarText.setText("Tip %: " + String.valueOf(progress));
			}
		});

		billAmountText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (seekBar.getProgress() > 0) {
					double tipPercentage = (double) seekBar.getProgress() / 100;
					calculateTipAndTotal(tipPercentage);
				}
			}
		});

		if (savedInstanceState != null) {
			tipFinal = savedInstanceState.getString("TIP");
			totalFinal = savedInstanceState.getString("TOTAL");
		}

	}

	public void calculateTipAndTotal(double tipPercentage) {
		double tip = calculateTip(tipPercentage);
		double total = calculateTotal(tip);
		setTipAndTotal(tip, total);
	}

	private double calculateTip(double tipPercentage) {
		billAmount = getBillAmount();
		return tipPercentage * billAmount;
	}

	private double getBillAmount() {
		String billAmountString = billAmountText.getText().toString();
		if (billAmountString.isEmpty()) {
			billAmount = 0.0;
		} else {
			billAmount = Double.parseDouble(billAmountString);
		}
		return billAmount;
	}

	private double calculateTotal(double tip) {
		return tip + billAmount;
	}

	private void setTipAndTotal(double tip, double total) {
		DecimalFormat df = new DecimalFormat("#.##");
		tipFinal = df.format(tip);
		totalFinal = df.format(total);
		tipAmountText.setText(tipFinal);
		totalAmountText.setText(totalFinal);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("TIP", tipFinal);
		outState.putString("TOTAL", totalFinal);
	}

}
