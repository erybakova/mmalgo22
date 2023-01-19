abstract class Token {
    public String ch;
    public abstract String getCh();
    public abstract boolean isTokenBracket();
    public abstract boolean isNumber();
    public abstract boolean isBinaryOperation();
}