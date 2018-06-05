/*
This question is from https://leetcode.com/problems/number-of-distinct-islands/description/
Difficulty: medium

Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Example 1:
11000
11000
00011
00011
Given the above grid map, return 1.
Example 2:
11011
10000
00001
11011
Given the above grid map, return 3.

Notice that:
11
1
and
 1
11
are considered different island shapes, because we do not consider reflection / rotation.
Note: The length of each dimension in the given grid does not exceed 50.

*/

// T:O(C*R), S:O(C*R), 66 ms
class Solution {

    int[][] mGrid;
    Set<Integer> mShape;
    boolean[][] mVisited;
    int mRows;
    int mColumns;

    public int numDistinctIslands(int[][] grid) {
        // result shapes
        Set<Set<Integer>> shapes = new HashSet();

        // rows and columns
        mRows = grid.length;
        mColumns = grid[0].length;

        // visitied position in the grid
        mVisited = new boolean[mRows][mColumns];

        // referece the grid
        this.mGrid = grid;

        // go through all position in the grid
        for(int r = 0; r < mRows; r++){
            for(int c = 0; c < mColumns; c++){
                mShape = new HashSet();
                checkShape(r, c, r, c);
                if(!mShape.isEmpty()) shapes.add(mShape);
            }
        }

        return shapes.size();
    }

    private void checkShape(int r, int c, int r0, int c0){
        // check boundary
        if(r >= 0 && r < mRows && c >= 0 && c < mColumns && !mVisited[r][c] && mGrid[r][c] == 1){
            // encode the position
            int p = (r - r0) * 2 * mColumns + (c - c0);
            // add this position into the shape
            mShape.add(p);
            // visited
            mVisited[r][c] = true;
            // BFS
            checkShape(r+1, c, r0, c0);
            checkShape(r-1, c, r0, c0);
            checkShape(r, c+1, r0, c0);
            checkShape(r, c-1, r0, c0);
        }
    }
}