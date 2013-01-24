package net.binout.codestory2013.calculator;

import groovy.lang.GroovyShell;

public class GroovyCalculator implements Calculator {

    @Override
    public String calculate(String expression) {
        GroovyShell shell = new GroovyShell();
        return String.valueOf(shell.evaluate("BigDecimal b=" + expression +"; return b"));
    }
}
