/*
This question is from https://leetcode.com/problems/spiral-matrix/
Difficulty: medium

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

*/
// T:O(N), S:O(N), 0 ms
class Solution {

    int[][] dirt;
    int direction;
    int row;
    int col;
    boolean[][] visited;

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new LinkedList();
        if(matrix == null || matrix.length == 0) return result;
        // direction: right, down, left, up
        dirt = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

        int index = 0;
        direction = 0;
        row = matrix.length;
        col = matrix[0].length;
        int r = 0;
        int c = 0;
        visited = new boolean[row][col];

        while(index < row * col){
            result.add(matrix[r][c]);
            visited[r][c] = true;
            int[] next = next(r, c, index);
            // System.out.println(Arrays.toString(next));
            r = next[0];
            c = next[1];
            index++;

        }

        return result;
    }

    private int[] next(int r, int c, int index){
        int nextR = r;
        int nextC = c;
        while(index < row * col - 1){
            direction = direction % 4;
            nextR = r + dirt[direction][0];
            nextC = c + dirt[direction][1];
            if(nextR >= 0 && nextR < row && nextC >=0 && nextC < col && visited[nextR][nextC] == false){
                break;
            }else{
                direction++;
            }
        }

        return new int[]{nextR, nextC};
    }
}