/**
This question is from https://leetcode.com/problems/max-area-of-island/
Difficulty: medium

Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:

[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.


*/

// T:O(N), S:O(1), 3 ms (53.81%)
class Solution {

    int rows;
    int cols;
    int[][] grid;
    int max;
    int[][] directions;

    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.max = 0;

        directions = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};

        for(int r = 0; r < rows ; r++){
            for(int c = 0; c < cols; c++){

                if(grid[r][c] == 1) max = Math.max(max, dfs(r,c));
            }
        }

        return this.max;
    }

    private int dfs(int r, int c){
        grid[r][c] = 0;

        int sum = 1;

        for(int i = 0 ; i < directions.length; i++){
            int nextR = r + directions[i][0];
            int nextC = c + directions[i][1];

            if(nextR < rows && nextC < cols && nextR >= 0 && nextC >= 0 && grid[nextR][nextC] == 1){
                sum += dfs(nextR, nextC);
            }
        }

        return sum;
    }
}