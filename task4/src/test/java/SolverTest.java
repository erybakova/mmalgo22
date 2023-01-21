import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class SolverTest {
    @Test
    public void solverTest1() {
        int n = 4;
        int[] matr = {  1,  6,  2,  3,
                        9,  5,  8,  7,
                        0, 10, 12,  4,
                       13, 14, 11, 15 };

        Board matrixInit = new Board(matr, n);
        Solver solver = new Solver(matrixInit);

        for (int a = 1; a <= 10; ++a) {
            for (int b = 1; b <= 10; ++b) {
                solver.setPriorityParams(a, b);
                System.out.printf("a = " + a + ", b = " + b + ": ");
                int moves = solver.solve();
                Assertions.assertTrue(solver.isVictory());
                System.out.printf("solved in " + moves + " moves\n");
            }
        }
    }

    @Test
    public void solverTest2() {
        int n = 4;
        int[] matr = {  1, 2,  4,  7,
                        9, 5, 12,  3,
                       13, 0, 10,  8,
                       14, 6, 11, 15 };

        Board matrixInit = new Board(matr, n);
        Solver solver = new Solver(matrixInit);

        for (int a = 1; a <= 10; ++a) {
            for (int b = 1; b <= 10; ++b) {
                solver.setPriorityParams(a, b);
                System.out.printf("a = " + a + ", b = " + b + ": ");
                int moves = solver.solve();
                Assertions.assertTrue(solver.isVictory());
                System.out.printf("solved in " + moves + " moves\n");
            }
        }
    }
}