/**
This question is from https://leetcode.com/problems/shortest-path-in-binary-matrix/
Difficulty: medium

In an N by N square grid, each cell is either empty (0) or blocked (1).

A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:

Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
C_1 is at location (0, 0) (ie. has value grid[0][0])
C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.



Example 1:

Input: [[0,1],[1,0]]


Output: 2

Example 2:

Input: [[0,0,0],[1,1,0],[1,1,0]]


Output: 4



Note:

1 <= grid.length == grid[0].length <= 100
grid[r][c] is 0 or 1
*/

// T:O(rows*cols), S:O(1), 17 ms (87.77%)
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0][0] == 1) return -1;
        int moves = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        Deque<int[]> q = new ArrayDeque();
        q.add(new int[]{0,0});
        grid[0][0] = 1;
        while( q.size() > 0){

            int size = q.size();
            for(int index = 0; index < size; index++){
                int[] current = q.poll();
                // System.out.println(Arrays.toString(current));
                if(current[0] == rows - 1 && current[1] == cols - 1) return moves + 1;

                for(int i = -1; i <= 1; i++){
                    for(int j = -1; j <= 1; j++){
                        if(i == 0 && j == 0) continue;

                        int r = current[0] + i;
                        int c = current[1] + j;
                        // System.out.println(rows+", "+cols);
                        if(r < rows && r >= 0 && c < cols && c >= 0 && grid[r][c] != 1){
                            // System.out.println("ADD: "+r+": "+c);
                            grid[r][c] = 1;
                            q.add(new int[]{r, c});
                        }
                    }
                }
            }

            moves++;
        }


        return -1;
    }
}