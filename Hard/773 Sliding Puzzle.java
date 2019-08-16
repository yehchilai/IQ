/**
This question is from https://leetcode.com/problems/sliding-puzzle/
Difficulty: hard

On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
Input: board = [[3,2,4],[1,5,0]]
Output: 14
Note:

board will be a 2 x 3 array as described above.
board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].

*/

// BFS, T:O(N*N!), S:O(N), 10ms (33.03% )
class Solution {
    public int slidingPuzzle(int[][] board) {
        if(board == null || board.length == 0) return 0;

        int rows = board.length;
        int cols = board[0].length;

        int[][] directions = new int[][]{{-1, 0}, {1,0}, {0,-1}, {0,1}};

        Deque<Node> q = new ArrayDeque();
        HashSet<String> visited = new HashSet();

        String target = "123450";
        String invalid = "123540";

        int rZero = 0;
        int cZero = 0;

        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(board[r][c] == 0){
                    rZero = r;
                    cZero = c;
                    break;
                }
            }
        }

        String str = boardToString(board);
        q.add(new Node(board, rZero, cZero, str, 0));
        visited.add(str);
        visited.add(invalid);

        while( q.size() > 0){
            Node node = q.poll();

            if(node.strBoard.equals(target)) return node.move;

            int[][] currentBoard = node.board;

            for(int i = 0 ; i < directions.length; i++){
                int r = node.row + directions[i][0];
                int c = node.col + directions[i][1];

                if(r < rows && r >= 0 && c < cols && c >= 0){
                    int[][] newBoard = swap(currentBoard, r, c, node.row, node.col);
                    String strBoard = boardToString(newBoard);
                    // System.out.println(strBoard);
                    if(!visited.contains(strBoard)){
                        q.add(new Node(newBoard, r, c, strBoard, node.move + 1));
                        visited.add(strBoard);
                    }
                }
            }
        }

        return -1;

    }

    private String boardToString(int[][] board){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    private int[][] swap(int[][] board, int r, int c, int sr, int sc){
        // clone board

        int[][] newBoard = new int[board.length][board[0].length];
        for(int i = 0; i < newBoard.length; i++){
            for(int j = 0; j < newBoard[0].length; j++){
                newBoard[i][j] = board[i][j];
            }
        }
        newBoard[r][c]  = board[sr][sc];
        newBoard[sr][sc] = board[r][c];
        // System.out.println(Arrays.deepToString(board));
        // System.out.println(Arrays.deepToString(newBoard));
        return newBoard;
    }
}

class Node{

    String strBoard;
    int row;
    int col;
    int[][] board;
    int move;

    public Node(int[][] board, int row, int col, String strBoard, int move){
        this.board = board;
        this.row = row;
        this.col = col;
        this.strBoard = strBoard;
        this.move = move;

    }
}