package net.binout.codestory2013.scalaskel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Scalaskel {

    public List<ScalaskelResult> change(int nbCents) {
        if (nbCents == 1) {
            ScalaskelResult result  = new ScalaskelResult();
            result.setFoo(1);
            return Collections.singletonList(result);
        }  else {
            ScalaskelResult result1  = new ScalaskelResult();
            result1.setBar(1);
            ScalaskelResult result2  = new ScalaskelResult();
            result2.setFoo(7);
            return Arrays.asList(result2, result1);
        }
    }
}
