package com.hh0221.DTO;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class RentalAgreement {

    Ladder ladder = new Ladder();

    Chainsaw chainsaw = new Chainsaw();

    Jackhammer jackhammer = new Jackhammer();

    String toolCode;

    public String getToolCode() {
        return toolCode;
    }

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

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    String checkoutDate;

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    String dueDate;

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    double dailyRentalCharge;

    public double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public void setDailyRentalCharge(double dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    double prediscountCharge;

    public double getPrediscountCharge() {
        return prediscountCharge;
    }

    public void setPrediscountCharge(double prediscountCharge) {
        this.prediscountCharge = prediscountCharge;
    }

    int discountPercent;

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    double discountAmount;

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    double finalCharge;

    public double getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(double finalCharge) {
        this.finalCharge = finalCharge;
    }

    int chargeDays;

    private void setChargeDays(int dayCount, LocalDate checkoutDate, boolean weekendCharge, boolean holidayCharge) {
        LocalDate dueDate = checkoutDate.plusDays(dayCount);
        int unchargedDays = 0;
        MonthDay independenceDay = MonthDay.of(7, 4);
        LocalDate currentIndependenceDay = independenceDay.atYear(checkoutDate.getYear());
        if(currentIndependenceDay.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            currentIndependenceDay = currentIndependenceDay.minusDays(1);
        }else if (currentIndependenceDay.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            currentIndependenceDay = currentIndependenceDay.plusDays(1);
        }
        LocalDate september = checkoutDate.withMonth(9);
        LocalDate laborDay = september.with(firstInMonth(DayOfWeek.MONDAY));
        if (!weekendCharge) {
            for (int i = checkoutDate.getDayOfYear(); i < dueDate.getDayOfYear(); i++){

                if(checkoutDate.withDayOfYear(i).getDayOfWeek().equals(DayOfWeek.SATURDAY)){
                    unchargedDays++;
                }else if(checkoutDate.withDayOfYear(i).getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                    unchargedDays++;
                }
            }
        }else if (!holidayCharge) {
            for (int i = checkoutDate.getDayOfYear(); i < dueDate.getDayOfYear(); i++){
                if (i == currentIndependenceDay.getDayOfYear()){
                    unchargedDays++;
                } else if (i == laborDay.getDayOfYear()){
                    unchargedDays++;
                }
            }
        }

        chargeDays = dayCount - unchargedDays;
    }

    public int getChargeDays() {

        return chargeDays;
    }

    public void createRentalAgreement(String toolCode, int dayCount, int discountPercent, String checkoutDate){
        setToolCode(toolCode);
        setDiscountPercent(discountPercent);
        setRentalDays(dayCount);
        LocalDate convertedCheckoutDate = LocalDate.parse(checkoutDate);
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MM-dd-yy");
        String formattedCheckoutDate = formatDate.format(convertedCheckoutDate);
        setCheckoutDate(formattedCheckoutDate);
        if (toolCode.equals("LADW")){
            setToolType(ladder.toolType);
            setToolBrand(ladder.brand);
            setDailyRentalCharge(ladder.dailyCharge);
            setChargeDays(dayCount, convertedCheckoutDate, true, false);
        } else if (toolCode.equals("CHNS")){
            setToolType(chainsaw.toolType);
            setToolBrand(chainsaw.brand);
            setDailyRentalCharge(chainsaw.dailyCharge);
            setChargeDays(dayCount, convertedCheckoutDate, true, false);
        } else if (toolCode.equals("JAKR") || toolCode.equals("JAKD")){
            jackhammer.chooseBrand(toolCode);
            setToolType(jackhammer.toolType);
            setToolBrand(jackhammer.brand);
            setDailyRentalCharge(jackhammer.dailyCharge);
            setChargeDays(dayCount, convertedCheckoutDate, true, false);
        }
    }

    public void printRentalAgreement() {
        System.out.println("Tool code: "+toolCode+"\nTool type: "+toolType);
    }

}
