package labbonus20;
//@ Sasikaladevi Kumarasamy


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// this program checks for validity of many types of data, each in a different method. 
// It even checks if the user input data is within range mentioned or not(where applicable)
import java.util.Scanner;

public class Validator {
	Scanner sc = new Scanner(System.in);
	// overloaded method with different set of parameters. 
	//This checks if the user has entered an integer or not. Returns the valid number.
	public static int getInteger(Scanner sc, String prompt) {
		//int number;
/*		try {
			System.out.print(prompt);
			return sc.nextInt();
		} catch (InputMismatchException ex) {
			System.out.println("Invalid number. Please enter a valid integer!");
			sc.nextLine();
			return getInteger(sc,prompt);
		}*/
		boolean isValid = false;
		do {
			System.out.print(prompt);
			isValid = sc.hasNextInt();
			if(!isValid) {
				System.out.println("Invaild entry. Please try again!");
			}
		} while(!isValid);
		return sc.nextInt();
		
	}
	// overloaded method with different set of parameters. 
	//This checks if the user has entered an integer within the range mentioned.

	public static int getInteger(Scanner sc, String prompt,int min,int max) {
		boolean isValid = false;
		int number = 0;
		while(!isValid) {
			number = getInteger(sc,prompt);
			if(number<min) {
				System.out.println("The number has to be greater than "+min);
				
			}
			else if(number >max) {
				System.out.println("The number has to be less than "+max);
			}
			else {
				isValid = true;
			}
				
		} 
		return number;
	
	}
	
	// overloaded method with different set of parameters. 
	//This checks if the user has entered a double or not and returns a valid number.

	public static double getDouble(Scanner sc, String prompt) {
/*		try {
			System.out.print(prompt);
			return sc.nextDouble();
		} catch (InputMismatchException ex) {
			System.out.println("Invaild entry. Please try again!");
			sc.nextLine();
			return getDouble(sc, prompt);
		}
		*/
		
		boolean isValid = false;
	
		do {
			System.out.print(prompt);
			isValid = sc.hasNextDouble();
			if(!isValid) {
				System.out.println("Invaild entry. Please try again!");
			}
		} while(!isValid);
		return sc.nextDouble();
	}
// overloaded method with different set of parameters. 
//This checks if the user has entered a double or not and also if it is within the range mentioned.
	public static double getDouble(Scanner sc, String prompt, double min, double max) {
		double number=0;
		boolean isValid = false;
		while(!isValid) {
			number = getDouble(sc,prompt);
			if(number<min) {
				System.out.println("The number has to be greater than "+min);
			}
			else if(number >max) {
				System.out.println("The number has to be less than "+max);
			}
			else {
				isValid = true;
			}
		}
			return number;	
	}
	
	// prints the prompt and returns the user entered string
	public static String getString(Scanner sc, String prompt) {
		System.out.print(prompt);
		return sc.nextLine();
	}
	
	// checks the user input string matches the RegEx pattern or not. 
	// It keeps asking until valid entry is made.
	public static String getRegExString(Scanner sc,String prompt,String regExPattern ) {
		boolean isValid = false;
		String userInput="";
		while(!isValid) {
//			System.out.print(prompt);
			userInput = getString(sc, prompt);
			if (!(userInput.matches(regExPattern))) 
				System.out.println("Invalid entry! Please try again.");
			else
				isValid = true;
			
		}	
		return userInput;
	}
	
	// checks for the valid inputs for continue or not. 
	// If the user input string matches the RegEx pattern received, it returns true; else returns false.
	public static boolean continueOrNot(Scanner sc, String prompt, String regExPattern) {
		boolean isValid = false;
		while(!isValid) {
			System.out.print(prompt);
			String userInput = sc.next();
			if(userInput.matches(regExPattern))
				return false;
			else
				return true;
		}
		return isValid;		
	}
	
	// checks for the input as HTML tags < > or </ > and returns the user input string 
	// only if it matches the RegEx pattern sent; else it throws erroe.
	public static String htmlTagValidation(Scanner sc, String prompt, String regExPattern) {
		boolean isValid = false;
		String userInput="";
		while(!isValid) {
			System.out.print(prompt);
			userInput = sc.next();
			if(!(userInput.matches(regExPattern)))
				System.out.println("Invalid entry. Please try again!");
			else
				isValid=true;
		}
		return userInput;		
	}
// accepts a string and converts it to Date type and returns it
	public static Date getDate(Scanner sc, String prompt) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		format.setLenient(false);
		boolean isValid = false;
		Date date=null;
		String userInput="";
		
		while(!isValid) {
			// get in the raw string
			userInput = getString(sc, prompt);
			//convert the raw string to date format
			try {
				date = format.parse(userInput);
				isValid = true;
			} catch (ParseException e) {
				//e.printStackTrace();
				isValid = false;
				System.out.println("Cannot convert "+userInput+" to date!");
			}
//			sc.nextLine();
		}	
		return date;
	}
		

}
