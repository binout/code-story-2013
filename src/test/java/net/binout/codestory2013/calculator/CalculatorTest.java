package net.binout.codestory2013.calculator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorTest {

    private Calculator calculator = Calculators.getDefaultCalculator();

    @DataProvider
    public Object[][] operations() {
        return new Object[][] {
                new String[] {"1+1",  "2"},
                new String[] {"(1+2)/2",  "1.5"},
                new String[] {"1.5*4",  "6"},
                new String[] {"((1.1+2)+3.14+4+(5+6+7)+(8+9+10)*4267387833344334647677634)/2*553344300034334349999000", "31878018903828901761984975061078744643351263313920"},
        };
    }

    @Test(dataProvider = "operations")
    public void should_compute_simple_add(String op, String expected) {
        String result = calculator.calculate(op);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, expected);
    }
}
