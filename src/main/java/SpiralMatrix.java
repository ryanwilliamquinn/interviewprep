/**
 * Created by rquinn on 5/28/17.
 */
public class SpiralMatrix {
    private int[][] matrix;
    private int counter = 1;
    private static final int RIGHT = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int UP = 3;

    public SpiralMatrix(int matrixSize, int startX, int startY) {
        this.matrix = new int[matrixSize][matrixSize];

        Point start = new Point(startX, startY);

        matrix[start.getX()][start.getY()] = counter++;

        move(start, start, 0, RIGHT);

        // depending on our starting location, we need to pick if we are going to move clockwise or counterclockwise
        // assuming the matrix is square
        // if the matrix length is even, we have to figure out which way to go
        // if the matrix length is odd, we can choose either direction

        // start with odd for simplicity
        // move clockwise by default, directions are R D U L
    }

    private class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
           return x;
        }
        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Point moveRight() {
            return new Point(this.x + 1, this.y);
        }

        public Point moveDown() {
            return new Point(this.x, this.y - 1);
        }

        public Point moveLeft() {
            return new Point(this.x - 1, this.y);
        }

        public Point moveUp() {
            return new Point(this.x, this.y + 1);
        }
    }

    public boolean validateMove(Point point) {
        int x = point.getX();
        int y = point.getY();

        if (x >= matrix.length || y >= matrix[0].length) {
            return false;
        }

       return true;
    }

    public void updateCell(Point point) {
        matrix[point.getX()][point.getY()] = counter++;
    }

    public Point moveClockwise(Point currentPosition) {
        Point right = currentPosition.moveRight();
        if (validateMove(right)) {
            updateCell(right);
            return right;
        }

        Point down = currentPosition.moveDown();
        if (validateMove(down)) {
            updateCell(down);
            return down;
        }

        Point left = currentPosition.moveLeft();
        if (validateMove(left)) {
            updateCell(left);
            return left;
        }

        Point up = currentPosition.moveUp();
        if (validateMove(up)) {
            updateCell(up);
            return up;
        }

        return currentPosition;
    }

    public boolean finishedLayer(Point point) {
       return true;
    }


    public void move(Point center, Point current, int layer, int direction)  {
        if (finishedLayer(current)) {

        }
        // for the initial layer, we have already marked the center point, so we know we need to move clockwise
        if (layer == 0) {
            moveClockwise(current);

        } else {

        }
    }

    public void printMatrix() {
        for (int[] row : matrix) {
            for (int column : row) {
                System.out.println("the column " + column);
            }
        }
    }
}
