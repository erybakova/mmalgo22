import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Stack;

public class SolverTest {
    @Test
    public void solverTest1() {
        int n = 4;
        int[] matr = {  1,  6,  2,  3,
                        9,  5,  8,  7,
                        0, 10, 12,  4,
                       13, 14, 11, 15 };

        Board matrixInit = new Board(matr, n);
        Board solutionMatrix = new Board(n, true);
        Solver solver = new Solver(matrixInit, solutionMatrix);

        matrixInit.print();

        for (int a = 1; a <= 10; ++a) {
            for (int b = 1; b <= 10; ++b) {
                solver.setPriorityParams(a, b);
                System.out.printf("a = " + a + ", b = " + b + ": ");
                Assertions.assertTrue(solver.solve());
                System.out.print("Solved in ");
                Stack<Board> pth = solver.retracePath();
                System.out.print(pth.size() + " steps\n");
            }
        }
    }

    @Test
    public void solverTest2() {
        int n = 3;
        int[] matr = {  1, 2, 3,
                        4, 5, 6,
                        8, 7, 0 };

        Board matrixInit = new Board(matr, n);
        Board solutionMatrix = new Board(n, true);
        Solver solver = new Solver(matrixInit, solutionMatrix);

        matrixInit.print();

        for (int a = 1; a <= 10; ++a) {
            for (int b = 1; b <= 10; ++b) {
                solver.setPriorityParams(a, b);
                Assertions.assertFalse(solver.solve());
            }
        }
    }
}