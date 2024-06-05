package elizagn.values;

import java.util.Map;

import elizagn.interfaces.IExpressionVisitor;
import elizagn.interfaces.IValue;

public class Variable implements IValue {
    private final String _value;

    public Variable(String value) {
        _value = value;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        return variables.get(_value);
    }

    @Override
    public void accept(IExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        //проверка для случаев, когда сравниваются две ссылки на один и тот же объект в памяти
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Variable variable = (Variable) o;

        return _value.equals(variable._value);
    }

    @Override
    public int hashCode() {
        return _value.hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(_value);
    }
}