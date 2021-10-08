package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class IOHelper {

    public static void output(String outputString) {
        System.out.println(outputString);
    }

    public static void displayIntroMessage() {
        output("*****Welcome to the Vendo-Matic 800*****");
        output("***Vendo-Matic 800 is an Umbrella Corp Product***");
        output("");
    }

    public static String captureMainMenuSelection(Scanner scanner) {
        output("*****Main Menu*****");
        output("Please select from the following menu options: ");
        output("1) Display Vending Machine Items");
        output("2) Purchase" );
        output("3) Exit");      //switch output to diff method
        output("Selection: ");
        String inputNumber = scanner.nextLine();
        return inputNumber;
    }

    public static String capturePurchaseMenuSelection(Scanner scanner) {
        output("*****Purchase Menu*****");
        output("Please select from the following menu options: ");
        output("1) Feed Money");
        output("2) Select Product" );
        output("3) Finish Transaction");
        output("Current Money Provided: " + "$"); //need to call the amount of money feed in
        output("Selection: ");
        String inputNumber = scanner.nextLine();
        return inputNumber;
    }

    public static void  displayExitMessage() {
        output("Thank you for using the Vendo-Matic 800");
        output("Have a fantastic day!");
    }

    public static HashMap<String, Product> getProductMapFromFile() {
        File vendingMachineProductList = new File("vendingmachine.csv");
        HashMap<String,Product> productMap = new HashMap<>();

        try (Scanner fileReader = new Scanner(vendingMachineProductList)) {
            while (fileReader.hasNextLine()) {
                String lineToSearch = fileReader.nextLine();
                String[] parts = lineToSearch.split("\\|");
                Product product = new Product(parts[0], parts[1], Double.valueOf(parts[2]), parts[3]);
                productMap.put(product.getLocation(), product);
            }

            return productMap;
        } catch(FileNotFoundException e){
            IOHelper.output("The file was not found!");
            return null;
        }

    }

    public static void displayProductMap(Map<String, Product> productHashMap) {
        for (Product product : productHashMap.values()) {
            System.out.println(product);
        }
    }
    //output sales report to file

}
