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