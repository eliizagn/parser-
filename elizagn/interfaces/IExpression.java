package elizagn.interfaces;

import java.util.Map;

public interface IExpression {
    double evaluate(Map<String, Double> variables);

    void accept(IExpressionVisitor visitor);
}
