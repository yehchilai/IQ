/**
This question is from https://leetcode.com/problems/trapping-rain-water-ii/
DIfficulty: hard

Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.



Note:

Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.



Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.


The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.





After the rain, water is trapped between the blocks. The total volume of water trapped is 4.

*/

// https://www.youtube.com/watch?v=cJayBq38VYw
// T:O(N*M*log(N*M)), S:O(N*M), 13 ms (86.19%)
class Solution {
    public int trapRainWater(int[][] heightMap) {
        int ans = 0;
        if(heightMap == null || heightMap.length == 0) return ans;

        int[][] directions = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};

        PriorityQueue<Cell> pq = new PriorityQueue();

        int rows = heightMap.length;
        int cols = heightMap[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for(int i = 0; i < rows; i++){
            pq.add(new Cell(i, 0, heightMap[i][0]));
            visited[i][0] = true;
            pq.add(new Cell(i, cols - 1, heightMap[i][cols - 1]));
            visited[i][cols - 1] = true;
        }

        for(int i = 1; i < cols - 1; i++){
            pq.add(new Cell(0, i, heightMap[0][i]));
            visited[0][i] = true;
            pq.add(new Cell(rows - 1,i , heightMap[rows - 1][i]));
            visited[rows - 1][i] = true;
        }

        int max = pq.peek().height;

        while(pq.size() > 0){
            Cell current = pq.poll();
            max = Math.max(max, current.height);
            for(int i = 0; i < directions.length; i++){
                int r = directions[i][0] + current.row;
                int c = directions[i][1] + current.col;

                if(r >= 0 && r < rows && c >= 0 && c < cols && visited[r][c] == false){
                    visited[r][c] = true;
                    if(heightMap[r][c] < max)  ans += max - heightMap[r][c];
                    pq.add(new Cell(r, c, heightMap[r][c]));

                }
            }
        }
        return ans;
    }
}

class Cell implements Comparable<Cell>{
    int row;
    int col;
    int height;

    public Cell(int r, int c, int h){
        row = r;
        col = c;
        height = h;
    }

    @Override
    public int compareTo(Cell b){
        return this.height - b.height;
    }
}