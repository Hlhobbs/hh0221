package com.hh0221.Controller;

import com.hh0221.DTO.RentalAgreement;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckoutController {



    public void Checkout() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        String printAgreement;
        RentalAgreement agreement = new RentalAgreement();
        String toolCode = inputToolCode(scanner);
        int rentalDays = inputRentalDays(scanner);
        int discountPercent = inputDiscountPercentage(scanner);
        String checkoutDate = inputCheckoutDate(scanner);
        agreement.createRentalAgreement(toolCode, rentalDays, discountPercent, checkoutDate);
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


    private String inputToolCode(Scanner s) {
        String toolCode;
        do {
            System.out.println("Please enter the tool code of the tool you wish to rent:");
            toolCode = s.next().toUpperCase(Locale.ROOT);
            if (!toolCode.equals("LADW") && !toolCode.equals("CHNS") && !toolCode.equals("JAKR") && !toolCode.equals("JAKD")) {
                System.out.println("That is not a code of a tool we have available. Please try again!");
            }
        } while (!toolCode.equals("LADW") && !toolCode.equals("CHNS") && !toolCode.equals("JAKR") && !toolCode.equals("JAKD"));
        return toolCode;
    }

    private int inputRentalDays(Scanner s) {
        int rentalDays = 0;
        boolean isInt = false;
        do {
            try{
                System.out.println("Please enter how many days you wish to rent this tool:");
                rentalDays = s.nextInt();
                if (rentalDays < 1){
                    throw new NumberFormatException("The number you have given is less than 1, please enter a number greater than or equal to 1!");
                }else
                    isInt = true;
            }catch (InputMismatchException e) {
                s.next();
                System.out.println("Please enter a number!");
            }
        }while (!isInt);
        return rentalDays;
    }

    private int inputDiscountPercentage(Scanner s){
        int discount = 0;
        boolean isInt = false;
        do {
            try{
                System.out.println("Please enter what percentage of a discount you would like to apply:");
                discount = s.nextInt();
                if (discount < 0 || discount > 100){
                    throw new NumberFormatException("The discount percentage must be between 0 and 100!");
                }else
                    isInt = true;
            }catch (InputMismatchException e) {
                s.next();
                System.out.println("Please enter a number!");
            }
        }while (!isInt);
        return discount;
    }

    private String inputCheckoutDate(Scanner s){
        final String regex = "[0-9]{2}[/,'-][0-9]{2}[/,'-][0-9]{4}";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        String checkoutDate;
        Matcher matcher;
        do {
            System.out.println("Please enter today's date (MM/DD/YYYY):");
            checkoutDate = s.next();
            matcher = pattern.matcher(checkoutDate);
            if (checkoutDate.contains("-")){
                checkoutDate = checkoutDate.replaceAll("-", "/");
                matcher = pattern.matcher(checkoutDate);
            }
            if (!matcher.matches()) {
                s.next();
                System.out.println("Please enter today's date in a valid format!");
            }
        }while (!matcher.matches());
        return checkoutDate;
    }





}
