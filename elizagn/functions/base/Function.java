package elizagn.functions.base;

import java.util.List;
import java.util.Map;
import elizagn.interfaces.IExpression;

public abstract class Function implements IExpression {
    private final List<IExpression> _expressions;
    protected List<Double> args;
    private Double cache = null;
    private int varsHash = 0;

    protected Function(List<IExpression> expressions) {
        _expressions = expressions;
    }

    protected abstract double evaluate();

    public List<IExpression> getExpressions() {
        return _expressions;
    }

    private void clearCache() {
        cache = null;
    }

    @Override
   public double evaluate(Map<String, Double> variables) {
        int newVarsHash = variables.hashCode();
        args = _expressions.stream()
                .map(arg -> arg.evaluate(variables))
                .toList();
        varsHash = newVarsHash ^ args.hashCode();

        if (cache != null && newVarsHash == varsHash) {
            return cache;
        }

        cache = evaluate();
        double result = cache;
        clearCache();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Function function))
            return false;

        return _expressions.equals(function._expressions);
    }

    @Override
    public int hashCode() {
        return _expressions.hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(_expressions);
    }
}