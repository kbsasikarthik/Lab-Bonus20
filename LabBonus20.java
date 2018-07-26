/* @ Sasikaladevi Kumarasamy
 * 
 * 
 * This program is a smaller version of a shopping cart. 
 * You can list items, add items to shopping cart, view the cart, or quit.
 * Used HashMap to store items and price and array list to store the items added to cart and 
 * another array list to store the price of items, find average price of items in the cart, 
 * highest price and lowest price in cart.
 */

package labbonus20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LabBonus20 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String printItemFormat = "%-14s\t %.2f\n", userChoice;
		boolean isValid = false;
		int userOption;
		// this HashMap has a Key as String and value as Integer
		Map<String,Double> inventory = new HashMap<>();
		
		ArrayList<String> itemList= new ArrayList<>();
		ArrayList<Double> itemcost= new ArrayList<>();
		ArrayList<Integer> itemQuantity = new ArrayList<>();
		
		
		System.out.println("Welcome to the Fruit market!\n~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		// call the fillInventory method to add initial items to the empty Map
		inventory = fillInventory(inventory);
		
		while(!isValid) {
			menuOptions();
			try {
			userChoice = Validator.getString(sc, "What can I do for you? ");
			userOption = Integer.parseInt(userChoice);
			if(userOption==1) {
				printInventory(printItemFormat,inventory);	// prints the inventory
				continue;
			}else if(userOption==2) {
				addItem(sc,inventory, itemList, itemcost, itemQuantity, "What would you like to add to your cart?  ");
				sc.nextLine();
				continue;
			}else if(userOption==3) {
				displayShoppingCart(printItemFormat, itemList, itemcost);	// display shopping cart
				averagePriceOfItems(itemcost); //display the average price of items in the cart
				highCostItem(itemcost); // display the highest cost item in the cart
				lowCostItem(itemcost); // displays the lowest cost item in the cart
				continue;
			}else if(userOption ==4) {
				break;
			}
			}catch(Exception ex) { // catches the InpustMisMatch Exception
				System.out.println("Invalid input. Please try again.");
				continue;
			}
			
/*			if(userChoice.equals("list")) {
				printInventory(printItemFormat,inventory);	
			}else if(userChoice.equals("add")) {
				addItem(sc,inventory, itemList, itemcost, itemQuantity, "Enter an item from the menu: ");
			}else if(userChoice.equals("cart")) {
				displayShoppingCart(printItemFormat, itemList, itemcost);	
				averagePriceOfItems(itemcost);
				highCostItem(itemcost);
				lowCostItem(itemcost);
			}else if(userChoice.equals("quit")) {
				break;
			}*/
			isValid=Validator.continueOrNot(sc, "Would you like to see the menu again?(y/n) : ", "[yY]");
			sc.nextLine();
		}
		System.out.println("\nThanks for shopping with us! See you next time!");
	
		
	}
	
	
	// displays the menu options the user can choose from
	private static void menuOptions() {
		System.out.println("\nMenu Options\n~~~~ ~~~~~~~");
		System.out.println("1.List of items\n2.Add items to the cart\n3.Show the cart\n4.Quit\n");
	}


	// if the user wants to add an item to the cart, it validates if the item is available in the inventory or not and then adds it
	private static void addItem(Scanner sc, Map<String, Double> inventory,
				    				 ArrayList<String> itemList, ArrayList<Double> itemCost,ArrayList<Integer> itemQuantity, 
				    				 String prompt) {
		String userInput;
		boolean isValid=false;		
		int count=0, quantity = 0;
		
		while(!isValid) {
			System.out.print(prompt);
			userInput = sc.next().toLowerCase();
			//userInput = userInput.toLowerCase();
			if(inventory.containsKey(userInput)) {
				count++;
				itemList.add(userInput);
				itemCost.add(inventory.get(userInput));
				if(itemList.contains(userInput)) {
					quantity++;
				itemQuantity.add(quantity);
					
				}else {
					itemCost.add(inventory.get(userInput));	
				}
				System.out.println(userInput+" is added to the cart!");
			} else {
				System.out.println("Sorry! We dont carry "+userInput+" in our store!");
				addItem(sc, inventory, itemList, itemCost, itemQuantity,prompt);
			}
			
			isValid = Validator.continueOrNot(sc, "Would you like to add more items? (y/n) : ", "[yY]");// continue only if user agrees
	
		}
		System.out.println("You have added "+count+" items to the cart!"); // prints number of items in the cart
		//System.out.println("item) Quantity - "+itemQuantity);
	}

	// finds the low cost item from the cart and lists it
	private static void lowCostItem(ArrayList<Double> itemCost) {
		double lowCost=100;
		for(double cost:itemCost) {
			if(cost<lowCost) {
				lowCost = cost;
			}
		}
		System.out.printf("\nThe least expensive item in your cart costs $%.2f\n",lowCost);	
	}

	// finds the high cost item from the cart and lists it
	private static void highCostItem(ArrayList<Double> itemCost) {
		double highCost=0;
		//ArrayList<Double> sortedArray = itemCost.sort();
		for(double cost:itemCost) {
			if(cost>highCost) {
				highCost = cost;
			}
		}
		System.out.printf("\nThe most expensive item in your cart costs $%.2f",highCost);	
	}
	
	// calculates the average price of items in the cart and prints it
	private static void averagePriceOfItems(ArrayList<Double> itemCost) {
		double averageCost=0, totalCost=0;
		for(double cost: itemCost) {
			totalCost += cost;
		}
		System.out.printf("\nTotal Cost $%.2f",totalCost);
		averageCost = totalCost / itemCost.size();
		System.out.printf("\nThe average price of your cart is $%.2f",averageCost);
	}


	// method to display items and corresponding prices from the ArrayList that stores the information
	private static void displayShoppingCart(String printItemFormat, ArrayList<String> shoppingList, ArrayList<Double> itemCost) {
		System.out.println("\nItems in your cart");
		System.out.println("~~~~~~~~~~~~~~~~~~");
		for(int i=0;i<shoppingList.size();i++) {
			System.out.printf(printItemFormat, shoppingList.get(i), itemCost.get(i));
		}	
	}


	// method used to populate items to the HashMap with key-Value pair
	private static Map<String,Double> fillInventory(Map<String, Double> inventory) {
		
		inventory.put("apple", 2.99);
		inventory.put("banana", 0.55);
		inventory.put("cherries", 3.99);
		inventory.put("grapes", 2.49);
		inventory.put("strawberry", 4.99);
		inventory.put("blueberry", 3.99);
		inventory.put("mango", 0.99);
		inventory.put("grapefruit", 5.99);
		inventory.put("kiwi", 1.99);
		inventory.put("cucumber", 2.99);

		return inventory;
		
	}
	
	
	// prints the items in the inventory by pulling Key-Value pair from the HashMap
	private static void printInventory(String printItemFormat, Map<String,Double> inventory) {
		System.out.printf("%s\t\t%s\n", "Item","Price");
		System.out.printf("%s\t\t%s\n", "~~~~","~~~~~");
		
		for (String item: inventory.keySet()) {
			System.out.printf(printItemFormat,item, inventory.get(item));
		}
/*		for(Map.Entry<String, Double> entry : inventory.entrySet()) {
			System.out.printf("%-14s\t %.2f\n",entry.getKey(), entry.getValue());
		}*/

	}
}
