package com.hh0221.Controller;

import com.hh0221.DTO.RentalAgreement;

import java.time.LocalDate;
import java.util.Scanner;

public class CheckoutController {



    public void ScannerOutput() {
        RentalAgreement agreement = new RentalAgreement();
        Scanner s = new Scanner(System.in);
        String toolCode;
        int rentalDays = 0;
        boolean isInt = false;
        int discount = 0;
        LocalDate checkoutDate;
        do {
            System.out.println("Please enter the tool code of the tool you wish to rent:");
            toolCode = s.next();
            if (!toolCode.equals("LADW") && !toolCode.equals("CHNS") && !toolCode.equals("JAKR") && !toolCode.equals("JAKD")) {
                System.out.println("That is not a code of a tool we have available. Please try again!");
            }
        } while (!toolCode.equals("LADW") && !toolCode.equals("CHNS") && !toolCode.equals("JAKR") && !toolCode.equals("JAKD"));
        agreement.setToolCode(toolCode);
        do {
            System.out.println("Please enter how many days you wish to rent this tool:");
            if (s.hasNextInt()) {
                rentalDays = s.nextInt();
                isInt = true;
            }else{
                s.next();
                System.out.println("Please enter a number!");
            }
        }while (!isInt);
        agreement.setRentalDays(rentalDays);
        isInt = false;
        do {
            System.out.println("Enter a discount percentage, in whole numbers:");
            if (s.hasNextInt()) {
                discount = s.nextInt();
                isInt = true;
            }else{
                s.next();
                System.out.println("Please enter a number!");
            }
        }while (!isInt);
        System.out.println("You have entered the Tool Code: " + agreement.getToolCode() + ", and you wish to rent this tool for: " + agreement.getRentalDays() + " days.");
    }





}
