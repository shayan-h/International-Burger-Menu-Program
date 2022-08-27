/*
 * myFranchise contains the main method for this project. This class uses Scanner to gather input from the user used to create the burger
 * menu. Once the input from the user is gathered, using runtime polymorphism an instance of Menu is created with a parent class of 
 * BurgerWorldWide. The values are sent to Menu and the Menu program and it's actions occur. Then the main method asks for more input 
 * regarding burger toppings and the updateTopping method in BurgerWorldWide is called based on user input. Promotion is also called to
 * give the String promotion a value so it can be displaced through the Menu class.
 */
package ProjBurgerWorldWide;
import java.util.Scanner;  // import Scanner

public class myFranchise {

	public static void main(String[] args) {   // main method
		String country;
		String franchiseName;
		int discount;
		String promotion = "";
		int burgerNumber;
		String newTopping;
		
		Scanner input = new Scanner(System.in);  // create Scanner for user input
		
		System.out.println("\nBurgerWorldWide!");
		boolean run = true;
		while (run) {
			System.out.println("\nEnter country name. (Alpha-2 Code ONLY for ex: US, GB)");  // asks for country code which is used to call Menu
			country = input.next();   
			if (country.equals("0")) { // if user wishes to terminate program
				run = false;
				System.out.println("Bye!");
				break;
			} else {
				input.nextLine();
				System.out.println("Enter your franchise name.");  // asks for franchise name which is displayed on top of the menu
				franchiseName = input.nextLine();                // franchiseName used to call Menu which is BurgerWorldWide sublcass
				
				System.out.println("Enter discount rate % (0 ~ 99)");  // asks for discount rate which is used to call Promotion in Menu
				discount = input.nextInt();
				
				if (discount > 0) {
					input.nextLine();
					System.out.println("Enter Promotion");  // asks for Promotion which is used to call Promotion in Menu class
					promotion = input.nextLine();
				}
					
				BurgerWorldWide franchise = new Menu(country, franchiseName);  // creates Menu object which is a sublcass of BurgerWorldWide super class
					
				System.out.println("\nWhich burger would you like to change the topping? (enter " + franchise.getHowManyBurgers() + " if not) \n");
				for (int i = 0; i < franchise.getHowManyBurgers(); i++) {
					System.out.println(i + ". " + franchise.getName(i) + ": " + franchise.getTopping(i));  // asks for topping update
				}
					
				burgerNumber = input.nextInt();
				if (burgerNumber < franchise.getHowManyBurgers()) {
					input.nextLine();
					System.out.println("\nEnter new toppings of burger " + burgerNumber);  // Calls the updateTopping method in BurgerWorldWide 
					newTopping = input.nextLine();                                         // using which burger to update and newTopping String.
					franchise.updateTopping(burgerNumber, newTopping);
				}
					
				franchise.Promotion((double)discount/100.0, promotion);   // calls Promotion abstract method in Menu
					
				franchise.printMenu();                                    // finally calls printMenu in Menu which calls MenuGUI to display the Menu Jframe
				
			}
		}

	}

}
