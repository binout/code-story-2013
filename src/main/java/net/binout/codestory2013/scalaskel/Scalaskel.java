package net.binout.codestory2013.scalaskel;

import java.util.ArrayList;
import java.util.List;

public class Scalaskel {

    public List<ScalaskelResult> change(int nbCents) {
        List<ScalaskelResult> toReturn = new ArrayList<ScalaskelResult>();

        ScalaskelResult withFoo  = new ScalaskelResult();
        withFoo.setFoo(nbCents);

        toReturn.add(withFoo);

        if (nbCents == 7) {
            ScalaskelResult withBar  = new ScalaskelResult();
            withBar.setBar(1);
            toReturn.add(withBar);
        }

        if (nbCents > 7) {
             int nbBar = (nbCents / 7) -1;
             if (nbBar == 0) nbBar = 1;
             List<ScalaskelResult> barChange = change(nbCents - 7);
             for (ScalaskelResult barResult : barChange) {
                 barResult.addBar(nbBar);
             }
             toReturn.addAll(barChange);
        }

        return toReturn;
    }
}
