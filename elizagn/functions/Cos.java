package elizagn.functions;

import elizagn.functions.base.UnaryFunction;
import elizagn.interfaces.IExpression;
import elizagn.interfaces.IExpressionVisitor;

public class Cos extends UnaryFunction {
    public Cos(IExpression expression) {
        super(expression);
    }

    @Override
    protected double evaluate() {
        return Math.cos(getA());
    }

    @Override
    public void accept(IExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
