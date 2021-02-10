package com.hh0221.Controller;

import com.hh0221.DTO.RentalAgreement;
import java.util.Scanner;

public class CheckoutController {




    public void ScannerOutput() {
        RentalAgreement agreement = new RentalAgreement();
        Scanner s = new Scanner(System.in);
        String toolCode;
        do {
            System.out.println("What tool do you want?");
            while (!s.nextLine().contains("LADW") || !s.nextLine().contains("JAKR") || !s.nextLine().contains("CHNS") || !s.nextLine().contains("JAKD")){
                String input = s.next();
                System.out.printf("\"%s\" is not a valid tool code. Try again! \n", input);
            }
            toolCode = s.nextLine();
        } while (toolCode.equals("LADW") || toolCode.equals("JAKR") || toolCode.equals("CHNS") || toolCode.equals("JAKD"));
            agreement.setToolCode(toolCode);
            agreement.setToolType("Foo");
            agreement.printRentalAgreement();
    }





}
