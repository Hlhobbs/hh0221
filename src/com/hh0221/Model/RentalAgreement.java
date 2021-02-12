package com.hh0221.Model;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import java.text.ParseException;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.*;

public class RentalAgreement {

    private final UserInputException userInputException = new UserInputException();

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");

    private final Ladder ladder = new Ladder();

    private final Chainsaw chainsaw = new Chainsaw();

    private final Jackhammer jackhammer = new Jackhammer();

    private String toolCode;

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    private String toolType;

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    private String toolBrand;

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    private int rentalDays;

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    private String checkoutDate;

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    private String dueDate;

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String checkoutDate, int rentalDays) {
        LocalDate dateDue = convertStringToDate(checkoutDate).plusDays(rentalDays);
        DateTimeFormatter formattedDueDate = DateTimeFormatter.ofPattern("MM/dd/yy");
        this.dueDate = dateDue.format(formattedDueDate);
    }

    private double dailyRentalCharge;

    public void setDailyRentalCharge(double dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    private double prediscountCharge;

    public double getPrediscountCharge() {
        return prediscountCharge;
    }

    public void setPrediscountCharge(int chargeDays, double dailyRentalCharge) {

        this.prediscountCharge = new BigDecimal(chargeDays * dailyRentalCharge).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private int discountPercent;

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    private double discountAmount;

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountPercent, double prediscountCharge) {

        double discountPercentDecimal = (discountPercent / 100.00);

        this.discountAmount = new BigDecimal(prediscountCharge * discountPercentDecimal).setScale(2, RoundingMode.HALF_UP).doubleValue();

    }

    private double finalCharge;

    public double getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(double prediscountCharge, double discountAmount) {


        this.finalCharge = new BigDecimal(prediscountCharge - discountAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private int chargeDays;

    //Takes the amount of days the user wants to rent the tool, and the day they rented the tool,
    // and calculates how many days they will actually be charged based on which tool they rented
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
                DayOfWeek day = convertedCheckoutDate.withDayOfYear(i).getDayOfWeek();
                if(day.equals(DayOfWeek.SATURDAY) || day.equals((DayOfWeek.SUNDAY))){
                    unchargedDays++;
                }
            }
        }
        if (!holidayCharge) {
            for (int i = convertedCheckoutDate.getDayOfYear(); i < LocalDate.parse(dueDate, formatter).getDayOfYear(); i++){
                if (i == currentIndependenceDay.getDayOfYear() || i == laborDay.getDayOfYear()){
                    unchargedDays++;
                }
            }
        }
        chargeDays = rentalDays - unchargedDays;
    }

    public int getChargeDays() {

        return chargeDays;
    }

    //Using the inputs the user gave, this assigns their agreement the pre-defined variables of the tool they asked for,
    //and also calls the necessary functions to calculate the variables that can't be defined without user input (e.g. how many days they will be charged, the final charge sum)
    public void createRentalAgreement(String toolCode, int dayCount, int discountPercent, String rawCheckoutDate) throws ParseException {
        userInputException.RentalDaysException(dayCount);
        userInputException.DiscountPercentException(discountPercent);
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

    //Prints out the rental agreement to the user in the CLI, tabulated to present in a more uniform way
    public void printRentalAgreement() {
        System.out.println("Tool code:\t\t\t\t\t"+toolCode+"\nTool type:\t\t\t\t\t"+toolType+
                "\nTool brand:\t\t\t\t\t"+toolBrand+"\nAmount of days renting:\t\t\t\t"+rentalDays+
                "\nDate tool was rented:\t\t\t\t"+checkoutDate+"\nDate tool must be returned:\t\t\t"+dueDate+
                "\nAmount owed per day:\t\t\t\t$"+dailyRentalCharge+"\nAmount of days where charged will be incurred:\t"+chargeDays+
                "\nTotal charge before discount:\t\t\t$"+prediscountCharge+"\nDiscount Percent:\t\t\t\t"+discountPercent+
                "%\nTotal discount amount:\t\t\t\t$"+discountAmount+"\nFinal charge:\t\t\t\t\t$"+finalCharge);
    }

    private LocalDate convertStringToDate(String date){
        return LocalDate.parse(date, formatter);
    }

}
