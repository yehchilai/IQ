/*
This question is from https://leetcode.com/problems/rotate-image/description/
Difficulty: medium

You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place(https://en.wikipedia.org/wiki/In-place_algorithm), which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Given input matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
Example 2:

Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
],

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
*/
// T: O(N/2), S:(1), 2ms
class Solution {
    public void rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        // initial rows
        int rows = matrix.length - 1;
        // initial columns
        int columns = matrix[0].length - 1;
        // start row
        int sr = 0;
        // start column
        int sc = 0;

        while(sr < rows){
            // total rotate times for this square
            int length = rows - sr;
            for(int i= 0 ;i < length; i++){
                // tem is left-top
                int tmp = matrix[sr + i][sc];
                // left-top = left-bottom
                matrix[sr + i][sc] = matrix[rows][sc + i];
                //System.out.println((sr + i) + "," +(sc)+ " = "+ (rows)+","+(sc + i) +" = "+ matrix[sr + i][sc]);
                // left-bottom = right-bottom
                matrix[rows][sc + i] = matrix[rows - i][columns ];
                //System.out.println((rows) + "," +(sc + i)+ " = "+ (rows - i)+","+(columns) +" = "+ matrix[rows][sc + i] );
                // right-bottom = right-top
                matrix[rows - i][columns] = matrix[sr][columns - i];
                //System.out.println((rows - i) + "," +(columns)+ " = "+ (sr)+","+(columns - i) +" = "+ matrix[rows - i][columns]);
                // right-top = left-top
                matrix[sr][columns - i] = tmp;
                //System.out.println((sr) + "," +(columns - i)+ " = "+ (sr + i)+","+(sc) +" = "+ matrix[sr][columns - i]);

            }
            sr++;
            sc++;
            rows--;
            columns--;
            //System.out.println("sr: "+sr+", rows: "+rows);
        }

    }
}