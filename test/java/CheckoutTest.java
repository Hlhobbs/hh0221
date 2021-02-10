import com.hh0221.DTO.RentalAgreement;
import org.junit.Assert;
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

        Assert.assertEquals(rentalAgreement.getToolType(), "Jackhammer");
    }

    @Test
    public void testTwo() {
        rentalAgreement.createRentalAgreement("LADW", 3, 10, "2020-07-02");

        Assert.assertEquals(rentalAgreement.getToolType(), "Ladder");
    }

    @Test
    public void testThree() {
        rentalAgreement.createRentalAgreement("CHNS", 5, 25, "2015-07-02");

        Assert.assertEquals(rentalAgreement.getToolType(), "Chainsaw");
    }

    @Test
    public void testFour() {
        rentalAgreement.createRentalAgreement("JAKD", 6, 0, "2015-09-03");

        Assert.assertEquals(rentalAgreement.getToolType(), "Jackhammer");
    }

    @Test
    public void testFive() {
        rentalAgreement.createRentalAgreement("JAKR", 9, 0, "2015-07-02");

        Assert.assertEquals(rentalAgreement.getToolType(), "Jackhammer");
    }

    @Test
    public void testSix() {
        rentalAgreement.createRentalAgreement("JAKR", 4, 50, "2020-07-02");

        Assert.assertEquals(rentalAgreement.getToolType(), "Jackhammer");
    }
}
