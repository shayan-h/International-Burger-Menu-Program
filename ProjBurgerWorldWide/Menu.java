/*
 * Menu is a subclass of BurgerWorldWide. Menu contains abstract methods that it modifies to. It also contains an ExchangeRate object
 * which is used to obtain the exchange rate depending on which currency code is selected. Menu calls on MenuGUI to display burgers
 * and their name, topping, price (with discount or no discount). Also formats the price always with 2 digits after the decimal point.
 */
package ProjBurgerWorldWide;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class Menu extends BurgerWorldWide {

	String country;   // variables
	String franchise;
	Double discountRate;
	String promotion;
	String currencyCode;
	String currencySymbol;
	double exchangeRate;
	int updatedBurgerNum;
	String updatedTopping;
	
	static ExchangeRate convert;  // static ExchangeRate used to call getRate method from ExchangeRate class
	
	
	Menu(String country, String franchise) {  // Constructor accesses Currency class and uses country variable from user to do so
		
		this.franchise = franchise;
		//this.country = country;
		Locale locale = new Locale.Builder().setRegion(country).build();
		
		currencyCode = Currency.getInstance(locale).getCurrencyCode(); // converts the given country (Alpha 2 code) into it's associated currency code
		currencySymbol = Currency.getInstance(locale).getSymbol();
		
		try {
			convert = new ExchangeRate();
			exchangeRate = convert.getRate(currencyCode);  // call getRate from ExchangeRate class using currency code
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	void printMenu() {  // abstract method
		ArrayList<String> localPrice = new ArrayList<>();  // local price ArrayList for prices of each burger calculating discount
		
		DecimalFormat df = new DecimalFormat("#,###,###0.00");  // Decimal format used to unsure 2 decimal places after point
		
		for (int i = 0; i < getHowManyBurgers(); i++) {  // stops when number of burgers is passed
			String newPrice = df.format(getPrice(i) - (getPrice(i) * discountRate));  // discounted price = price - (price * discount rate)
			if (discountRate > 0.0) {                                                    // if there is a discount
				localPrice.add(newPrice + " (regular:" + df.format(getPrice(i)) + ")");  // then print regular price on the side
			} else {
				localPrice.add(newPrice + "");                                           // if not then just display normal price
			}
		}
		new MenuGUI(franchise,promotion,getName(0), currencySymbol + localPrice.get(0), getTopping(0),   // call MenuGUI to display Menu
				getName(1), currencySymbol + localPrice.get(1), getTopping(1),
				getName(2), currencySymbol + localPrice.get(2), getTopping(2));
	}
	
	@Override
	public double getPrice(int i) {                   // Override price getter from BurgerWorldWide
		return (super.getPrice(i) * exchangeRate);    // Taking into account exchangeRate applied to price
	}
	/*
	@Override
	public String getTopping(int i) {
		return updatedTopping;
	}
	*/

	@Override
	void Promotion(Double discountRate, String promote) {  // Abstract method
		this.discountRate = discountRate;
		this.promotion = promote;
	}
	
}