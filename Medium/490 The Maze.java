/*
This question is from https://leetcode.com/problems/the-maze/
Difficulty: medium

There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.



Example 1:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true

Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false

Explanation: There is no way for the ball to stop at the destination.



Note:

There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

*/

// T:O(N*M), S:O(N*M), 5 ms
class Solution {

    int[][] maze;
    boolean[][] visited;
    int row;
    int col;

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if(maze == null || maze.length == 0) return false;

        int[][] directions = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        row = maze.length;
        col = maze[0].length;
        this.maze = maze;
        visited = new boolean[row][col];
        visited[start[0]][start[1]] = true;
        LinkedList<int[]> q = new LinkedList();

        q.add(start);
        // System.out.println("=====");
        while(q.size() > 0){
            int[] current = q.poll();
            int r = current[0];
            int c = current[1];


            if(r == destination[0] && c == destination[1]) return true;

            for(int i = 0; i < directions.length; i++){
                int[] next = getNext(r, c, directions[i]);
                if(!visited[next[0]][next[1]]){
                    // System.out.println(next[0]+","+next[1]);
                    visited[next[0]][next[1]] = true;
                    q.add(next);
                }
            }
        }

        return false;
    }

    private int[] getNext(int r, int c, int[] direction){

        while(true){
            int newR = r + direction[0];
            int newC = c + direction[1];

            if(newR < 0 || newR >= row || newC < 0 || newC >= col
               || maze[newR][newC] == 1) break;

            r = newR;
            c = newC;
        }

        return new int[]{r, c};
    }
}