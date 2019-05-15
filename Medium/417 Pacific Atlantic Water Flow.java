/*
This question is from https://leetcode.com/problems/pacific-atlantic-water-flow/
Difficulty: medium

Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

*/

// T:O(M*N), S:O(M*N), 3ms
class Solution {

    List<int[]> ans;
    int[][] visited;
    int[][] direction;
    int row;
    int col;
    int[][] graph;

    public List<int[]> pacificAtlantic(int[][] matrix) {
        ans = new LinkedList();
        if(matrix == null || matrix.length == 0) return ans;
        graph = matrix;
        row = matrix.length;
        col = matrix[0].length;
        visited = new int[row][col];

        direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for(int r = 0; r < row; r++){
            dfs(r, 0, 1); // Pacific
            dfs(r, col - 1, -1); // Atlantic
        }

        for(int c = 0; c < col; c++){
            dfs(0, c, 1); // Pacific
            dfs(row - 1, c, -1); // Atlantic
        }

        return ans;
    }

    private void dfs(int r, int c, int ocean){
        if(visited[r][c] + ocean == 0){
            ans.add(new int[]{r, c});
            visited[r][c] = 2;
        }else if(visited[r][c] != 2){
            visited[r][c] = ocean;
        }

        for(int i = 0; i < direction.length; i++){
            int newR = r + direction[i][0];
            int newC = c + direction[i][1];

            if(newR >= 0 && newR < row && newC >=0 && newC < col
               && visited[newR][newC] != 2
               && visited[newR][newC] != ocean
               && (graph[r][c] <= graph[newR][newC]))
                dfs(newR, newC, ocean);
        }

    }
}