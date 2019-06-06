/*
This question is from https://leetcode.com/problems/the-maze-ii/
Difficulty: medium

There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

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

Output: 12

Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Example 2:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1

Explanation: There is no way for the ball to stop at the destination.



Note:

There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

*/
// T:O(M*N), S:O(M*N), 10 ms
class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {

        if(maze == null || maze.length == 0) return -1;

        int row = maze.length;
        int col = maze[0].length;
        int[][] distances = new int[row][col];
        for(int i = 0; i < distances.length; i++){
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }

        int[][] direction = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

        PriorityQueue<State> pq = new PriorityQueue(new StateComparator());
        pq.add(new State(start[0], start[1], 0));
        distances[start[0]][start[1]] = 0;

        while(pq.size() > 0){
            State current = pq.poll();

            if(distances[current.r][current.c] < current.distance) continue;
            // System.out.println(current.r+","+current.c);
            for(int i = 0; i < direction.length; i++){
                State next = getNext(current, direction[i], maze);
                if(distances[next.r][next.c] > next.distance){
                    distances[next.r][next.c] = next.distance;
                    pq.add(next);
                }
            }
        }

        if(distances[destination[0]][destination[1]] == Integer.MAX_VALUE) return -1;
        return distances[destination[0]][destination[1]];
    }

    private State getNext(State current, int[] direction, int[][] maze){
        int r = current.r;
        int c = current.c;
        int distance = current.distance;
        int row = maze.length;
        int col = maze[0].length;

        while(true){
            int newR = r + direction[0];
            int newC = c + direction[1];
            if(newR < 0 || newR >= row || newC < 0 || newC >= col || maze[newR][newC] != 0) break;

            r = newR;
            c = newC;
            distance++;
        }
        // System.out.println(r+","+c);
        return new State(r, c, distance);
    }
}

class State{
    int r;
    int c;
    int distance;

    State(int row, int col, int d){
        this.r = row;
        this.c = col;
        this.distance = d;
    }
}

class StateComparator implements Comparator<State>{
    @Override
    public int compare(State a, State b){
        return a.distance - b.distance;
    }
}
// wrong answer
class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {

        if(maze == null || maze.length == 0) return -1;

        int row = maze.length;
        int col = maze[0].length;
        boolean[][] visited = new boolean[row][col];

        int[][] direction = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

        PriorityQueue<State> pq = new PriorityQueue(new StateComparator());
        pq.add(new State(start[0], start[1], 0));
        visited[start[0]][start[1]] = true;

        while(pq.size() > 0){
            State current = pq.poll();

            if(current.r == destination[0] && current.c == destination[1]) return current.distance;

            for(int i = 0; i < direction.length; i++){
                State next = getNext(current, direction[i], maze);
                if(visited[next.r][next.c] == false){
                    visited[next.r][next.c] = true;
                    pq.add(next);
                }
            }
        }

        return -1;
    }

    private State getNext(State current, int[] direction, int[][] maze){
        int r = current.r;
        int c = current.c;
        int distance = current.distance;
        int row = maze.length;
        int col = maze[0].length;

        while(true){
            int newR = r + direction[0];
            int newC = c + direction[1];
            if(newR < 0 || newR >= row || newC < 0 || newC >= col || maze[newR][newC] != 0) break;

            r = newR;
            c = newC;
            distance++;
        }
        // System.out.println(r+","+c);
        return new State(r, c, distance);
    }
}

class State{
    int r;
    int c;
    int distance;

    State(int row, int col, int d){
        this.r = row;
        this.c = col;
        this.distance = d;
    }
}

class StateComparator implements Comparator<State>{
    @Override
    public int compare(State a, State b){
        return a.distance - b.distance;
    }
}