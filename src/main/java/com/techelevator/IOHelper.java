package com.techelevator;

import java.io.*;
import java.text.NumberFormat;
import java.util.*;

public class IOHelper {

    public static void output(String outputString) {
        System.out.println(outputString);
    }

    public static void outputNoBreak(String outputString) {
        System.out.print(outputString);
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

    public static String capturePurchaseMenuSelection(Scanner scanner, double totalMoney) {
        output("*****Purchase Menu*****");
        output("Please select from the following menu options: ");
        output("1) Feed Money");
        output("2) Select Product" );
        output("3) Finish Transaction");
        output("Current Money Provided: " + IOHelper.formatMoney(totalMoney));
        output("Selection: ");
        String inputNumber = scanner.nextLine();
        return inputNumber;
    }

    public static String captureProductSelection(Scanner scanner) {
        String inputNumber = scanner.nextLine();
        return inputNumber;
    }

    public static String captureYesOrNo(Scanner scanner) {
        String inputYOrN = scanner.nextLine();

        while (!isValidYOrN(inputYOrN)) {
            IOHelper.output(inputYOrN + " is not a y or n.  Reenter y or n");
            inputYOrN = IOHelper.captureYesOrNo(scanner); //verifying that the measurement input is actually a y or n
        }
        return inputYOrN;
    }

    public static double insertMoney (Scanner scanner) {
        String inputMoney = scanner.nextLine();
        double moneyInserted = Double.parseDouble(inputMoney);
        return moneyInserted;
    }

    public static void displayProductPurchaseMessage(String location) {
        String type = VendingMachineCLI.vendingMachine.getProductMap().get(location).getType();
        if (type.equals("Candy")) {
            output("Munch Munch, Yum!");
        }

        if (type.equals("Chip")) {
            output("Crunch Crunch, Yum!");
        }

        if (type.equals("Drink")) {
            output("Glug Glug, Yum!");
        }

        if (type.equals("Gum")) {
            output("Chew Chew, Yum!");
        }

    }

    public static void  displayExitMessage() {
        output("Thank you for using the Vendo-Matic 800");
        output("Umbrella Corp: Our business is Life itself");
    }

    public static TreeMap<String, Product> getProductMapFromFile() {
        File vendingMachineProductList = new File("vendingmachine.csv");
        TreeMap<String,Product> productMap = new TreeMap<>();

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

    public static String formatMoney (double money) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(money);
    }

    public static boolean isValidYOrN(String toValidate) {
        List<String> validation = Arrays.asList("y", "Y", "n", "N");
        return validation.contains(toValidate);
    }

    //output sales report to file
    public static void outputToFile (String lineToOutput) {
        try(FileWriter fWriter = new FileWriter("auditlog.txt", true);
            PrintWriter writer = new PrintWriter(fWriter)) {
        writer.println(lineToOutput);
        } catch(FileNotFoundException e) {
            System.out.println("File not found!");
        } catch(IOException e) {
            System.out.println("IOException!");
        }
    }

    public static void outputToFileNoBreak (String lineToOutput) {
        try(FileWriter fWriter = new FileWriter("auditlog.txt", true);
            PrintWriter writer = new PrintWriter(fWriter)) {
            writer.print(lineToOutput);
        } catch(FileNotFoundException e) {
            System.out.println("File not found!");
        } catch(IOException e) {
            System.out.println("IOException!");
        }
    }
}
