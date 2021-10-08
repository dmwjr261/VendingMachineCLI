package com.techelevator;

import java.util.*;

// Vending Machine Command Line Interface application
public class VendingMachineCLI {
	private static VendingMachine vendingMachine;

	public static void main(String[] args) {

		//read products from file

		vendingMachine = new VendingMachine(IOHelper.getProductMapFromFile()); // vendingMachine.getProductMap().get("A1").getName();

		String mainMenuSelection;
		String purchaseMenuSelection;

		IOHelper.displayIntroMessage();

		mainMenuSelection = mainMenuValidation();

		if (mainMenuSelection.equals("1")) {
			//display product menu
			IOHelper.displayProductMap(vendingMachine.getProductMap());
		}

		if (mainMenuSelection.equals("2")) {
			purchaseMenuSelection = purchaseMenuValidation();
		}

		if (mainMenuSelection.equals("3")) {
			IOHelper.displayExitMessage();
		}
}

	public static String mainMenuValidation() {
		Scanner scanner = new Scanner(System.in);
		String inputNumber = IOHelper.captureMainMenuSelection(scanner); //capturing measurement input from user using a function call

		while (!isValidInput(inputNumber)) {
			inputNumber = IOHelper.captureMainMenuSelection(scanner); //verifying that the measurement input is actually a number
		}
		return inputNumber;
	}

	public static String purchaseMenuValidation() {
		Scanner scanner = new Scanner(System.in);
		String inputNumber = IOHelper.capturePurchaseMenuSelection(scanner); //capturing measurement input from user using a function call

		while (!isValidInput(inputNumber)) {
			inputNumber = IOHelper.capturePurchaseMenuSelection(scanner); //verifying that the measurement input is actually a number
		}
		return inputNumber;
	}

	public static boolean isValidInput(String toValidate) {
		List<String> validation = Arrays.asList("1", "2", "3", "4");
		return validation.contains(toValidate);
	}

}
