package elizagn.functions.base;

import java.util.Collections;

import elizagn.interfaces.IExpression;

public abstract class UnaryFunction extends Function {
    private Double a;

    protected UnaryFunction(IExpression expressionA) {
        super(Collections.singletonList(expressionA));
    }

    protected double getA() {
        if (a == null) {
            a = args.get(0);
        }
        return a;
    }
}