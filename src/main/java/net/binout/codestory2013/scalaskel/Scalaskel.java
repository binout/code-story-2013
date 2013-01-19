package net.binout.codestory2013.scalaskel;

import java.util.*;

public class Scalaskel {

    static Map<Integer, String> config = new TreeMap<Integer, String>();
    {
        config.put(7, "bar");
        config.put(11, "qix");
        config.put(21, "baz");
    }

    public Set<ScalaskelResult> change(int nbCents) {
        Set<ScalaskelResult> toReturn = new LinkedHashSet<ScalaskelResult>();

        ScalaskelResult withFoo  = new ScalaskelResult();
        withFoo.setFoo(nbCents);
        toReturn.add(withFoo);

        for (Map.Entry<Integer, String> entry : config.entrySet()) {
            manageDivisor(nbCents, entry.getKey(), entry.getValue(), toReturn);
        }

        return toReturn;
    }

    private void manageDivisor(int nbCents, int divisor, String field, Set<ScalaskelResult> toReturn) {
        if (nbCents == divisor) {
            ScalaskelResult single  = new ScalaskelResult();
            single.add(field, 1);
            toReturn.add(single);
        }

        if (nbCents > divisor) {
            int nb = (nbCents / divisor) -1;
            if (nb == 0) nb = 1;
            for (int i = 1; i<=nb; i++) {
                Set<ScalaskelResult> changeResult = change(nbCents - divisor * i);
                for (ScalaskelResult result : changeResult) {
                    result.add(field, i);
                }
                toReturn.addAll(changeResult);
            }
        }
    }
}
