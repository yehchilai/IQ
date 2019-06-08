/*
This question is from https://leetcode.com/problems/word-search/
Difficulty: medium

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
*/
// T:O(N), S:O(N), 11 ms
class Solution {

    int[][] directions;
    char[][] board;
    String word;
    int row;
    int col;

    public boolean exist(char[][] board, String word) {

        if(board == null || board.length == 0) return false;
        if(word == null || word.length() == 0) return true;

        row = board.length;
        col = board[0].length;
        this.board = board;
        this.word = word;

        directions = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                if(board[r][c] == word.charAt(0)){
                    if(dfs(r, c, 0, new boolean[row][col])) return true;
                    // System.out.println("=====");
                }
            }
        }

        return false;
    }

    private boolean dfs(int r, int c , int index, boolean[][] visited){

        visited[r][c] = true;
        index++;
        if(index >= word.length()) return true;
        char next = word.charAt(index);
        boolean ans = false;
        for(int i = 0; i < directions.length; i++){
            int newR = r + directions[i][0];
            int newC = c + directions[i][1];

            if(newR >= 0 && newR < row && newC >= 0 && newC < col
               && !visited[newR][newC] && next == board[newR][newC]){
                // System.out.println(board[newR][newC]);
                 if(dfs(newR, newC, index, visited)){
                     ans = true;
                 }else{
                     visited[newR][newC] = false;
                 }
            }

        }
        return ans;
    }
}