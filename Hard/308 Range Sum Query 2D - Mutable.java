/*
This question is from https://leetcode.com/problems/range-sum-query-2d-mutable/
Difficulty: hard

Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2.
*/
// T:O(n), S:O(m*n), 60 ms
class NumMatrix {

    int[][] sumMatrix;

    public NumMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return;
        int row = matrix.length;
        int col = matrix[0].length;
        sumMatrix = new int[row][col+1];

        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                sumMatrix[r][c+1] = sumMatrix[r][c] + matrix[r][c];
            }
        }
    }

    public void update(int row, int col, int val) {
        if(sumMatrix == null) return;
        // delta = original value - new value
        // for(int n : sumMatrix[row]) System.out.print(n+", ");
        // System.out.println();

        int delta = val - (sumMatrix[row][col + 1] - sumMatrix[row][col]);
        // System.out.println(delta);
        for(int c = col + 1 ; c < sumMatrix[row].length; c++){
            sumMatrix[row][c] = sumMatrix[row][c] + delta;
        }

        // for(int n : sumMatrix[row]) System.out.print(n+", ");
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(sumMatrix == null) return 0;
        int sum = 0;
        for(int r = row1; r <= row2; r++){
            sum += sumMatrix[r][col2 + 1] - sumMatrix[r][col1];
        }
        return sum;
    }
}

// T:O(m*n), S:O(1), 86 ms
class NumMatrix {

    int[][] matrix;
    int row;
    int col;

    public NumMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return;
        this.matrix = matrix;
        row = matrix.length;
        col = matrix[0].length;
    }

    public void update(int row, int col, int val) {
        if(matrix == null) return;
        matrix[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(matrix == null) return 0;
        int sum = 0;
        for(int r = row1; r <= row2; r++){
            for(int c = col1; c <= col2; c++){
                sum += matrix[r][c];
            }
        }
        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */