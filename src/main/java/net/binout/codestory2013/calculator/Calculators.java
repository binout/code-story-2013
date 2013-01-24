package net.binout.codestory2013.calculator;

public enum Calculators {

    rhino(new RhinoCalculator()),
    groovy(new GroovyCalculator());

    Calculator calculator;
    Calculators(Calculator calculator) {
        this.calculator = calculator;
    }
    public static Calculator getDefaultCalculator() {
        return groovy.calculator;
    }

}
