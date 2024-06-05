package elizagn.interfaces;

import elizagn.functions.Cos;
import elizagn.functions.Pow;
import elizagn.operators.Divide;
import elizagn.operators.Multiply;
import elizagn.operators.Substract;
import elizagn.operators.Add;
import elizagn.values.Const;
import elizagn.values.Variable;

public interface IExpressionVisitor {
    void visit(Const expression);

    void visit(Variable expression);

    void visit(Cos expression);

    void visit(Pow expression);

    void visit(Divide expression);

    void visit(Multiply expression);

    void visit(Substract expression);

    void visit(Add expression);
}