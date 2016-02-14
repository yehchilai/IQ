/*
This question is from https://leetcode.com/problems/valid-sudoku/
Difficulty: easy

Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.

Time Complexity: (N^2)
*/
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<String> set = new HashSet<String>();
        for(int i = 0; i < 9; i++){
        	for(int j = 0; j < 9; j++){
        		if(board[i][j] != '.'){
        			if(!set.add(board[i][j] + " in the row " + i) ||
        				!set.add(board[i][j] + " in the column " + j)||
        				!set.add(board[i][j] + " in block " + i/3 + j/3))
        				return false;
        		}
        	}
        }
        return true;
    }
}