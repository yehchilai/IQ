/*
This question is from https://leetcode.com/problems/search-a-2d-matrix-ii/description/
Difficulty: medium

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Example 1:

Input: matrix, target = 5
Output: true
Example 2:

Input: matrix, target = 20
Output: false
*/
// T: O(M+N), S: O(1), 15ms
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        // row length
        int r = matrix.length;
        if(r == 0) return false;

        // column length
        int c = matrix[0].length;

        // start point
        int row = r - 1;
        int column = 0;

        while(row >= 0 && column < c){
            if(matrix[row][column] > target){
                row--;
            }else if(matrix[row][column] < target){
                column++;
            }else{
                return true;
            }
        }
        return false;
    }
}