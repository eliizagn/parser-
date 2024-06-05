package elizagn.operators;

import elizagn.functions.base.BinaryFunction;
import elizagn.interfaces.IExpression;
import elizagn.interfaces.IExpressionVisitor;

public class Multiply extends BinaryFunction {
    public Multiply(IExpression expressionA, IExpression expressionB) {
        super(expressionA, expressionB);
    }

    @Override
    public double evaluate() {
        return getA() * getB();
    }

    @Override
    public void accept(IExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
