/*
This question is from https://leetcode.com/problems/design-tic-tac-toe/
Difficulty: medium

Design a Tic-tac-toe game that is played between two players on a n x n grid.

You may assume the following rules:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves is allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Example:
Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

TicTacToe toe = new TicTacToe(3);

toe.move(0, 0, 1); -> Returns 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

toe.move(0, 2, 2); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

toe.move(2, 2, 1); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

toe.move(1, 1, 2); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

toe.move(2, 0, 1); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

toe.move(1, 0, 2); -> Returns 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
Follow up:
Could you do better than O(n2) per move() operation?
*/
// T:O(1), S:O(N), 46 ms
class TicTacToe {

    int r[][]; // horizontial count
    int c[][]; // vertical count
    int d[][]; // diagonal count

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        r = new int[n][2];
        c = new int[n][2];
        d = new int[2][2]; // top-left to bottom-right and top-right to bottom-left
    }

    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        if(r.length == 0) return 0;
        int n = r.length;

        int p = player - 1;

        r[row][p]++; // count horizontal
        c[col][p]++; // count vertical
        if(row == col) d[0][p]++; // count top-left to bottom-right
        if(row + col == n - 1) d[1][p]++; // count top-right to bottom-left

        if(r[row][p] == n || c[col][p] == n
           || d[0][p] == n || d[1][p] == n){
            return player;
        }
        return 0;
    }
}


// T:O(N^2), S:O(N^2), 59 ms
class TicTacToe {

    int[][] board;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        board = new int[n][n];
    }

    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        if(board == null || board.length == 0) return 0;
        board[row][col] = player;

        return checkWinner(player);
    }

    private int checkWinner(int player){

        int n = board.length;

        // for(int i = 0; i < n; i++) System.out.println(Arrays.toString(board[i]));
        // System.out.println("===========");
        for(int i = 0; i < n; i++){
            if(board[i][i] == player && i == n - 1){
                return player;
            }else if(board[i][i] == player){
                continue;
            }else{
                break;
            }
        }

        for(int i = 0; i < n; i++){
            if(board[i][n - 1 - i] == player && i == n - 1){
                return player;
            }else if(board[i][n - 1 - i] == player){
                continue;
            }else{
                break;
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                // System.out.print(board[i][j]+",");
                if(board[i][j] == player && j == n - 1){
                    // System.out.print("return: "+player);
                    return player;
                }else if(board[i][j] == player){
                    continue;
                }else{
                    break;
                }
            }
            // System.out.println();
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(board[j][i] == player && j == n - 1){
                    return player;
                }else if(board[j][i] == player){
                    continue;
                }else{
                    break;
                }
            }
        }

        return 0;
    }

}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */