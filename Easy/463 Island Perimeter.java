/*
This question is from https://leetcode.com/problems/island-perimeter/
Difficulty: easy

You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.



Example:

Input:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Output: 16

Explanation: The perimeter is the 16 yellow stripes in the image below:


*/
// Optional without dfs
class Solution {
    public int islandPerimeter(int[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    if(i > 0 && grid[i-1][j] == 1) count-=2;
                    if(j > 0 && grid[i][j-1] == 1) count-=2;
                    count+=4;
                }
            }
        }
        return count;
    }
}

// T:O(MN), S:O(MN), 13 ms
class Solution {

    boolean[][] visited;
    int[][] g;
    int perimeter = 0;

    public int islandPerimeter(int[][] grid) {
        if(grid == null || grid.length == 0)return 0;
        int r = grid.length;
        int c = grid[0].length;
        g = grid;
        visited = new boolean[r][c];


        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(!visited[i][j] && grid[i][j] == 1){
                    dfs(i, j, r, c);
                }

            }
        }

        return perimeter;
    }

    public void dfs(int row, int col, int r, int c){
        if(visited[row][col] || g[row][col] == 0) return;

        visited[row][col] = true;
        int currentPerimeter = 4;
        // System.out.println(row+", "+col+": "+currentPerimeter);
        // right
        if(row + 1 < r && g[row + 1][col] == 1) {
            currentPerimeter--;
            dfs(row + 1, col, r, c);
        }
        // left
        if(row - 1 >= 0 && g[row - 1][col] == 1){
            currentPerimeter--;
            dfs(row - 1, col, r, c);
        }
        // up
        if(col + 1 < c && g[row][col + 1] == 1){
            currentPerimeter--;
            dfs(row, col + 1, r, c);
        }
        // down
        if(col - 1 >= 0 && g[row][col - 1] == 1){
            currentPerimeter--;
            dfs(row, col - 1, r, c);
        }

        // System.out.println(col+", "+row+": "+currentPerimeter);
        perimeter += currentPerimeter;
    }

}