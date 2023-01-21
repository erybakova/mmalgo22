import java.util.*;
import java.util.stream.Collectors;

public class Solver {
    private class AdjacentStates implements Comparable<AdjacentStates> {
        public State currState, prevState;
        public int moves;

        public AdjacentStates(State prev, State curr, int steps) {
            this.prevState = prev;
            this.currState = curr;
            this.moves = steps;
        }
        @Override
        public int compareTo(AdjacentStates rhs) {
            int pr1 = _a * currState.getManhattan() + _b * moves;
            int pr2 = _a * rhs.currState.getManhattan() + _b * rhs.moves;

            return Integer.compare(pr1, pr2);
        }
    }
    private int _a, _b;
    private AdjacentStates _initialState;
    private Set<AdjacentStates> _explored;
    private PriorityQueue<AdjacentStates> _queueOfStates;
    public boolean _isVictory;

    Solver(Board matrix) {
        _initialState = new AdjacentStates(null, new State(matrix), 0);
        _explored = new HashSet<AdjacentStates>();
        _queueOfStates = new PriorityQueue<AdjacentStates>();
        _isVictory = false;
    }
    public void setPriorityParams(int a, int b) {
        this._a = a;
        this._b = b;
    }
    public boolean isVictory() {
        return _isVictory;
    }
    private LinkedList<AdjacentStates> getNeighbours(AdjacentStates statesPair) {
        LinkedList<AdjacentStates> neighbours = new LinkedList<AdjacentStates>();

        int steps = statesPair.moves;
        State prev = statesPair.prevState;
        State cur = statesPair.currState;
        LinkedList<State> nextStates = cur.getNextStates();

        State next;
        while ((next = nextStates.pollFirst()) != null)
            if (!next.isEqual(prev))
                neighbours.addFirst(new AdjacentStates(cur, next, steps + 1));

        return neighbours;
    }
    public int solve() {
        _queueOfStates.clear();
        _queueOfStates.add(_initialState);
        AdjacentStates adjacentStates = new AdjacentStates(null, null, 0);

        while (true) {
            adjacentStates = _queueOfStates.poll();

            assert adjacentStates != null;
            if (adjacentStates.currState.isVictory()) {
                _isVictory = true;
                break;
            }

            _explored.add(adjacentStates);

            LinkedList<AdjacentStates> neighbours = getNeighbours(adjacentStates);
            neighbours.stream()
                    .filter(e -> !_explored.contains(e))
                    .forEach(e -> _queueOfStates.add(e));
        }

        return adjacentStates.moves;
    }
    public static void main(String[] args) {
        int n = 4;

        Board matrixInit = new Board(n);
        matrixInit.setRandom(123);
        matrixInit.print();

        Solver solver = new Solver(matrixInit);

        for (int a = 1; a <= 10; ++a) {
            for (int b = 1; b <= 10; ++b) {
                solver.setPriorityParams(a, b);
                System.out.printf("a = " + a + ", b = " + b + ": ");
                int moves = solver.solve();
                System.out.printf("solved in " + moves + " moves\n");
            }
        }
    }
}