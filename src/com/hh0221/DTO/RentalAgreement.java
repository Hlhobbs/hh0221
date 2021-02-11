package com.hh0221.DTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class RentalAgreement {

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");

    Ladder ladder = new Ladder();

    Chainsaw chainsaw = new Chainsaw();

    Jackhammer jackhammer = new Jackhammer();

    String toolCode;

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    String toolType;

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    String toolBrand;

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    int rentalDays;

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    String checkoutDate;

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    String dueDate;

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String checkoutDate, int rentalDays) {
        LocalDate dateDue = convertStringToDate(checkoutDate).plusDays(rentalDays);
        DateTimeFormatter formattedDueDate = DateTimeFormatter.ofPattern("MM/dd/yy");
        this.dueDate = dateDue.format(formattedDueDate);
    }

    double dailyRentalCharge;

    public void setDailyRentalCharge(double dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    double prediscountCharge;

    public double getPrediscountCharge() {
        return prediscountCharge;
    }

    public void setPrediscountCharge(int chargeDays, double dailyRentalCharge) {

        this.prediscountCharge = new BigDecimal(chargeDays * dailyRentalCharge).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    int discountPercent;

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    double discountAmount;

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountPercent, double prediscountCharge) {

        double discountPercentDecimal = (discountPercent / 100.00);

        this.discountAmount = new BigDecimal(prediscountCharge * discountPercentDecimal).setScale(2, RoundingMode.HALF_UP).doubleValue();

    }

    double finalCharge;

    public double getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(double prediscountCharge, double discountAmount) {


        this.finalCharge = new BigDecimal(prediscountCharge - discountAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    int chargeDays;

    private void setChargeDays(int rentalDays, String checkoutDate, boolean weekendCharge, boolean holidayCharge) {
        LocalDate convertedCheckoutDate = convertStringToDate(checkoutDate);

        int unchargedDays = 0;
        MonthDay independenceDay = MonthDay.of(7, 4);
        LocalDate currentIndependenceDay = independenceDay.atYear(convertedCheckoutDate.getYear());
        if(currentIndependenceDay.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            currentIndependenceDay = currentIndependenceDay.minusDays(1);
        }else if (currentIndependenceDay.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            currentIndependenceDay = currentIndependenceDay.plusDays(1);
        }
        LocalDate september = convertedCheckoutDate.withMonth(9);
        LocalDate laborDay = september.with(firstInMonth(DayOfWeek.MONDAY));
        if (!weekendCharge) {
            for (int i = convertedCheckoutDate.getDayOfYear(); i <= LocalDate.parse(dueDate, formatter).getDayOfYear(); i++){

                if(convertedCheckoutDate.withDayOfYear(i).getDayOfWeek().equals(DayOfWeek.SATURDAY)){
                    unchargedDays++;
                }else if(convertedCheckoutDate.withDayOfYear(i).getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                    unchargedDays++;
                }
            }
        }
        if (!holidayCharge) {
            for (int i = convertedCheckoutDate.getDayOfYear(); i < LocalDate.parse(dueDate, formatter).getDayOfYear(); i++){
                if (i == currentIndependenceDay.getDayOfYear()){
                    unchargedDays++;
                } else if (i == laborDay.getDayOfYear()){
                    unchargedDays++;
                }
            }
        }
        chargeDays = rentalDays - unchargedDays;
    }

    public int getChargeDays() {

        return chargeDays;
    }

    public void createRentalAgreement(String toolCode, int dayCount, int discountPercent, String rawCheckoutDate) throws ParseException {
        if (dayCount < 1){
            throw new NumberFormatException("The number you have given is less than 1, please enter a number greater than or equal to 1!");
        }else if (discountPercent < 0 || discountPercent > 100){
            throw new NumberFormatException("The discount percentage must be between 0 and 100!");
        }
        setToolCode(toolCode);
        setDiscountPercent(discountPercent);
        setRentalDays(dayCount);
        SimpleDateFormat dateFormatFromInput = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat unifiedFormat = new SimpleDateFormat("MM/dd/yy");
        String formattedCheckoutDate = unifiedFormat.format(dateFormatFromInput.parse(rawCheckoutDate));
        setCheckoutDate(formattedCheckoutDate);
        switch (toolCode) {
            case "LADW" -> {
                setToolType(ladder.toolType);
                setToolBrand(ladder.brand);
                setDueDate(checkoutDate, rentalDays);
                setDailyRentalCharge(ladder.dailyCharge);
                setChargeDays(dayCount, checkoutDate, true, false);
                setPrediscountCharge(chargeDays, dailyRentalCharge);
                setDiscountAmount(discountPercent, prediscountCharge);
                setFinalCharge(prediscountCharge, discountAmount);
            }
            case "CHNS" -> {
                setToolType(chainsaw.toolType);
                setToolBrand(chainsaw.brand);
                setDueDate(checkoutDate, rentalDays);
                setDailyRentalCharge(chainsaw.dailyCharge);
                setChargeDays(dayCount, checkoutDate, false, true);
                setPrediscountCharge(chargeDays, dailyRentalCharge);
                setDiscountAmount(discountPercent, prediscountCharge);
                setFinalCharge(prediscountCharge, discountAmount);
            }
            case "JAKR", "JAKD" -> {
                jackhammer.chooseBrand(toolCode);
                setToolType(jackhammer.toolType);
                setToolBrand(jackhammer.brand);
                setDueDate(checkoutDate, rentalDays);
                setDailyRentalCharge(jackhammer.dailyCharge);
                setChargeDays(dayCount, checkoutDate, false, false);
                setPrediscountCharge(chargeDays, dailyRentalCharge);
                setDiscountAmount(discountPercent, prediscountCharge);
                setFinalCharge(prediscountCharge, discountAmount);
            }
        }
    }

    public void printRentalAgreement() {
        System.out.println("Tool code: "+toolCode+"\nTool type: "+toolType+
                "\nTool brand: "+toolBrand+"\nAmount of days renting: "+rentalDays+
                "\nDate tool was rented: "+checkoutDate+"\nDate tool must be returned: "+dueDate+
                "\nAmount owed per day: $"+dailyRentalCharge+"\nAmount of days where charged will be incurred: "+chargeDays+
                "\nTotal charge before discount: $"+prediscountCharge+"\nDiscount Percent: "+discountPercent+
                "%\nTotal discount amount: $"+discountAmount+"\nFinal charge: $"+finalCharge);
    }

    private LocalDate convertStringToDate(String date){
        return LocalDate.parse(date, formatter);
    }

}
