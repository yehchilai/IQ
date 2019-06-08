/*
This question is from https://leetcode.com/problems/game-of-life/
Difficulty: medium

According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

Example:

Input:
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output:
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
Follow up:

Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
*/
// T:O(N), S:O(1), 0 ms
class Solution {
    public void gameOfLife(int[][] board) {
        if(board == null || board.length == 0) return;

        int row = board.length;
        int col = board[0].length;

        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){

                // 8 direction
                int ones = 0;
                for(int i = -1; i <= 1; i++){
                    for(int j = -1; j <= 1; j++){
                        if(i == 0 && j == 0) continue;
                        int newR = r + i;
                        int newC = c + j;
                        if(newR >= 0 && newR < row && newC >=0 && newC < col
                           && Math.abs(board[newR][newC]) == 1){
                            ones++;
                        }
                    }
                }

                if(ones < 2){
                    if(board[r][c] == 1) board[r][c] = -1;
                }else if(ones == 3){
                    if(board[r][c] == 0) board[r][c] = 2;
                }else if(ones > 3){
                    if(board[r][c] == 1) board[r][c] = -1;
                }
            }
        }

        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                if(board[r][c] == -1){
                    board[r][c] = 0;
                }else if(board[r][c] == 2){
                    board[r][c] = 1;
                }
            }
        }
    }
}