/**
This question is from https://leetcode.com/problems/valid-tic-tac-toe-state/
Difficulty: medium

A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.

The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.

Here are the rules of Tic-Tac-Toe:

Players take turns placing characters into empty squares (" ").
The first player always places "X" characters, while the second player always places "O" characters.
"X" and "O" characters are always placed into empty squares, never filled ones.
The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
The game also ends if all squares are non-empty.
No more moves can be played if the game is over.
Example 1:
Input: board = ["O  ", "   ", "   "]
Output: false
Explanation: The first player always plays "X".

Example 2:
Input: board = ["XOX", " X ", "   "]
Output: false
Explanation: Players take turns making moves.

Example 3:
Input: board = ["XXX", "   ", "OOO"]
Output: false

Example 4:
Input: board = ["XOX", "O O", "XOX"]
Output: true
Note:

board is a length-3 array of strings, where each string board[i] has length 3.
Each board[i][j] is a character in the set {" ", "X", "O"}.
*/

// T:O(9), S:O(9), 0 ms (100%)
class Solution {
    public boolean validTicTacToe(String[] board) {
        int[][] row = new int[3][2];
        int[][] col = new int[3][2];
        int[][] d = new int[2][2];
        int turns = 0;

        for(int i = 0; i < 3; i++){
            char[] arr = board[i].toCharArray();
            for(int j = 0; j < arr.length; j++){
                if(arr[j] == ' ') continue;
                int player = (arr[j] == 'O'? 0:1);
                // System.out.println("   "+arr[j]+": "+player);
                if(player == 1){
                    turns++;
                }else{
                    turns--;
                }

                row[i][player]++;
                col[j][player]++;
                if(i == j){
                    d[0][player]++;
                }
                if( i+j == 2){
                    d[1][player]++;
                }
            }
        }

        boolean xWin = (row[0][1] == 3 || row[1][1] == 3 || row[2][1] == 3 ||
                        col[0][1] == 3 || col[1][1] == 3 || col[2][1] == 3 ||
                        d[0][1] == 3 || d[1][1] == 3);

        boolean oWin = (row[0][0] == 3 || row[1][0] == 3 || row[2][0] == 3 ||
                        col[0][0] == 3 || col[1][0] == 3 || col[2][0] == 3 ||
                        d[0][0] == 3 || d[1][0] == 3);

        // if x is win, it should be x turn (1).
        // if o is win, it should be o turn (0).
        if(xWin && turns == 0 || oWin && turns == 1) return false;

        // System.out.println(Arrays.toString(board)+" "+xWin+", "+oWin+": "+!(xWin && oWin)+", "+turns);
        // It only can be x turn or o turn.
        // x and o can not win in the same time.
        return (turns == 0 || turns == 1) && !(xWin && oWin);

    }


}