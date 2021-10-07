package com.techelevator;

import javax.swing.*;
import java.util.Scanner;

// Vending Machine Command Line Interface application
public class VendingMachineCLI {

	public static void main(String[] args) {
		// Make some objects here!

		int mainMenuSelection = 0;

		System.out.println("Welcome to the Vendo-Matic 800");
		System.out.println("Vendo-Matic 800 is an Umbrella Corp Product");

		mainMenuSelection = mainMenuInterface();

		if (mainMenuSelection == 3) {
			System.out.println("Thank you for using the Vendo-Matic 800");
			System.out.println("Have a fantastic day!");
		}

		// if 1 go to display
		// if 2 go to purchase
		// if 3 go to exit

	}

	public static int mainMenuInterface() {
		Scanner input = new Scanner(System.in);
		int menuSelection = 0;

		//need input verification

		System.out.println("Please select from the following menu options: ");
		System.out.println("1) Display Vending Machine Items");
		System.out.println("2) Purchase" );
		System.out.println("3) Exit");
		System.out.println("Selection: ");

		menuSelection = input.nextInt();

		return menuSelection;

	}
}
