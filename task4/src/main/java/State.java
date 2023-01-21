import java.util.LinkedList;

public class State {
    private final int _manhattan;
    private final Board _currMatrix;
    private final Board _solutionMatrix;

    public State(Board curr) {
        this._currMatrix = curr.copy();
        this._solutionMatrix = new Board(curr.getN());
        this._manhattan = countManhattan();
    }
    public int getManhattan() {
        return _manhattan;
    }
    public Board getCurrMatrix() {
        return _currMatrix;
    }
    public boolean isVictory() {
        return _currMatrix.isEqual(_solutionMatrix);
    }
    public boolean isEqual(State rhs) {
        return (rhs != null) &&
                _currMatrix.isEqual(rhs.getCurrMatrix());
    }
    private int countManhattanForCell(int val, int x, int y, int n) {
        int tx = (val == 0) ? (n - 1) : ((val - 1) % n);
        int ty = (val == 0) ? (n - 1) : ((val - 1) / n);

        return Math.abs(x - tx) + Math.abs(y - ty);
    }
    public int countManhattan() {
        int total = 0;
        int n = _solutionMatrix.getN();

        for (int x = 0; x < n; ++x)
            for (int y = 0; y < n; ++y)
                total += countManhattanForCell(_currMatrix.getXY(x, y), x, y, n);

        return total;
    }
    public LinkedList<State> getNextStates() {
        LinkedList<State> res = new LinkedList<State>();

        if (_currMatrix.canMoveRight()) {
            Board movedRight = _currMatrix.copy();
            movedRight.moveRight();
            State state = new State(movedRight);
            res.addLast(state);
        }

        if (_currMatrix.canMoveUp()) {
            Board movedUp = _currMatrix.copy();
            movedUp.moveUp();
            State state = new State(movedUp);
            res.addLast(state);
        }

        if (_currMatrix.canMoveLeft()) {
            Board movedLeft = _currMatrix.copy();
            movedLeft.moveLeft();
            State state = new State(movedLeft);
            res.addLast(state);
        }

        if (_currMatrix.canMoveDown()) {
            Board movedDown = _currMatrix.copy();
            movedDown.moveDown();
            State state = new State(movedDown);
            res.addLast(state);
        }

        return res;
    }
}