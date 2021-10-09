package com.techelevator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    private Map<String, Product> productMap = new HashMap<>();
    private static String timeStamp = new SimpleDateFormat(">MM/dd/yyyy HH:mm:ss ").format(new Date());


    public VendingMachine(Map<String, Product> productMap) {
        this.productMap = productMap;
    }

    public Map<String, Product> getProductMap() {
        return productMap;
    }


    //feed money

    //dispense product
    public void dispenseProduct() {
        Scanner scanner = new Scanner(System.in);
        boolean dispenseMore = true;

        while(dispenseMore) {

            IOHelper.displayProductMap(getProductMap());
            IOHelper.output(" ");
            IOHelper.output("Which product would you like? ");

            String productSelection = IOHelper.captureProductSelection(scanner);
            productSelection = productSelection.toUpperCase();

            Product productToDispense = getProductMap().get(productSelection);

            IOHelper.output("You have chosen " + productToDispense.getName());
            IOHelper.output("Please retrieve your " + productToDispense.getName() + " from the bin below!");
            IOHelper.displayProductPurchaseMethod(productSelection);

            //subtract inventory
            productToDispense.subtractInventory();

            //subtract cost

            //output sale to sales log outputs to screen currently need to output to file log
            IOHelper.output(timeStamp + getProductMap().get(productSelection).getName() + " " + getProductMap().get(productSelection).getLocation()); // + \current money + \remaining money


            IOHelper.output("Would you like to purchase another item? (y or n)");
            String yesOrNo = IOHelper.captureYesOrNo(scanner);
            yesOrNo = yesOrNo.toLowerCase();
            if (yesOrNo.equals("n")) {
                dispenseMore = false;
            }

        }
    }
}
