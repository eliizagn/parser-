package elizagn;

import elizagn.expressions.ExpressionParser;
import elizagn.interfaces.IExpression;
import elizagn.visitors.DotGraphVisitor;
import elizagn.visitors.SimplificationVisitor;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        var expression = new ExpressionParser().parse("(x+y+2)*(x+y+3)");
        var simplified = simplify(expression);
        var variables = Map.of("x", 3.0, "y", 3.0);
        var variables1 = Map.of("x", 3.0, "y", 8.0);
        
        System.out.println("Result of expression: " + simplified.evaluate(variables));
        System.out.println("Result of expression: " + simplified.evaluate(variables1));
        System.out.println("Dot graph of regular expression: " + makeDotGraph(expression));
        System.out.println("Dot graph compressed expression: " + makeDotGraph(simplified));
    }

    private static IExpression simplify(IExpression expression) {
        var visitor = new SimplificationVisitor();
        expression.accept(visitor);

        return visitor.makeUnique(expression);
    }

    private static String makeDotGraph(IExpression expression) {
        var graphVisitor = new DotGraphVisitor();
        expression.accept(graphVisitor);

        return graphVisitor.buildDotGraph();
    }
}