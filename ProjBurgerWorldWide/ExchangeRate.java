/*
 * ExchangeRate class reads big data from exchangerate-api.com and then returns the correct rate depending on the country's currency.
 */
package ProjBurgerWorldWide;

import java.io.BufferedReader; 
import java.net.URL;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Locale;
import org.json.simple.JSONObject;  // import JSONObject, json-simple jar file in jar folder
import org.json.simple.parser.JSONParser;

public class ExchangeRate {
	
	static double rate = 1.0;
	
	private static String exURL = "https://v6.exchangerate-api.com/v6/a9426340b9767d00f852d966/latest/USD";  // link to real-time exchange rate
	
	public static double getRate(String currencyCode) throws Exception { // returns exchange rate from specified country
		
		try {
			URL url = new URL(exURL);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			
			JSONParser jsonParser = new JSONParser();
			JSONObject myObject = (JSONObject)jsonParser.parse(br);
			
			JSONObject conversionRates = (JSONObject)myObject.get("conversion_rates");  // reads key: conversion_rates from api
			//double country = (double) conversionRates.get(currencyCode);
			
			NumberFormat format = NumberFormat.getInstance(Locale.getDefault()); // create number formatter makes all objects convertible to desired type
			Number number = (Number) conversionRates.get(currencyCode);          // retrieves the value assigned to the key currencyCode
			//rate = (double) conversionRates.get(currencyCode);
			rate = number.doubleValue();    // number is returned as a double.
			return rate;                    // returns exchange rate
		} 
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return rate;
	}
}
