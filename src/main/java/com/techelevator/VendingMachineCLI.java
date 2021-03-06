package com.techelevator;

import java.util.*;

// Vending Machine Command Line Interface application
public class VendingMachineCLI {
	public static VendingMachine vendingMachine;
	private static final String MAIN_MENU_SELECTION_DISPLAY_MENU = "1";
	private static final String MAIN_MENU_SELECTION_GO_TO_PURCHASE_MENU = "2";
	private static final String MAIN_MENU_SELECTION_EXIT_PROGRAM = "3";			//variables to make it easier to read
	private static final String MAIN_MENU_SELECTION_HIDDEN_SALES_REPORT = "4";
	private static final String PURCHASE_MENU_FEED_MONEY = "1";
	private static final String PURCHASE_MENU_DISPENSE_PRODUCT = "2";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "3";


	public static void main(String[] args) {

		//read products from file

		vendingMachine = new VendingMachine(IOHelper.getProductMapFromFile()); // vendingMachine.getProductMap().get("A1").getName();

		String mainMenuSelection;
		String purchaseMenuSelection;

		IOHelper.displayIntroMessage();

		mainMenuSelection = mainMenuValidation();

		while (!mainMenuSelection.equals(MAIN_MENU_SELECTION_EXIT_PROGRAM)) {


			if (mainMenuSelection.equals(MAIN_MENU_SELECTION_DISPLAY_MENU)) {
				// 1M display product menu
				IOHelper.displayProductMap(vendingMachine.getProductMap());
			}
				// 2M goes to purchase menu
			if (mainMenuSelection.equals(MAIN_MENU_SELECTION_GO_TO_PURCHASE_MENU)) {
				purchaseMenuSelection = purchaseMenuValidation();

				while (!purchaseMenuSelection.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
					// 1P feeds money
					if (purchaseMenuSelection.equals(PURCHASE_MENU_FEED_MONEY)) {
						vendingMachine.feedMoney();
					}

					// 2P dispenses product
					if (purchaseMenuSelection.equals(PURCHASE_MENU_DISPENSE_PRODUCT)) {
						vendingMachine.dispenseProduct();
					}

					purchaseMenuSelection = purchaseMenuValidation();
				}
			}
			if(mainMenuSelection.equals(MAIN_MENU_SELECTION_HIDDEN_SALES_REPORT)) {
				System.out.println("Sales Report");
				// bonus do last if time permits

			}

			mainMenuSelection = mainMenuValidation();
		}

		//dispense change
		IOHelper.output("You have " + IOHelper.formatMoney(vendingMachine.getTotalMoney()) + " left.");
		IOHelper.output("Your change is being dispensed");
		IOHelper.output(vendingMachine.calculateChange());
		IOHelper.displayExitMessage();

}

	public static String mainMenuValidation() {
		Scanner scanner = new Scanner(System.in);
		String inputNumber = IOHelper.captureMainMenuSelection(scanner); //capturing input from user

		while (!isValidInput(inputNumber)) {
			inputNumber = IOHelper.captureMainMenuSelection(scanner); //verifying that the input is a usable number
		}
		return inputNumber;
	}

	public static String purchaseMenuValidation() {
		Scanner scanner = new Scanner(System.in);
		String inputNumber = IOHelper.capturePurchaseMenuSelection(scanner, vendingMachine.getTotalMoney()); //capturing input from user

		while (!isValidInput(inputNumber)) {
			inputNumber = IOHelper.capturePurchaseMenuSelection(scanner, vendingMachine.getTotalMoney()); //verifying that the input is a usable number
		}
		return inputNumber;
	}

	public static boolean isValidInput(String toValidate) {
		List<String> validation = Arrays.asList("1", "2", "3", "4");
		return validation.contains(toValidate);
	}

}
