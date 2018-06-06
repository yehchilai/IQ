/*
This question is from https://leetcode.com/problems/set-matrix-zeroes/description/
Difficulty: medium

Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place(https://en.wikipedia.org/wiki/In-place_algorithm).

Example 1:

Input:
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output:
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
Example 2:

Input:
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output:
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
Follow up:

A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
*/

// T: O(M*N), S:O(1), 2ms
class Solution {
    public void setZeroes(int[][] matrix) {
        // anchor row index
        int anchorRow = -1;

        // edge case
        if(matrix.length == 0) return;

        // rows
        int rows = matrix.length;
        // columns
        int columns = matrix[0].length;

        // find ancher column and replace the interger to 0
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < columns; c++){
                // set up the ancher column
                if(anchorRow == -1 && matrix[r][c] == 0){
                    anchorRow = r;
                }

                // set maker on the ancher row and colum
                if(matrix[r][c] == 0){
                    // mark anchor row with this column to 0
                    matrix[anchorRow][c] = 0;
                }
            }
        }

        // edge case : there is no 0 in the matrix
        if(anchorRow == -1) return;

        // skip ancher row and replace the row elements with 0 if there is a 0 in the row
        for(int r = 0; r < rows; r++){
            // skip the anchor row
            if(r != anchorRow){
                for(int c = 0; c < columns; c++){
                    // if there is a 0 in this row, replace all elements to 0 in this row
                    if(matrix[r][c] == 0){
                        for(int i = 0; i < columns; i ++){
                            matrix[r][i] = 0;
                        }
                        break;
                    }
                }
            }
        }

        // use anchor row to check the columns
        for(int c = 0; c < columns; c++){
            // if the element in the anchor row is 0, relpce all elements in the column to 0
            if(matrix[anchorRow][c] == 0){
                for(int r=0; r< rows; r++){
                    matrix[r][c] = 0;
                }
            }
        }

        // replace all element to 0 in the anchor row
        for(int c= 0; c < columns; c++){
            matrix[anchorRow][c] = 0;
        }
    }
}