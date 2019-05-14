/*
This question is from https://leetcode.com/problems/diagonal-traverse/
Difficulty: medium

Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.



Example:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

Output:  [1,2,4,7,5,3,6,8,9]

Explanation:



Note:

The total number of elements of the given matrix will not exceed 10,000.
*/
// T:O(M * N), S:O(M * N), 2 ms
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return new int[0];
        int row = matrix.length;
        int col = matrix[0].length;
        int[] ans = new int[row*col];
        int r = 0;
        int c = 0;

        for(int i = 0; i < ans.length; i++){
            ans[i] = matrix[r][c];

            if((r + c) % 2 == 0){ // going up
                if(c == col - 1) {r++;}
                else if(r == 0) {c++;}
                else {r--; c++;}
            }else{ // going down
                if(r == row - 1) {c++;}
                else if(c == 0) {r++;}
                else {r++; c--;}
            }
        }

        return ans;
    }
}