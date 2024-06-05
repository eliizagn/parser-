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

import java.util.HashSet;
import java.util.Set;

public class DotGraphVisitor implements IExpressionVisitor {
    private final StringBuilder builder;
    private final Set<String> _uniqueExpressions = new HashSet<>();

    public DotGraphVisitor() {
        builder = new StringBuilder();
        builder.append("digraph G { ");
    }

    public String buildDotGraph() {
        builder.append("}");
        return builder.toString();
    }

    @Override
    public void visit(Const expression) {
        addElement(getVariableName(expression), expression.toString());
    }

    @Override
    public void visit(Variable expression) {
        addElement(getVariableName(expression), expression.toString());
    }

    @Override
    public void visit(Cos expression) {
        addElement(getVariableName(expression), "cos");
        visitChildren(expression);
    }

    @Override
    public void visit(Pow expression) {
        addElement(getVariableName(expression), "pow");
        visitChildren(expression);
    }

    @Override
    public void visit(Divide expression) {
        addElement(getVariableName(expression), "/");
        visitChildren(expression);
    }

    @Override
    public void visit(Multiply expression) {
        addElement(getVariableName(expression), "*");
        visitChildren(expression);
    }

    @Override
    public void visit(Substract expression) {
        addElement(getVariableName(expression), "-");
        visitChildren(expression);
    }

    @Override
    public void visit(Add expression) {
        addElement(getVariableName(expression), "+");
        visitChildren(expression);
    }

    private void addDependency(String left, String right) {
        // m -> a;
        builder.append(left);
        builder.append(" -> ");
        builder.append(right);
        builder.append("; ");
    }

    private void addElement(String variable, String label) {
        // m[label="*"];
        if (_uniqueExpressions.contains(variable)) {
            return;
        }

        _uniqueExpressions.add(variable);

        builder.append(variable);
        builder.append("[label=\"");
        builder.append(label);
        builder.append("\"]; ");
    }

    private String getVariableName(IExpression expression) {
        return "E" + Long.toHexString(System.identityHashCode(expression));
    }

    private void visitChildren(Function expression) {
        for (var expr : expression.getExpressions()) {
            expr.accept(this);
            addDependency(getVariableName(expression), getVariableName(expr));
        } // для обхода в глубину, вдруг cos(cos(some_expr)) или cos(pow(some_expr))
    }
}
