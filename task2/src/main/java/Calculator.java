import java.util.*;

abstract class Token {
    public String ch;
    public abstract String getCh();
    public abstract boolean isTokenBracket();
    public abstract boolean isNumber();
    public abstract boolean isBinaryOperation();
}

class TokenBracket extends Token {
    public String getCh() { return "("; }
    public boolean isTokenBracket() {
        return true;
    }
    public boolean isNumber() { return false; }
    public boolean isBinaryOperation() { return false; }
}

class TokenNumber extends Token {
    public double value;

    public double getValue() {
        return value;
    }
    public String getCh() { return String.valueOf(value); }
    public void setValue(double value) {
        this.value = value;
    }
    public boolean isTokenBracket() {
        return false;
    }
    public boolean isNumber() {
        return true;
    }
    public boolean isBinaryOperation() {
        return false;
    }
}

abstract class TokenBinaryOperation extends Token {
    public int priority;

    public abstract int getPriority();
    public abstract boolean isLeftAssociative();
    public abstract double performOperation(double a, double b);

    public boolean isNumber() {
        return false;
    }
    public boolean isBinaryOperation() {
        return true;
    }
}

class TokenBinaryPlus extends TokenBinaryOperation {
    public int getPriority() {
        return 1;
    }
    public String getCh() { return "+"; }
    public boolean isLeftAssociative() {
        return true;
    }
    public double performOperation(double a, double b) {
        return a + b;
    }
    public boolean isTokenBracket() {
        return false;
    }
}

class TokenBinaryMinus extends TokenBinaryOperation {
    public int getPriority() {
        return 1;
    }
    public String getCh() { return "-"; }
    public boolean isLeftAssociative() {
        return false;
    }
    public double performOperation(double a, double b) {
        return a - b;
    }
    public boolean isTokenBracket() {
        return false;
    }
}

class TokenBinaryMul extends TokenBinaryOperation {
    public int getPriority() {
        return 2;
    }
    public String getCh() { return "*"; }
    public boolean isLeftAssociative() {
        return true;
    }
    public double performOperation(double a, double b) {
        return a * b;
    }
    public boolean isTokenBracket() {
        return false;
    }
}

class TokenBinaryDiv extends TokenBinaryOperation {
    public int getPriority() {
        return 2;
    }
    public String getCh() { return "/"; }
    public boolean isLeftAssociative() {
        return false;
    }
    public double performOperation(double a, double b) {
        return a / b;
    }
    public boolean isTokenBracket() {
        return false;
    }
}

class TokenBinaryPow extends TokenBinaryOperation {
    public int getPriority() {
        return 3;
    }
    public String getCh() { return "pow"; }
    public boolean isLeftAssociative() {
        return false;
    }
    public double performOperation(double a, double b) {
        return Math.pow(a, b);
    }
    public boolean isTokenBracket() {
        return false;
    }
}

public class Calculator {
    public boolean wasError;

    Calculator() {
        wasError = false;
    }

    private String[] _parseExpression(String input) {
        return input.split(" ");
    }

    private void _processTokenBinaryOperation(Deque<Token> output_queue,
                                              Deque<Token> operator_stack,
                                              TokenBinaryOperation token_op) {
        while (!operator_stack.isEmpty() &&
                   !operator_stack.peekFirst().isTokenBracket()) {
            int op1_pr = token_op.getPriority();
            Token op2 = operator_stack.peekFirst();
            int op2_pr = ((TokenBinaryOperation) op2).getPriority();
            if (op1_pr < op2_pr ||
                    ((op1_pr == op2_pr) && token_op.isLeftAssociative())) {
                output_queue.add(op2);
                operator_stack.pop();
            } else {
                break;
            }
        }
        operator_stack.push(token_op);
    }

    private void _processTokenBracketClose(Deque<Token> output_queue,
                                           Deque<Token> operator_stack) {
        while (!operator_stack.isEmpty() &&
                !operator_stack.peekFirst().isTokenBracket()) {
            Token top_op = operator_stack.peekFirst();
            output_queue.add(top_op);
            operator_stack.pop();
        }
        if (!operator_stack.isEmpty() &&
                operator_stack.peekFirst().isTokenBracket()) {
            operator_stack.pop();
        } else {
            System.out.println("Incorrect input");
            wasError = true;
        }
    }

    private void _processRest(Deque<Token> output_queue,
                              Deque<Token> operator_stack) {
        while (!operator_stack.isEmpty()) {
            if (operator_stack.peekFirst().isTokenBracket()) {
                System.out.println("Incorrect input");
                wasError = true;
                break;
            }
            Token top_op = operator_stack.peekFirst();
            output_queue.add(top_op);
            operator_stack.pop();
        }
    }

    private void _printStruct(Deque<Token> struct) {
        int i = 0;
        for (Token elem : struct) {
            System.out.print(elem.getCh() + " ");
        }
        System.out.print('\n');
    }
    private Deque<Token> _sortingStation(String[] tokens) {
        Deque<Token> output_queue = new ArrayDeque<Token>();
        Deque<Token> operator_stack = new ArrayDeque<Token>();

        for (int n = 0; n < tokens.length; n++) {
            //_printStruct(output_queue);
            //_printStruct(operator_stack);

            String str = tokens[n];
            char ch = str.charAt(0);
            if (ch >= '0' && ch <= '9') {
                Token token_num = new TokenNumber();
                ((TokenNumber) token_num).setValue(Double.parseDouble(str));
                output_queue.add(token_num);
            } else if (ch == '+') {
                Token token_op = new TokenBinaryPlus();
                _processTokenBinaryOperation(
                        output_queue, operator_stack, (TokenBinaryOperation) token_op);
            } else if (ch == '-') {
                Token token_op = new TokenBinaryMinus();
                _processTokenBinaryOperation(
                        output_queue, operator_stack, (TokenBinaryOperation) token_op);
            } else if (ch == '*') {
                Token token_op = new TokenBinaryMul();
                _processTokenBinaryOperation(
                        output_queue, operator_stack, (TokenBinaryOperation) token_op);
            } else if (ch == '/') {
                Token token_op = new TokenBinaryDiv();
                _processTokenBinaryOperation(
                        output_queue, operator_stack, (TokenBinaryOperation) token_op);
            } else if (ch == '^') {
                Token token_op = new TokenBinaryPow();
                _processTokenBinaryOperation(
                        output_queue, operator_stack, (TokenBinaryOperation) token_op);
            } else if (ch == '(') {
                Token token_br = new TokenBracket();
                operator_stack.push(token_br);
            } else if (ch == ')') {
                _processTokenBracketClose(output_queue, operator_stack);
            }
        }
        _processRest(output_queue, operator_stack);

        return output_queue;
    }

    private double _stackCalculator(Deque<Token> output_queue) {
        Deque<Token> number_stack = new ArrayDeque<Token>();

        while (!output_queue.isEmpty()) {
            Token head = output_queue.peek();
            if (head.isNumber())
                number_stack.push(head);
            else if (head.isBinaryOperation()) {
                Token token = number_stack.pop();
                double b = ((TokenNumber) token).getValue();
                token = number_stack.pop();
                double a = ((TokenNumber) token).getValue();

                double c = ((TokenBinaryOperation) head).performOperation(a, b);
                Token token_num = new TokenNumber();
                ((TokenNumber) token_num).setValue(c);
                number_stack.push(token_num);
            }
            output_queue.pop();
        }

        if (number_stack.size() == 1) {
            return ((TokenNumber) number_stack.pop()).getValue();
        } else {
            wasError = true;
            System.out.println("Error in expression");
        }
        return 0;
    }

    public double processInput(String input) {
        String[] tokens = _parseExpression(input);
        Deque<Token> output_queue = _sortingStation(tokens);
        return _stackCalculator(output_queue);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter an expression to compute: ");
        String userInput = input.nextLine();

        Calculator calc = new Calculator();
        double ans = calc.processInput(userInput);

        if (!calc.wasError)
            System.out.println("The result is " + ans);
    }
}