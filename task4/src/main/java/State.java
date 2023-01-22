import java.util.ArrayList;
import java.util.List;

public class State implements Comparable<State> {
    private final State _prevState;
    private final Board _currMatrix;
    private final int _priority;
    private final int _moves;
    private final int _manhattan;

    public State(State prev, Board currMatrix, int moves) {
        this._prevState = prev;
        this._currMatrix = currMatrix;
        this._manhattan = computeManhattan();
        this._moves = moves;
        this._priority = computePriority();
    }

    public int computePriority() {
        return Solver.a * _manhattan + Solver.b * _moves;
    }

    public int getPriority() {
        return _priority;
    }

    public int getMoves() {
        return _moves;
    }

    public State getPrevState() {
        return _prevState;
    }

    public Board getCurrMatrix() {
        return _currMatrix;
    }

    @Override
    public int compareTo(State rhs) {
        return Integer.compare(this.getPriority(), rhs.getPriority());
    }

    private int computeManhattanForCell(int val, int x, int y, int n) {
        if (val == 0) {
            return 0;
        }

        int tx = (val - 1) % n;
        int ty = (val - 1) / n;

        return Math.abs(x - tx) + Math.abs(y - ty);
    }

    public int computeManhattan() {
        int total = 0;
        int n = _currMatrix.getN();

        for (int x = 0; x < n; ++x)
            for (int y = 0; y < n; ++y)
                total += computeManhattanForCell(_currMatrix.getXY(x, y), x, y, n);

        return total;
    }

    public List<Board> getNeighbourStates() {
        List<Board> res = new ArrayList<Board>(4);

        if (_currMatrix.canMoveUp()) {
            Board movedUp = _currMatrix.copy();
            movedUp.moveUp();
            res.add(movedUp);
        }

        if (_currMatrix.canMoveDown()) {
            Board movedDown = _currMatrix.copy();
            movedDown.moveDown();
            res.add(movedDown);
        }

        if (_currMatrix.canMoveLeft()) {
            Board movedLeft = _currMatrix.copy();
            movedLeft.moveLeft();
            res.add(movedLeft);
        }

        if (_currMatrix.canMoveRight()) {
            Board movedRight = _currMatrix.copy();
            movedRight.moveRight();
            res.add(movedRight);
        }

        return res;
    }
}