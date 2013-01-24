package net.binout.codestory2013.calculator;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class RhinoCalculator implements Calculator {

    @Override
    public String calculate(String expression) {
        // Create and enter a Context. A Context stores information about the execution environment of a script.
        Context cx = Context.enter();
        try {
          // Initialize the standard objects (Object, Function, etc.). This must be done before scripts can be
            // executed. The null parameter tells initStandardObjects
            // to create and return a scope object that we use
            // in later calls.
            Scriptable scope = cx.initStandardObjects();

            // Build the script
            String script = "var s = "+ expression +"; s;";

            // Execute the script
            Object obj = cx.evaluateString(scope, script, "TestScript", 1, null );

            // Cast the result to a string
            return String.valueOf(obj);
        }
        finally
        {
            // Exit the Context. This removes the association between the Context and the current thread and is an
            // essential cleanup action. There should be a call to exit for every call to enter.
            Context.exit();
        }
    }
}
