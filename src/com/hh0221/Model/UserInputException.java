package com.hh0221.Model;

public class UserInputException {

    public void RentalDaysException(int rentalDays){
        if (rentalDays < 1) {
            throw new NumberFormatException("The number you have given is less than 1, please enter a number greater than or equal to 1!");
        }
    }

    public void DiscountPercentException(int discountPercent){
        if (discountPercent < 0 || discountPercent > 100){
            throw new NumberFormatException("The discount percentage must be between 0 and 100!");
        }
    }
}
