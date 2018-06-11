/*
This question is from https://leetcode.com/problems/image-smoother/description/
Difficulty: easy

Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.

Example 1:
Input:
[[1,1,1],
 [1,0,1],
 [1,1,1]]
Output:
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]
Explanation:
For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
For the point (1,1): floor(8/9) = floor(0.88888889) = 0
Note:
The value in the given matrix is in the range of [0, 255].
The length and width of the given matrix are in the range of [1, 150].

*/
// T: O(N*M), S:O(N*M), 38ms
class Solution {
    public int[][] imageSmoother(int[][] M) {
        if((M.length == 0) || (M[0].length == 0)) return M;

        int rows = M.length;
        int columns = M[0].length;

        int[][] smooth = new int[rows][columns];

        for(int r = 0; r < rows; r++){
            for(int c = 0; c < columns; c++){
                int sum = 0;
                int count = 0;
                for(int i = -1; i <= 1; i++){
                    for(int j = -1; j <= 1; j++){
                        if( ((r + i) >= 0) && ((r + i) < rows) && ((c + j) >= 0) && ((c + j) < columns)){
                            sum += M[r + i][c + j];
                            count++;
                        }
                    }
                }
                smooth[r][c] = (int)(sum/count);
            }
        }

        return smooth;
    }
}