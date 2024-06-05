package elizagn.functions;

import elizagn.functions.base.BinaryFunction;
import elizagn.interfaces.IExpression;
import elizagn.interfaces.IExpressionVisitor;

public class Pow extends BinaryFunction {
    public Pow(IExpression expressionA, IExpression expressionB) {
        super(expressionA, expressionB);
    }

    @Override
    protected double evaluate() {
        return Math.pow(getA(), getB());
    }

    @Override
    public void accept(IExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
