/*
This question is from https://leetcode.com/problems/knight-probability-in-chessboard/
Difficulty: medium

On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.







Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.



Example:

Input: 3, 2, 0, 0
Output: 0.0625
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.


Note:

N will be between 1 and 25.
K will be between 0 and 100.
The knight always initially starts on the board.
*/
// T:O(K * N^2), S:O(N^2), 4 ms
class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        int[][] move = new int[][]{{2, -1}, {1, -2}, {-1, -2}, {-2, -1},
                                   {-2, 1}, {-1, 2}, {1, 2}, {2, 1}};

        double[][] dp = new double[N][N];
        dp[r][c] = 1;

        while(K > 0){
            double[][] tmp = new double[N][N];

            for(int row = 0; row < N; row++){
                for(int col = 0; col < N; col++){
                    //update Probability
                    if(dp[row][col] > 0){
                        for(int i = 0; i < move.length; i++){
                            int nextRow = row + move[i][0];
                            int nextCol = col + move[i][1];
                            if(nextRow >=0 && nextRow < N && nextCol >= 0 && nextCol < N)
                                tmp[nextRow][nextCol] += dp[row][col]/8;
                        }
                    }
                }
            }

            dp = tmp;
            K--;
        }

        double ans = 0.0;
        for(double[] row: dp){
            for(double d: row) ans += d;
        }

        return ans;
    }
}