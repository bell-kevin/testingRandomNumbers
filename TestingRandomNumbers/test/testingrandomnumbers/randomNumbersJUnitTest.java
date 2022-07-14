package testingrandomnumbers;

import org.junit.Test;
import static org.junit.Assert.*;

public class randomNumbersJUnitTest {

    public randomNumbersJUnitTest() {
    }

    @Test
    public void randomNumbers() {
        //arrange
        int n1 = 20;
        int n2 = 30;
        int result;
        //act
        for (int i = 1; i < 5000; i++) {
            //variable = package.Class.method();
            result = testingrandomnumbers.TestingRandomNumbers.getRN(n1, n2);
            //assert
            assertTrue("random number " + result + " is not in range", result >= n1 && result <= n2);
        } //end For loop        
    } //end randomNumbers
} //end class randomNumbersJUnitTest
