/*
 * This abstract class also contains another class BurgerInfo. BurgerInfo has the variables name, price and topping. In the BurgerWorldWide class
 * an ArrayList with instances of BurgerInfo is created. The program adds 3 different burgers with a different name, price and topping.
 * The class has two abstract methods which are modifed in the Menu subclass. This class has getters and setters related to the contents
 * of each BurgerInfo instance in the Burger ArrayList.
 */
package ProjBurgerWorldWide;
import java.util.ArrayList;   // import to use ArrayList

class BurgerInfo {  // BurgerInfo object
	String name;
	double price;
	String topping;
}
public abstract class BurgerWorldWide {
	private ArrayList<BurgerInfo> Burger = new ArrayList<>();  // Burger ArrayList containing BurgerInfo objects
	
	BurgerWorldWide() {                            // Initialization of each burger
		BurgerInfo b1 = new BurgerInfo();
		b1.name = "Inheritance Burger";
		b1.price = 3.0;
		b1.topping = "beef patty, tomato, onion, ranch source";
		Burger.add(b1);
		
		BurgerInfo b2 = new BurgerInfo();
		b2.name = "Override Burger";
		b2.price = 3.5;
		b2.topping = "beef patty, lime, onion, lettuce, tomato source";
		Burger.add(b2);
		
		BurgerInfo b3 = new BurgerInfo();
		b3.name = "Polymorphism Burger";
		b3.price = 2.0;
		b3.topping = "chicken breast, gallo, onion, ranch source";
		Burger.add(b3);
	}
	
	abstract void printMenu();        // abstract methods
	abstract void Promotion(Double discountRate, String promote);
	
	public String getName(int i) {  // returns name of i burger
		return Burger.get(i).name;
	}
	
	public int getHowManyBurgers() { // returns number of burgers in ArrayList Burger
		return Burger.size();
	}
	
	public double getPrice(int i) {  // returns price of i burger
		return Burger.get(i).price;
	}
	
	public String getTopping(int i) {  // returns topping of i burger
		return Burger.get(i).topping;
	}
	
	public void updateTopping(int burgerNumber, String newTopping) {  // updates topping of a burger using burgerNumber and String for new topping
		Burger.get(burgerNumber).topping = newTopping;
	}
}
