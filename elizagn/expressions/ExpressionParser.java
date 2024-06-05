package elizagn.expressions;

import elizagn.functions.Cos;
import elizagn.functions.Pow;
import elizagn.interfaces.IExpression;
import elizagn.operators.Divide;
import elizagn.operators.Multiply;
import elizagn.operators.Substract;
import elizagn.operators.Add;
import elizagn.values.Const;
import elizagn.values.ValueFactory;

public class ExpressionParser {
    private int counter = 0;

    public IExpression parse(String expression) {
        expression = expression.replaceAll("\\s", "");
        counter = 0;
        return parseExpression(expression);
    } // удаляем пробелы

    private IExpression parseExpression(String expression) {
        var a = parseWithOperator(expression);

        while (counter < expression.length()) {
            var symbol = expression.charAt(counter);

            if (symbol == '+' || symbol == '-') {
                counter++;
                var b = parseWithOperator(expression);
                a = (symbol == '+') ? new Add(a, b) : new Substract(a, b);
            } else {
                break;
            }
        }

        return a;
    }

    private IExpression parseWithOperator(String expression) {
        var a = parseSymbol(expression);

        while (counter < expression.length()) {
            var symbol = expression.charAt(counter);

            if (symbol == '*' || symbol == '/') {
                counter++;
                var b = parseSymbol(expression);
                a = (symbol == '*') ? new Multiply(a, b) : new Divide(a, b);
            } else {
                break;
            }
        }

        return a;
    }

    private IExpression parseSymbol(String expression) {// разбираем символы
        var symbol = expression.charAt(counter);

        if (Character.isDigit(symbol)) {
            return parseDigit(expression);
        } else if (Character.isLetter(symbol)) {
            return parseVariable(expression);
        } else if (symbol == '-') { // отрицательное число
            counter++;

            var exp = parseSymbol(expression);
            return new Substract(new Const(0), exp);
        } else if (symbol == '(') {
            counter++; // '('
            var exp = parseExpression(expression);
            counter++; // ')'

            return exp;
        } else {
            throw new ArithmeticException(); // не предвиденный символ
        }
    }

    private IExpression parseDigit(String expression) { // числовые значения
        var builder = new StringBuilder();

        while (counter < expression.length() &&
                (Character.isDigit(expression.charAt(counter)) || expression.charAt(counter) == '.')) {
            builder.append(expression.charAt(counter));
            counter++;
        }

        return ValueFactory.create(builder.toString());
    }

    private IExpression parseVariable(String expression) { // переменные
        var builder = new StringBuilder();

        while (counter < expression.length() && Character.isLetter(expression.charAt(counter))) {
            builder.append(expression.charAt(counter));
            counter++;
        }

        var variable = builder.toString();

        return switch (variable) {
            case "E" -> new Const(Math.E);
            case "PI" -> new Const(Math.PI);
            case "PHI" -> new Const((1 + Math.sqrt(5)) / 2);
            case "pow" -> parseBinaryFunction(expression, variable);
            case "cos" -> parseBinaryFunction(expression, variable);
            default -> ValueFactory.create(builder.toString());
        };
    }

    private IExpression parseBinaryFunction(String expression, String functionName) { //для пов и кос
        counter++; // (
        var a = parseExpression(expression);
        counter++; // separator (',', '.', ';')
        var b = parseExpression(expression);
        counter++; // )

        return switch (functionName) {
            case "pow" -> new Pow(a, b);
            case "cos" -> new Cos(a);
            default -> throw new IllegalArgumentException("Неизвестная функция: " + functionName);
        };
    }
}