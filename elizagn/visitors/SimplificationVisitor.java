package elizagn.visitors;

import elizagn.functions.Cos;
import elizagn.functions.Pow;
import elizagn.functions.base.Function;
import elizagn.interfaces.IExpression;
import elizagn.interfaces.IExpressionVisitor;
import elizagn.operators.Divide;
import elizagn.operators.Multiply;
import elizagn.operators.Substract;
import elizagn.operators.Add;
import elizagn.values.Const;
import elizagn.values.Variable;

import java.util.HashMap;
import java.util.Map;

public class SimplificationVisitor implements IExpressionVisitor {
    private final Map<Integer, IExpression> _uniqueExpressions = new HashMap<>();

    public IExpression makeUnique(IExpression expression) {
        // tries to get, but if not present, adds and returns
        return _uniqueExpressions.putIfAbsent(expression.hashCode(), expression);
    }

    @Override
    public void visit(Const expression) {
        makeUnique(expression);
    }

    @Override
    public void visit(Variable expression) {
        makeUnique(expression);
    }

    @Override
    public void visit(Cos expression) {
        visitChildren(expression);

        var a = makeUnique(expression.getExpressions().get(0));
        makeUnique(new Cos(a));
    }

    @Override
    public void visit(Pow expression) {
        visitChildren(expression);

        var a = makeUnique(expression.getExpressions().get(0));
        var b = makeUnique(expression.getExpressions().get(1));
        makeUnique(new Pow(a, b));
    }

    @Override
    public void visit(Divide expression) {
        visitChildren(expression);

        var a = makeUnique(expression.getExpressions().get(0));
        var b = makeUnique(expression.getExpressions().get(1));
        makeUnique(new Divide(a, b));
    }

    @Override
    public void visit(Multiply expression) {
        visitChildren(expression);

        var a = makeUnique(expression.getExpressions().get(0));
        var b = makeUnique(expression.getExpressions().get(1));
        makeUnique(new Multiply(a, b));
    }

    @Override
    public void visit(Substract expression) {
        visitChildren(expression);

        var a = makeUnique(expression.getExpressions().get(0));
        var b = makeUnique(expression.getExpressions().get(1));
        makeUnique(new Substract(a, b));
    }

    @Override
    public void visit(Add expression) {
        visitChildren(expression);

        var a = makeUnique(expression.getExpressions().get(0));
        var b = makeUnique(expression.getExpressions().get(1));
        makeUnique(new Add(a, b));
    }

    private void visitChildren(Function expression) {
        for (var expr : expression.getExpressions()) {
            expr.accept(this);
        }
    }
}