package com.example.restservice.controller;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double a = 122.4325235763;
		Test.seperator(a);
	}

	public static void seperator(double a) {

		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
		otherSymbols.setDecimalSeparator(',');
		otherSymbols.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat();
		// df.parse(1,2);
	}

}
