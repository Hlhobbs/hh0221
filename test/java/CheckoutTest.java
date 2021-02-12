import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import java.text.ParseException;
import com.hh0221.Model.RentalAgreement;
import org.junit.jupiter.api.Test;


public class CheckoutTest {

    private RentalAgreement rentalAgreement;

    @BeforeEach
    public void setUp() {
        rentalAgreement = new RentalAgreement();
    }

    @Test
    public void testOneThrowsException() {
        NumberFormatException discountException = assertThrows(NumberFormatException.class, () -> rentalAgreement.createRentalAgreement("JAKR", 5, 101, "09/03/2015"));
        Assertions.assertTrue(discountException.getMessage().contains("The discount percentage must be between 0 and 100!"));
    }

    @Test
    public void testTwo() throws ParseException {
        rentalAgreement.createRentalAgreement("LADW", 3, 10, "07/02/2020");

        Assertions.assertEquals(rentalAgreement.getToolType(), "Ladder");
        Assertions.assertEquals( 2, rentalAgreement.getChargeDays());
        Assertions.assertEquals( 3.98, rentalAgreement.getPrediscountCharge());
        Assertions.assertEquals( .4, rentalAgreement.getDiscountAmount());
        Assertions.assertEquals( 3.58, rentalAgreement.getFinalCharge());
        Assertions.assertEquals( "07/05/20", rentalAgreement.getDueDate());
    }

    @Test
    public void testThree() throws ParseException {
        rentalAgreement.createRentalAgreement("CHNS", 5, 25, "07/02/2015");

        Assertions.assertEquals(rentalAgreement.getToolType(), "Chainsaw");
        Assertions.assertEquals( 3, rentalAgreement.getChargeDays());
        Assertions.assertEquals( 4.47, rentalAgreement.getPrediscountCharge());
        Assertions.assertEquals( 1.12, rentalAgreement.getDiscountAmount());
        Assertions.assertEquals( 3.35, rentalAgreement.getFinalCharge());
        Assertions.assertEquals( "07/07/15", rentalAgreement.getDueDate());
    }

    @Test
    public void testFour() throws ParseException {
        rentalAgreement.createRentalAgreement("JAKD", 6, 0, "09/03/2015");

        Assertions.assertEquals(rentalAgreement.getToolType(), "Jackhammer");
        Assertions.assertEquals(rentalAgreement.getToolBrand(), "DeWalt");
        Assertions.assertEquals( 3, rentalAgreement.getChargeDays());
        Assertions.assertEquals( 8.97, rentalAgreement.getPrediscountCharge());
        Assertions.assertEquals( 0.0, rentalAgreement.getDiscountAmount());
        Assertions.assertEquals( 8.97, rentalAgreement.getFinalCharge());
        Assertions.assertEquals( "09/09/15", rentalAgreement.getDueDate());
    }

    @Test
    public void testFive() throws ParseException {
        rentalAgreement.createRentalAgreement("JAKR", 9, 0, "07/02/2015");

        Assertions.assertEquals(rentalAgreement.getToolType(), "Jackhammer");
        Assertions.assertEquals(rentalAgreement.getToolBrand(), "Ridgid");
        Assertions.assertEquals( 5, rentalAgreement.getChargeDays());
        Assertions.assertEquals( 14.95, rentalAgreement.getPrediscountCharge());
        Assertions.assertEquals( 0.0, rentalAgreement.getDiscountAmount());
        Assertions.assertEquals( 14.95, rentalAgreement.getFinalCharge());
        Assertions.assertEquals( "07/11/15", rentalAgreement.getDueDate());

    }

    @Test
    public void testSix() throws ParseException {
        rentalAgreement.createRentalAgreement("JAKR", 4, 50, "07/02/2020");

        Assertions.assertEquals(rentalAgreement.getToolType(), "Jackhammer");
        Assertions.assertEquals(rentalAgreement.getToolBrand(), "Ridgid");
        Assertions.assertEquals( 1, rentalAgreement.getChargeDays());
        Assertions.assertEquals( 2.99, rentalAgreement.getPrediscountCharge());
        Assertions.assertEquals( 1.50, rentalAgreement.getDiscountAmount());
        Assertions.assertEquals( 1.49, rentalAgreement.getFinalCharge());
        Assertions.assertEquals( "07/06/20", rentalAgreement.getDueDate());
    }
}
