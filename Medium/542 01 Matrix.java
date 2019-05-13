/*
This question is from https://leetcode.com/problems/01-matrix/
Difficulty: medium

Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.



Example 1:

Input:
[[0,0,0],
 [0,1,0],
 [0,0,0]]

Output:
[[0,0,0],
 [0,1,0],
 [0,0,0]]
Example 2:

Input:
[[0,0,0],
 [0,1,0],
 [1,1,1]]

Output:
[[0,0,0],
 [0,1,0],
 [1,2,1]]


Note:

The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.

*/

// T:O((N*M)^2), S:O(N*M), 23 ms
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return matrix;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] ans = new int[row][col];

        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                bfs(r, c, row, col, matrix, ans);
            }
        }

        return ans;
    }

    public void bfs(int r, int c, int row, int col, int[][] matrix, int[][] ans){
        if(matrix[r][c] == 1){
            LinkedList<Location> q = new LinkedList();
            Location loc = new Location(r, c, 0);
            q.add(loc);
            while(q.size() > 0){
                Location currLoc = q.poll();

                if(currLoc.r >= 0 && currLoc.r < row && currLoc.c >= 0 && currLoc.c < col){
                    if(matrix[currLoc.r][currLoc.c] == 0){
                        ans[r][c] = currLoc.distance;
                        break;
                    }else{
                        Location up = new Location(currLoc.r - 1, currLoc.c, currLoc.distance + 1);
                        q.add(up);
                        Location down = new Location(currLoc.r + 1, currLoc.c, currLoc.distance + 1);
                        q.add(down);
                        Location left = new Location(currLoc.r, currLoc.c - 1, currLoc.distance + 1);
                        q.add(left);
                         Location right = new Location(currLoc.r, currLoc.c + 1, currLoc.distance + 1);
                        q.add(right);
                    }
                }
            }
        }
    }
}

class Location{
    int r;
    int c;
    int distance;

    Location(int r, int c, int distance){
        this.r = r;
        this.c = c;
        this.distance = distance;
    }
}
