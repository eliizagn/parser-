package elizagn.values;

import java.util.Map;

import elizagn.interfaces.IExpressionVisitor;
import elizagn.interfaces.IValue;

public class Const implements IValue {
    private final double _value;

    public Const(double value) {
        _value = value;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        return _value;
    }

    @Override
    public void accept(IExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Const aConst = (Const) o;

        return Double.compare(_value, aConst._value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(_value);
    }


    @Override
    public String toString() {
        return String.valueOf(_value);
    }
}
