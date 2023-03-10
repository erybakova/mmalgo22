import java.util.*;

public class Calculator {
    public boolean wasError;

    Calculator() {
        wasError = false;
    }

    private String[] _parseExpression(String input) {
        return input.split(" ");
    }

    private void _processTokenBinaryOperation(Deque<Token> outputQueue,
                                              Deque<Token> operatorStack,
                                              TokenBinaryOperation tokenOp) {
        while (!operatorStack.isEmpty() &&
                   !operatorStack.peekFirst().isTokenBracket()) {
            int op1Pr = tokenOp.getPriority();
            Token op2 = operatorStack.peekFirst();
            int op2Pr = ((TokenBinaryOperation) op2).getPriority();
            if (op1Pr < op2Pr ||
                    ((op1Pr == op2Pr) && tokenOp.isLeftAssociative())) {
                outputQueue.add(op2);
                operatorStack.pop();
            } else {
                break;
            }
        }
        operatorStack.push(tokenOp);
    }

    private void _processTokenBracketClose(Deque<Token> outputQueue,
                                           Deque<Token> operatorStack) {
        while (!operatorStack.isEmpty() &&
                !operatorStack.peekFirst().isTokenBracket()) {
            Token topOp = operatorStack.peekFirst();
            outputQueue.add(topOp);
            operatorStack.pop();
        }
        if (!operatorStack.isEmpty() &&
                operatorStack.peekFirst().isTokenBracket()) {
            operatorStack.pop();
        } else {
            System.out.println("Incorrect input");
            wasError = true;
        }
    }

    private void _processRest(Deque<Token> outputQueue,
                              Deque<Token> operatorStack) {
        while (!operatorStack.isEmpty()) {
            if (operatorStack.peekFirst().isTokenBracket()) {
                System.out.println("Incorrect input");
                wasError = true;
                break;
            }
            Token topOp = operatorStack.peekFirst();
            outputQueue.add(topOp);
            operatorStack.pop();
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
        Deque<Token> outputQueue = new ArrayDeque<Token>();
        Deque<Token> operatorStack = new ArrayDeque<Token>();

        for (int n = 0; n < tokens.length; n++) {
            //_printStruct(outputQueue);
            //_printStruct(operatorStack);

            String str = tokens[n];
            char ch = str.charAt(0);
            if (ch >= '0' && ch <= '9') {
                TokenNumber tokenNum = new TokenNumber();
                tokenNum.setValue(Double.parseDouble(str));
                outputQueue.add(tokenNum);
            } else if (ch == '+') {
                Token tokenOp = new TokenBinaryPlus();
                _processTokenBinaryOperation(
                        outputQueue, operatorStack, (TokenBinaryOperation) tokenOp);
            } else if (ch == '-') {
                Token tokenOp = new TokenBinaryMinus();
                _processTokenBinaryOperation(
                        outputQueue, operatorStack, (TokenBinaryOperation) tokenOp);
            } else if (ch == '*') {
                Token tokenOp = new TokenBinaryMul();
                _processTokenBinaryOperation(
                        outputQueue, operatorStack, (TokenBinaryOperation) tokenOp);
            } else if (ch == '/') {
                Token tokenOp = new TokenBinaryDiv();
                _processTokenBinaryOperation(
                        outputQueue, operatorStack, (TokenBinaryOperation) tokenOp);
            } else if (ch == '^') {
                Token tokenOp = new TokenBinaryPow();
                _processTokenBinaryOperation(
                        outputQueue, operatorStack, (TokenBinaryOperation) tokenOp);
            } else if (ch == '(') {
                Token tokenBr = new TokenBracket();
                operatorStack.push(tokenBr);
            } else if (ch == ')') {
                _processTokenBracketClose(outputQueue, operatorStack);
            }
        }
        _processRest(outputQueue, operatorStack);

        return outputQueue;
    }

    private double _stackCalculator(Deque<Token> outputQueue) {
        Deque<Token> numberStack = new ArrayDeque<Token>();

        while (!outputQueue.isEmpty()) {
            Token head = outputQueue.peek();
            if (head.isNumber())
                numberStack.push(head);
            else if (head instanceof TokenBinaryOperation binOp) {
                Token token = numberStack.pop();
                double b = ((TokenNumber) token).getValue();
                token = numberStack.pop();
                double a = ((TokenNumber) token).getValue();

                double c = binOp.performOperation(a, b);
                Token tokenNum = new TokenNumber();
                ((TokenNumber) tokenNum).setValue(c);
                numberStack.push(tokenNum);
            }
            outputQueue.pop();
        }

        if (numberStack.size() == 1) {
            return ((TokenNumber) numberStack.pop()).getValue();
        } else {
            wasError = true;
            System.out.println("Error in expression");
        }
        return 0;
    }

    public double processInput(String input) {
        String[] tokens = _parseExpression(input);
        Deque<Token> outputQueue = _sortingStation(tokens);
        return _stackCalculator(outputQueue);
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