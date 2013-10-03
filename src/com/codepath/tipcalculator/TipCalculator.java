package com.codepath.tipcalculator;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculator extends Activity {
	
	private EditText billAmountText;
	private TextView tipAmountText;
	private TextView totalAmountText;
	private String billAmountString;
	private double billAmount;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calculator);
		
		billAmountText = (EditText) findViewById(R.id.etBillAmount);
		tipAmountText = (TextView) findViewById(R.id.tvTipValue);
		totalAmountText = (TextView) findViewById(R.id.tvTotalValue);
	}

	public void calculateTenPercentTip(View view) {
		double tip = calculateTip(0.1);
		double total = calculateTotal(tip);
		setTipAndTotal(tip, total);
	}

	public void calculateFifteenPercentTip(View view) {
		double tip = calculateTip(0.15);
		double total = calculateTotal(tip);
		setTipAndTotal(tip, total);
	}

	public void calculateTwentyPercentTip(View view) {
		double tip = calculateTip(0.2);
		double total = calculateTotal(tip);
		setTipAndTotal(tip, total);
	}

	private double calculateTip(double tipPercentage) {
		billAmountString = billAmountText.getText().toString();
		if (billAmountString.isEmpty()) {
			return 0.0;
		} else {
			billAmount = Double.parseDouble(billAmountString);	
			return tipPercentage*billAmount;		
		}
	}

	private double calculateTotal(double tip) {
		return tip + billAmount;
	}
	
	private void setTipAndTotal(double tip, double total) {
		DecimalFormat df = new DecimalFormat("#.##");
		tipAmountText.setText(df.format(tip));
		totalAmountText.setText(df.format(total));		
	}


}
