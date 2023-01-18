import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CalculatorTest {
    @Test
    public void testCalculator1() {
        String input = "5 * ( 10 + 2 )";

        Calculator calc = new Calculator();
        double ans = calc.processInput(input);

        Assertions.assertFalse(calc.wasError);
        Assertions.assertEquals(60, ans);
    }

    @Test
    public void testCalculator2() {
        String input = "( 5 * 0 - 1 ) / 2 * 10";

        Calculator calc = new Calculator();
        double ans = calc.processInput(input);

        Assertions.assertFalse(calc.wasError);
        Assertions.assertEquals(-5, ans);
    }

    @Test
    public void testCalculator3() {
        String input = "7 - ( ( ( ( 8 - 1 ) * 2 + 5 / ( 3 + 2 ) ) ) ) / 3";

        Calculator calc = new Calculator();
        double ans = calc.processInput(input);

        Assertions.assertFalse(calc.wasError);
        Assertions.assertEquals(2, ans);
    }

    @Test
    public void testCalculator4() {
        String input = "( 3 + 1 ) * ( 4 / 2 ) ^ ( 2 * 2 )";

        Calculator calc = new Calculator();
        double ans = calc.processInput(input);

        Assertions.assertFalse(calc.wasError);
        Assertions.assertEquals(64, ans);
    }

    @Test
    public void testCalculator5() {
        String input = "2 ^ 2 ^ 3";

        Calculator calc = new Calculator();
        double ans = calc.processInput(input);

        Assertions.assertFalse(calc.wasError);
        Assertions.assertEquals(256, ans);
    }

    @Test
    public void testCalculator6() {
        String input = "( 10 + 2";

        Calculator calc = new Calculator();
        double ans = calc.processInput(input);

        Assertions.assertTrue(calc.wasError);
    }

    @Test
    public void testCalculator7() {
        String input = "7 - ( ( ( ( 8 - 1 ) * 2 + 5 / ( 3 + 2 ) ) ) / 3";

        Calculator calc = new Calculator();
        double ans = calc.processInput(input);

        Assertions.assertTrue(calc.wasError);
    }
}