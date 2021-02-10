import com.hh0221.DTO.RentalAgreement;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckoutTest {

    private RentalAgreement rentalAgreement;

    @BeforeEach
    public void setUp() throws Exception {
        rentalAgreement = new RentalAgreement();
    }

    @Test
    public void testOne() {
        rentalAgreement.createRentalAgreement("JAKR", 5, 101, "2015-09-03");

        Assert.assertEquals("Jackhammer", rentalAgreement.getToolType());
        Assert.assertEquals("Ridgid", rentalAgreement.getToolBrand());
        Assert.assertEquals( 2, rentalAgreement.getChargeDays());
        Assertions.assertEquals( 5.98, rentalAgreement.getPrediscountCharge());
        Assertions.assertEquals( 6.04, rentalAgreement.getDiscountAmount());
        Assertions.assertEquals( -0.06, rentalAgreement.getFinalCharge());
    }

    @Test
    public void testTwo() {
        rentalAgreement.createRentalAgreement("LADW", 3, 10, "2020-07-02");

        Assert.assertEquals(rentalAgreement.getToolType(), "Ladder");
        Assert.assertEquals( 2, rentalAgreement.getChargeDays());
        Assertions.assertEquals( 3.98, rentalAgreement.getPrediscountCharge());
        Assertions.assertEquals( .4, rentalAgreement.getDiscountAmount());
        Assertions.assertEquals( 3.58, rentalAgreement.getFinalCharge());
    }

    @Test
    public void testThree() {
        rentalAgreement.createRentalAgreement("CHNS", 5, 25, "2015-07-02");

        Assert.assertEquals(rentalAgreement.getToolType(), "Chainsaw");
        Assert.assertEquals( 3, rentalAgreement.getChargeDays());
        Assertions.assertEquals( 4.47, rentalAgreement.getPrediscountCharge());
        Assertions.assertEquals( 1.12, rentalAgreement.getDiscountAmount());
        Assertions.assertEquals( 3.35, rentalAgreement.getFinalCharge());
    }

    @Test
    public void testFour() {
        rentalAgreement.createRentalAgreement("JAKD", 6, 0, "2015-09-03");

        Assert.assertEquals(rentalAgreement.getToolType(), "Jackhammer");
        Assert.assertEquals(rentalAgreement.getToolBrand(), "DeWalt");
        Assert.assertEquals( 3, rentalAgreement.getChargeDays());
        Assertions.assertEquals( 8.97, rentalAgreement.getPrediscountCharge());
        Assertions.assertEquals( 0.0, rentalAgreement.getDiscountAmount());
        Assertions.assertEquals( 8.97, rentalAgreement.getFinalCharge());
    }

    @Test
    public void testFive() {
        rentalAgreement.createRentalAgreement("JAKR", 9, 0, "2015-07-02");

        Assert.assertEquals(rentalAgreement.getToolType(), "Jackhammer");
        Assert.assertEquals(rentalAgreement.getToolBrand(), "Ridgid");
        Assert.assertEquals( 5, rentalAgreement.getChargeDays());
        Assertions.assertEquals( 14.95, rentalAgreement.getPrediscountCharge());
        Assertions.assertEquals( 0.0, rentalAgreement.getDiscountAmount());
        Assertions.assertEquals( 14.95, rentalAgreement.getFinalCharge());

    }

    @Test
    public void testSix() {
        rentalAgreement.createRentalAgreement("JAKR", 4, 50, "2020-07-02");

        Assert.assertEquals(rentalAgreement.getToolType(), "Jackhammer");
        Assert.assertEquals(rentalAgreement.getToolBrand(), "Ridgid");
        Assert.assertEquals( 1, rentalAgreement.getChargeDays());
        Assertions.assertEquals( 2.99, rentalAgreement.getPrediscountCharge());
        Assertions.assertEquals( 1.50, rentalAgreement.getDiscountAmount());
        Assertions.assertEquals( 1.49, rentalAgreement.getFinalCharge());
    }
}
