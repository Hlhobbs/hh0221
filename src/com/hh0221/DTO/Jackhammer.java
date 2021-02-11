package com.hh0221.DTO;

public class Jackhammer extends Tool {

    String toolCodeRidgid = "JAKR";

    String toolCodeDeWalt = "JAKD";

    String brandRidgid = "Ridgid";

    String brandDeWalt = "DeWalt";

    public Jackhammer() {

        toolType = "Jackhammer";

        dailyCharge = 2.99;

    }

    public void chooseBrand(String toolCode) {
        if (toolCode.equals(toolCodeRidgid)){
            brand = brandRidgid;

        }else if (toolCode.equals(toolCodeDeWalt)){
            brand = brandDeWalt;
        }

    }
}