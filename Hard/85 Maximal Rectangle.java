/**
This question is from https://leetcode.com/problems/maximal-rectangle/
Difficulty: hard

Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
*/

// T:O(r*c), S:O(c), 4 ms (97.02%)
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] height = new int[cols];
        int[] left = new int[cols];
        int[] right = new int[cols];
        Arrays.fill(right, cols - 1);
        int max = 0;
        for(int r = 0; r < rows; r++){
            // update right
            int rightMost = cols - 1;
            for(int c = cols - 1; c >= 0; c--){
                if(matrix[r][c] == '1'){
                    right[c] = Math.min(right[c], rightMost);
                }else{
                    right[c] = cols - 1;
                    rightMost = c - 1;
                }
            }

            // update left and area
            int leftMost = 0;
            for(int c = 0; c < cols; c++){
                if(matrix[r][c] == '1'){
                    left[c] = Math.max(left[c], leftMost);
                    height[c]++;

                    max = Math.max(max, height[c] * (right[c] - left[c] + 1));
                }else{
                    left[c] = 0;
                    height[c] = 0;
                    leftMost = c + 1;
                }
            }
        }
        // System.out.println(Arrays.toString(left));
        // System.out.println(Arrays.toString(right));
        // System.out.println(Arrays.toString(height));
        return max;
    }
}
