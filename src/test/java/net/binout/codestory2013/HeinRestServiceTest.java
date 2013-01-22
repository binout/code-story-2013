package net.binout.codestory2013;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HeinRestServiceTest {

    @Test
    public void constructor_should_load_enonce() {
        HeinRestService service = new HeinRestService();
        Assert.assertNotNull(service.enonces);
        Assert.assertEquals(service.enonces.size(),  1);
    }

    @Test
    public void constructor_should_create_empty_cache() {
        HeinRestService service = new HeinRestService();
        Assert.assertNotNull(service.cache);
        Assert.assertEquals(service.cache.size(),  0);
    }

    @Test
    public void postcontruct_should_load_cache() {
        HeinRestService service = new HeinRestService();
        service.initScalaskelCache();
        Assert.assertTrue(service.cache.size() > 0);
    }

    @Test
    public void constructor_should_create_calculator() {
        HeinRestService service = new HeinRestService();
        Assert.assertNotNull(service.calculator);
    }

    @DataProvider
    public Object[][] toMathFrench_provider() {
        return new Object[][] {
                new String[] {"1.5", "1,5"},
                new String[] {"1.0", "1"},
        };
    }

    @Test(dataProvider = "toMathFrench_provider")
    public void toMathFrench_should_convert(String exp, String expected) {
        HeinRestService service = new HeinRestService();
        String math = service.toMathFrench(exp);
        Assert.assertEquals(math, expected);
    }
}
