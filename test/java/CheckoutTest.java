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
        rentalAgreement.createRentalAgreement("JAKR", 5, 101, "09-03-15");

        Assert.assertEquals(rentalAgreement.getToolType(), "Jackhammer");
    }
}
