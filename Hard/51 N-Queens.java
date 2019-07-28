/**
This question is from https://leetcode.com/problems/n-queens/
Difficulty: hard

The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
*/

// T:O(n!*n^2), S:O(n^2), 5 ms (38.16%)
class Solution {

    List<List<String>> ans;

    public List<List<String>> solveNQueens(int n) {

        ans = new LinkedList();

        if(n == 0) return ans;

        char[][] board = new char[n][n];

        for(char[] arr: board) Arrays.fill(arr, '.');

        backtracking(board, 0, n);

        return ans;
    }

    private void backtracking(char[][] board, int col, int queens){
        if(queens == 0){
            ans.add(getString(board));
        }

        for(int r = 0; r < board.length; r++){
            if(col < board.length && board[r][col] == '.'){
                if(valid(board, r, col)){
                    board[r][col] = 'Q';
                    backtracking(board, col + 1, queens - 1);
                    board[r][col] = '.';
                }
            }
        }
    }

    private List<String> getString(char[][] board){
        int len = board.length;
        List<String> strList = new LinkedList();

        for(int i = 0; i < len; i++){
            strList.add(new String(board[i]));
        }
        return strList;
    }

    private boolean valid(char[][] board, int r, int c){
        for(int i = 0; i < board.length; i++){
            // check row and column
            if(board[r][i] == 'Q' || board[i][c] == 'Q') return false;
            // check diagnal
            for(int j = 0; j < board.length; j++){
                if(board[i][j] == 'Q' && (Math.abs(r - i) == Math.abs(c - j))) return false;
            }
        }

        return true;
    }
}