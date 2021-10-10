package com.techelevator;

import java.text.SimpleDateFormat;
import java.util.*;

public class VendingMachine {
    private Map<String, Product> productMap = new TreeMap<>();
    private static String timeStamp = new SimpleDateFormat(">MM/dd/yyyy HH:mm:ss ").format(new Date());
    private double totalMoney = 0;

    public VendingMachine(Map<String, Product> productMap) {
        this.productMap = productMap;
    }

    public Map<String, Product> getProductMap() {
        return productMap;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    //feed money
    public void feedMoney() {
        Scanner scanner = new Scanner(System.in);

        boolean continueFeeding = true;

        while (continueFeeding) {

            IOHelper.output("Your current balance is " + IOHelper.formatMoney(totalMoney));
            IOHelper.output("How much would you like to add to your balance? ");

            double moneyInserted = IOHelper.insertMoney(scanner);
            IOHelper.outputToFileNoBreak(timeStamp + "FEED MONEY: \\" + IOHelper.formatMoney(totalMoney)); //time stamp start
            totalMoney = totalMoney + moneyInserted;
            IOHelper.outputToFile(" \\" + IOHelper.formatMoney(totalMoney)); //time stamp end

            //need to output timestamp to file instead of to user

            IOHelper.output("Your current balance is " + IOHelper.formatMoney(totalMoney));

            IOHelper.output("Would you like to add more money? ");
            String yesOrNo = IOHelper.captureYesOrNo(scanner);

            if (yesOrNo.equals("n")) {
                continueFeeding = false;
            }
        }
    }

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

            if (productMap.containsKey(productSelection)) {
                Product productToDispense = getProductMap().get(productSelection);

                IOHelper.output("You have chosen " + productToDispense.getName());

                if (getProductMap().get(productSelection).getQuantity() > 0) {
                    if (totalMoney > getProductMap().get(productSelection).getPrice()) {

                        IOHelper.output("Please retrieve your " + productToDispense.getName() + " from the bin below!");
                        IOHelper.displayProductPurchaseMethod(productSelection);

                        //subtract inventory
                        productToDispense.subtractInventory();

                        //subtract cost && time stamp
                        IOHelper.outputToFile(timeStamp + getProductMap().get(productSelection).getName() + " " + getProductMap().get(productSelection).getLocation()); //start time stamp
                        IOHelper.outputToFileNoBreak(" \\" + IOHelper.formatMoney(totalMoney));
                        totalMoney = totalMoney - getProductMap().get(productSelection).getPrice();
                        IOHelper.outputToFile(" \\" + IOHelper.formatMoney(totalMoney)); //finish time stamp

                        //output sale to sales log outputs to screen currently need to output to file log

                    } else {
                        IOHelper.output("You do not have enough money to purchase " + getProductMap().get(productSelection).getName());
                    }
                } else {
                    IOHelper.output("There are no " + getProductMap().get(productSelection).getName());
                }
            } else {
                IOHelper.output("That product does not exist");
            }

            IOHelper.output("You have " + IOHelper.formatMoney(totalMoney) + " left.");
            IOHelper.output("Would you like to purchase another item? (y or n)");
            String yesOrNo = IOHelper.captureYesOrNo(scanner);
            yesOrNo = yesOrNo.toLowerCase();
            if (yesOrNo.equals("n")) {
                dispenseMore = false;
            }

        }
    }

    public String calculateChange() {
        double quarter = .25;
        double dime = .10;
        double nickle = .05;
        int quarterCounter = 0;
        int dimeCounter = 0;
        int nickleCounter = 0;

        IOHelper.outputToFileNoBreak(timeStamp + "GIVE CHANGE: \\" + IOHelper.formatMoney(totalMoney));

        while (totalMoney > 0.0) {
            if (totalMoney >= quarter) {
                totalMoney -= .25;
                quarterCounter ++;
            } else if (totalMoney >= dime) {
                totalMoney -= .10;
                dimeCounter ++;
            } else if (totalMoney >= nickle) {
                totalMoney -= .05;
                nickleCounter ++;
            }
        }

        IOHelper.outputToFile(" \\" + IOHelper.formatMoney(totalMoney));
        String changeReturned = quarterCounter + " Quarters " + dimeCounter + " Dimes " + nickleCounter  + " Nickles";
        return changeReturned;
    }
}

