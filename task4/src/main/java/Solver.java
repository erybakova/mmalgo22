import java.util.*;

public class Solver {
    public static int a, b;
    public final Board solutionMatrix;
    private final State _initialState;
    private State _finalState;
    private Set<Board> _explored;
    private PriorityQueue<State> _queueOfStates;
    public boolean _isVictory;

    Solver(Board matrix, Board solutionMatrix) {
        this._initialState = new State(null, matrix, 0);
        this._explored = new HashSet<Board>();
        this._queueOfStates = new PriorityQueue<State>();
        this._isVictory = false;
        this.solutionMatrix = new Board(matrix.getN(), true);
        this._finalState = null;
    }

    public void setPriorityParams(int a, int b) {
        Solver.a = a;
        Solver.b = b;
    }

    public boolean isVictory() {
        return _isVictory;
    }

    public Stack<Board> retracePath() {
        if (!isVictory())
            return null;

        Stack<Board> stackSolution = new Stack<>();
        State current = _finalState;
        while (current.getPrevState() != null) {
            stackSolution.push(current.getCurrMatrix());
            current = current.getPrevState();
        }
        //stackSolution.push(_initialState.getCurrMatrix());
        return stackSolution;
    }

    public boolean solve() {
        _isVictory = false;

        _queueOfStates.add(_initialState);

        while (!_queueOfStates.isEmpty()) {
            State st = _queueOfStates.peek();

            if (st.getCurrMatrix().equals(solutionMatrix)) {
                _isVictory = true;
                _finalState = st;
                break;
            }

            st = _queueOfStates.poll();
            assert st != null;
            _explored.add(st.getCurrMatrix());
            List<Board> neighbours = st.getNeighbourStates();
            State finalSt = st;
            neighbours.stream()
                    .filter(e -> !_explored.contains(e))
                    .forEach(e -> _queueOfStates.add(new State(finalSt, e, finalSt.getMoves() + 1)));
        }

        _queueOfStates.clear();
        _explored.clear();

        return _isVictory;
    }

    public static void main(String[] args) {
        int n = 4;

        /*Board matrixInit = new Board(n, false);
        matrixInit.setRandom(123);*/
        int[] m = {  1,  6,  2,  3,
                     9,  5,  8,  7,
                     0, 10, 12,  4,
                    13, 14, 11, 15 };
        Board matrixInit = new Board(m, n);
        Board solutionMatrix = new Board(n, true);

        matrixInit.print();
        Solver solver = new Solver(matrixInit, solutionMatrix);

        for (int a = 1; a <= 10; ++a) {
            for (int b = 1; b <= 10; ++b) {
                solver.setPriorityParams(a, b);
                System.out.printf("a = " + a + ", b = " + b + ": ");
                boolean solved = solver.solve();
                if (solved) {
                    System.out.print("Solved in ");
                    Stack<Board> pth = solver.retracePath();
                    System.out.print(pth.size() + " steps\n");
                } else
                    System.out.print("Not solved\n");
            }
        }
    }
}