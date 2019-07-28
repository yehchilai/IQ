/**
This question is from https://leetcode.com/problems/sudoku-solver/
Difficulty: hard

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.


A sudoku puzzle...


...and its solution numbers marked in red.

Note:

The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single unique solution.
The given board size is always 9x9.
*/

// backtracking, T:O(9^m), m is the number of empty slots. S:O(1 or m)?, 6 ms (73.70%)
class Solution {
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0) return;

        solve(board);
    }

    private boolean solve(char[][] board){
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                // check if the character is '.'
                if(board[r][c] == '.'){
                    // try digit from 1 to 9
                    for(char digit = '1'; digit <= '9' ; digit++){
                        // valid the new digit
                        if(valid(board, r, c, digit)){
                            board[r][c] = digit;
                            if(solve(board)){
                                return true;
                            }
                            board[r][c] = '.';
                        }
                    }
                    // unable to fill a valid digit, then return false
                    return false;
                }
            }
        }
        // finish all slots, then return true
        return true;
    }

    private boolean valid(char[][] board, int r, int c, char target){
        for(int i = 0; i < 9; i++){
            if(board[r][i] != '.' && board[r][i] == target) return false;
            if(board[i][c] != '.' && board[i][c] == target) return false;
            if(board[3*(r/3) + i/3][3*(c/3) + i%3] != '.' &&
               board[3*(r/3) + i/3][3*(c/3) + i%3] == target) return false;
        }
        return true;
    }
}