package com.hh0221.Controller;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.text.ParseException;
import java.util.regex.Pattern;
import com.hh0221.Model.RentalAgreement;
import com.hh0221.Model.UserInputException;

import java.util.Scanner;


public class CheckoutController {

    private final String[] toolCodes = {"LADW", "CHNS", "JAKR", "JAKD"};
    private final UserInputException userInputException = new UserInputException();
    private final Scanner scanner = new Scanner(System.in);
    private final RentalAgreement agreement = new RentalAgreement();


    //calls the input functions below to get the user's input,
    // then inserts then into the function that creates the rental agreement,
    // and either calls a function to print the agreement,
    // or closes and ends the program depending on the user's choice
    public void checkout() throws ParseException {
        String toolCode = inputToolCode(scanner);
        int rentalDays = inputRentalDays(scanner);
        int discountPercent = inputDiscountPercentage(scanner);
        String checkoutDate = inputCheckoutDate(scanner);
        agreement.createRentalAgreement(toolCode, rentalDays, discountPercent, checkoutDate);
        String printAgreement;
        do {
            System.out.println("Do you wish to print out your rental agreement?");
            printAgreement = scanner.next().toLowerCase(Locale.ROOT);
            if (printAgreement.equals("yes")) {
                agreement.printRentalAgreement();
            } else if (printAgreement.equals("no")) {
                System.out.println("Have a great day! Your " + agreement.getToolType() + " is due on: " + agreement.getDueDate());
                scanner.close();
            } else
                System.out.println("Input not recognized. Please answer with yes or no!");
        }while (!printAgreement.equals("yes") && !printAgreement.equals("no"));
    }


    private String inputToolCode(Scanner scanner) {
        String toolCode;
        do {
            System.out.println("Please enter the tool code of the tool you wish to rent:");
            toolCode = scanner.next().toUpperCase(Locale.ROOT);
            if (!Arrays.asList(toolCodes).contains(toolCode)) {
                System.out.println("That is not a code of a tool we have available. Please try again!");
            }
        } while (!Arrays.asList(toolCodes).contains(toolCode));
        return toolCode;
    }

    private int inputRentalDays(Scanner scanner) {
        int rentalDays = 0;
        boolean isInt = false;
        do {
            try{
                System.out.println("Please enter how many days you wish to rent this tool:");
                rentalDays = scanner.nextInt();
                userInputException.RentalDaysException(rentalDays);
                isInt = true;
            }catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Please enter a number!");
            }
        }while (!isInt);
        return rentalDays;
    }

    private int inputDiscountPercentage(Scanner scanner){
        int discount = 0;
        boolean isInt = false;
        do {
            try{
                System.out.println("Please enter what percentage of a discount you would like to apply:");
                discount = scanner.nextInt();
                userInputException.DiscountPercentException(discount);
                isInt = true;
            }catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Please enter a number!");
            }
        }while (!isInt);
        return discount;
    }

    private String inputCheckoutDate(Scanner scanner){
        final String regex = "[0-9]{2}[/,'-][0-9]{2}[/,'-][0-9]{4}";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        String checkoutDate;
        Matcher matcher;
        do {
            System.out.println("Please enter today's date (MM/DD/YYYY):");
            checkoutDate = scanner.next();
            matcher = pattern.matcher(checkoutDate);
            if (checkoutDate.contains("-")){
                checkoutDate = checkoutDate.replaceAll("-", "/");
                matcher = pattern.matcher(checkoutDate);
            }
            if (!matcher.matches()) {
                scanner.next();
                System.out.println("Please enter today's date in a valid format!");
            }
        }while (!matcher.matches());
        return checkoutDate;
    }





}
