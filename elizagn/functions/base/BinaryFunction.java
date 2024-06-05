package elizagn.functions.base;

import java.util.Arrays;

import elizagn.interfaces.IExpression;

public abstract class BinaryFunction extends Function {
    private Double a;
    private Double b;

    protected BinaryFunction(IExpression expressionA, IExpression expressionB) {
        super(Arrays.asList(expressionA, expressionB));
    }

    protected double getA() {
        if (a == null) {
            a = args.get(0);
        }
        return a;
    }

    protected double getB() {
        if (b == null) {
            b = args.get(1);
        }
        return b;
    }
}