/*
This question is from https://leetcode.com/problems/range-sum-query-2d-immutable/
Difficulty: medium

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
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.
*/
// T:O(M or N), S:O(M*N), 57 ms
class NumMatrix {

    int[][] dp;
    public NumMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return;
        int rows = matrix.length;
        int cols = matrix[0].length;
        dp = new int[rows][cols + 1];
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                dp[r][c + 1] = dp[r][c] + matrix[r][c];
            }
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for(int r = row1; r <= row2; r++){
            sum = sum + dp[r][col2 + 1] - dp[r][col1];
        }

        return sum;
    }
}

// T:O(M*N), S:O(1), 261 ms
class NumMatrix {

    int[][] matrix;

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;

        for(int r = row1; r <= row2; r++){
            for(int c = col1; c <= col2; c++){
                sum = sum + matrix[r][c];
            }
        }

        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */