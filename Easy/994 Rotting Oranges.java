/*
This question is from https://leetcode.com/problems/rotting-oranges/
Difficulty: easy

In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.



Example 1:



Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.


Note:

1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] is only 0, 1, or 2.
*/
// T:O(N*M), S:O(N*M),  1 ms
class Solution {
    public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0) return -1;

        int minute = -1;
        int row = grid.length;
        int col = grid[0].length;
        int[][] directions = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

        LinkedList<int[]> q = new LinkedList();

        for(int r = 0; r < row; r++){
            for(int c = 0 ; c < col; c++){
                if(grid[r][c] == 2) q.add(new int[]{r, c});
            }
        }

        while(q.size() > 0){
            int len = q.size();

            for(int i = 0; i < len; i++){
                int[] current = q.poll();
                for(int j = 0; j < directions.length; j++){
                    int r = current[0] + directions[j][0];
                    int c = current[1] + directions[j][1];

                    if(r >= 0 && r < row && c >= 0 && c < col && grid[r][c] == 1){
                        grid[r][c] = 2;
                        q.add(new int[]{r, c});
                    }
                }
            }

            minute++;
        }

        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                if(grid[r][c] == 1) return -1;
            }
        }

        return Math.max(minute, 0);
    }
}