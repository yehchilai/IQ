/**
This question is from https://leetcode.com/problems/minimum-path-sum/
Difficulty: medium

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
*/

// T:O(M*N), S:O(1), 2 ms (90.07%)
class Solution {
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;


        for(int r = rows - 1; r >= 0; r--){
            for(int c = cols - 1; c >= 0; c--){
                int right = c + 1 < cols? grid[r][c + 1] : Integer.MAX_VALUE;
                int bottom = r + 1 < rows? grid[r + 1][c] : Integer.MAX_VALUE;

                int min = Math.min(right, bottom);

                if(min != Integer.MAX_VALUE) grid[r][c] = grid[r][c] + min;
            }
        }

        return grid[0][0];
    }
}


// T:O(M*N), S:O(M*N), 3 ms (15.12%)
class Solution {
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;

        int[][] dp = new int[rows][cols];
        dp[rows - 1][cols - 1] = grid[rows - 1][cols - 1];

        for(int r = rows - 1; r >= 0; r--){
            for(int c = cols - 1; c >= 0; c--){
                int right = c + 1 < cols? dp[r][c + 1] : Integer.MAX_VALUE;
                int bottom = r + 1 < rows? dp[r + 1][c] : Integer.MAX_VALUE;

                int min = Math.min(right, bottom);

                if(min != Integer.MAX_VALUE) dp[r][c] = grid[r][c] + min;
            }
        }

        return dp[0][0];
    }
}

// T:O(NlogN), S:O(N), 10 ms (5.11%)
class Solution {
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] directions = new int[][]{{0,1}, {1,0}};
        boolean[][] visited = new boolean[rows][cols];
        PriorityQueue<Node> pq = new PriorityQueue();
        pq.add(new Node(0, 0, grid[0][0]));
        visited[0][0] = true;

        while(pq.size() > 0){
            Node node = pq.poll();
            // System.out.println(node.row+", "+node.col);
            if(node.row == rows - 1 && node.col == cols - 1) return node.sum ;

            for(int i = 0; i < directions.length; i++){
                int r = node.row + directions[i][0];
                int c = node.col + directions[i][1];
                // System.out.println("   - "+r+", "+c);
                if(r < rows && r >= 0 && c < cols && c >= 0 && !visited[r][c]){
                    visited[r][c] = true;
                    pq.add(new Node(r, c, node.sum + grid[r][c]));
                }
            }
        }

        return 0;
    }
}

class Node implements Comparable<Node>{

    int row;
    int col;
    int sum;

    public Node(int r, int c, int sum){
        this.row = r;
        this.col = c;
        this.sum = sum;
    }

    public int compareTo(Node node){
        return sum - node.sum;
    }
}
