import java.util.*;

public class Board {
    private final int _n;
    private int[] _matrix;
    private int _xFrom, _yFrom;

    Board(int n) {
        this._n = n;

        this._matrix = new int[n * n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                _matrix[i * n + j] = i * n + j + 1;
        _matrix[n * n - 1] = 0;

        _xFrom = n - 1;
        _yFrom = n - 1;
    }

    Board(int[] matrix, int n) {
        this._n = n;

        this._matrix = new int[matrix.length];
        for (int i = 0; i < _n; ++i)
            System.arraycopy(matrix, i * _n, this._matrix, i * _n, _n);

        for (int i = 0; i < _n; ++i)
            for (int j = 0; j < _n; ++j)
                if (matrix[i * _n + j] == 0) {
                    _xFrom = j;
                    _yFrom = i;
                }
    }

    public int getN() {
        return _n;
    }

    public Board copy() {
        return new Board(_matrix, _n);
    }

    public int getXY(int x, int y) {
        return _matrix[y * _n + x];
    }
    public boolean canMoveRight() {
        return _xFrom != _n - 1;
    }
    public boolean canMoveUp() {
        return _yFrom != 0;
    }
    public boolean canMoveLeft() {
        return _xFrom != 0;
    }
    public boolean canMoveDown() {
        return _yFrom != _n - 1;
    }
    private void swap(int x1, int y1, int x2, int y2) {
        int t = _matrix[y1 * _n + x1];
        _matrix[y1 * _n + x1] = _matrix[y2 * _n + x2];
        _matrix[y2 * _n + x2] = t;
    }
    public void moveRight() {
        if (canMoveRight()) {
            swap(_xFrom, _yFrom, _xFrom + 1, _yFrom);
            _xFrom += 1;
        }
    }
    public void moveUp() {
        if (canMoveUp()) {
            swap(_xFrom, _yFrom, _xFrom, _yFrom - 1);
            _yFrom -= 1;
        }
    }
    public void moveLeft() {
        if (canMoveLeft()) {
            swap(_xFrom, _yFrom, _xFrom - 1, _yFrom);
            _xFrom -= 1;
        }
    }
    public void moveDown() {
        if (canMoveDown()) {
            swap(_xFrom, _yFrom, _xFrom, _yFrom + 1);
            _yFrom += 1;
        }
    }
    public boolean isEqual(Board rhs) {
        for (int i = 0; i < _n; ++i)
            for (int j = 0; j < _n; ++j)
                if (_matrix[i * _n + j] != rhs._matrix[i * _n + j])
                    return false;
        return true;
    }
    public void setRandom(int random_state) {
        Random random = new Random();

        for (int i = 0; i < random_state; i++)
            move(random.nextInt(4));
    }
    public void move(int dir) {
        switch (dir % 4) {
            case 0 -> moveRight();
            case 1 -> moveUp();
            case 2 -> moveLeft();
            case 3 -> moveDown();
        }
    }
    public void print() {
        for (int i = 0; i < _n; ++i) {
            for (int j = 0; j < _n; ++j) {
                System.out.printf(_matrix[i * _n + j] + " ");
            }
            System.out.print("\n");
        }
    }
}