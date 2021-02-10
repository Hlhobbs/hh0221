package com.hh0221.DTO;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class RentalAgreement {

    Ladder ladder;

    Chainsaw chainsaw;

    Jackhammer ridgidJackhammer;

    Jackhammer deWaltJackHammer;

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
        LocalDate september = LocalDate.of(1970, 9, 1);
        LocalDate laborDay = september.with(firstInMonth(DayOfWeek.MONDAY));
        MonthDay independenceDay = MonthDay.of(7, 4);
        LocalDate currentIndependenceDay = independenceDay.atYear(checkoutDate.getYear());
        if (!weekendCharge) {
            for (checkoutDate.getDayOfYear(); checkoutDate.getDayOfYear() < dueDate.getDayOfYear(); checkoutDate.plus(1, ChronoUnit.DAYS)){
                if(checkoutDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
                    unchargedDays++;
                }else if(checkoutDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                    unchargedDays++;
                }
            }
        }else if (!holidayCharge) {
            if(currentIndependenceDay.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            }
        }
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
            toolBrand = "Stihl";
            toolType = "Chainsaw";
        } else if (toolCode.equals("JAKR")){
            toolBrand = "Ridgid";
            toolType = "Jackhammer";
        } else if (toolCode.equals("JAKD")){
            toolBrand = "DeWalt";
            toolType = "Jackhammer";
        }
    }



    public void printRentalAgreement() {
        System.out.println("Tool code: "+toolCode+"\nTool type: "+toolType);
    }

}
