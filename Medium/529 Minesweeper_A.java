/*
This question is from https://leetcode.com/problems/minesweeper/description/
Difficulty: medium

Let's play the minesweeper game (Wikipedia[https://en.wikipedia.org/wiki/Minesweeper_(video_game)], online game[http://minesweeperonline.com/])!
()
You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:

If a mine ('M') is revealed, then the game is over - change it to 'X'.
If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.
Example 1:
Input:

[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

Click : [3,0]

Output:

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:

Example 2:
Input:

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Click : [1,2]

Output:

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:

Note:
The range of the input matrix's height and width is [1,50].
The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
The input board won't be a stage when game is over (some mines have been revealed).
For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.

*/
// BFS, T: O(N*M), S: O(N*M), 11ms
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        // if click mine
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }

        // board height and width
        int height = board.length;
        int width = board[0].length;

        // adjacent q
        Deque<int[]> q = new LinkedList();
        // put the first position
        q.add(click);
        // check adjacent
        while(q.size() > 0){
            int[] current = q.poll();
            // adjecent mines
            int mines = 0;
            // go though all adjacent to check how many mines it has.
            for(int i = -1; i < 2 ; i++){
                for(int j = -1; j < 2; j++){
                    // center
                    if(i == 0 && j == 0) continue;
                    // update i and j
                    int row = current[0] + i, column = current[1] + j;
                    // check if the position is in the board
                    if(row >= 0 && row < height && column >= 0 && column < width){
                        if(board[row][column] == 'M') mines++;
                    }
                }
            }

            // if the number of adjacent mines is larger than 0, update this position with the number
            if(mines > 0){
                board[current[0]][current[1]] = (char)('0' + mines);
            }else{
                // add the adjavent postion in the queue
                for(int i = -1; i < 2 ; i++){
                    for(int j = -1; j < 2; j++){
                        // center
                        if(i == 0 && j == 0) continue;
                        // update i and j
                        int row = current[0] + i, column = current[1] + j;
                        // check if the position is in the board
                        if(row >= 0 && row < height && column >= 0 && column < width){
                            if(board[row][column] == 'E'){
                                q.add(new int[]{row, column});
                                // visited
                                board[row][column] = 'B';
                            }
                        }
                    }
                }
                board[current[0]][current[1]] = 'B';
            }
        }

        return board;
    }

}


// BFS with costume object, T: O(N*M), S: O(N*M), 10ms
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        // if click mine
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }

        // board height and width
        int height = board.length;
        int width = board[0].length;

        // adjacent q
        Deque<Position> q = new LinkedList();
        // put the first position
        q.add(new Position(click[0], click[1]));
        // check adjacent
        while(q.size() > 0){
            int size = q.size();
            // go through all position in the current queue
            for(int pop = 0; pop < size; pop++){
                Position p = q.poll();
                int i = p.i;
                int j = p.j;
                int mines = 0;
                Deque<Position> cq = new LinkedList<Position>();
                // check if the current position is in the board
                if(i >= 0 && j >= 0 && i < height && j < width){
                    // check all adjacent position
                    // top
                    if(i - 1 >= 0) mines += checkAdjacent(cq, board, i - 1, j);
                    // bottom
                    if(i + 1 < height) mines += checkAdjacent(cq, board, i + 1, j);
                    // left
                    if(j - 1 >= 0) mines += checkAdjacent(cq, board, i, j - 1);
                    // right
                    if(j + 1 < width) mines += checkAdjacent(cq, board, i, j + 1);
                    // left-bottom
                    if(i + 1 < height && j - 1 >= 0) mines += checkAdjacent(cq, board, i + 1, j - 1);
                    // left-top
                    if(i - 1 >= 0 && j - 1 >= 0) mines += checkAdjacent(cq, board, i - 1, j - 1);
                    // right-bottom
                    if(i + 1 < height && j + 1 < width) mines += checkAdjacent(cq, board, i + 1, j + 1);
                    // right-top
                    if(i - 1 >= 0 && j + 1 < width) mines += checkAdjacent(cq, board, i - 1, j + 1);

                    // update the current state
                    if(mines > 0){
                        board[i][j] = (char)(mines + '0');
                    }else{
                        board[i][j] = 'B';
                        while(cq.size() > 0) {
                            Position pPoll = cq.poll();
                            board[pPoll.i][pPoll.j] = 'B';
                            q.add(pPoll);

                        }
                    }
                }

            }
        }

        return board;
    }

    public int checkAdjacent(Deque<Position> q, char[][] board, int i, int j){
        if(board[i][j] == 'M'){
            return 1;
        }
        else if(board[i][j] == 'E'){
            q.add(new Position(i, j));
        }
        return 0;
    }

    class Position{
        int i;
        int j;
        public Position(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}