/*
This question is from https://leetcode.com/problems/cat-and-mouse/
Difficulty: hard

A game on an undirected graph is played by two players, Mouse and Cat, who alternate turns.

The graph is given as follows: graph[a] is a list of all nodes b such that ab is an edge of the graph.

Mouse starts at node 1 and goes first, Cat starts at node 2 and goes second, and there is a Hole at node 0.

During each player's turn, they must travel along one edge of the graph that meets where they are.  For example, if the Mouse is at node 1, it must travel to any node in graph[1].

Additionally, it is not allowed for the Cat to travel to the Hole (node 0.)

Then, the game can end in 3 ways:

If ever the Cat occupies the same node as the Mouse, the Cat wins.
If ever the Mouse reaches the Hole, the Mouse wins.
If ever a position is repeated (ie. the players are in the same position as a previous turn, and it is the same player's turn to move), the game is a draw.
Given a graph, and assuming both players play optimally, return 1 if the game is won by Mouse, 2 if the game is won by Cat, and 0 if the game is a draw.



Example 1:

Input: [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
Output: 0
Explanation:
4---3---1
|   |
2---5
 \ /
  0


Note:

3 <= graph.length <= 50
It is guaranteed that graph[1] is non-empty.
It is guaranteed that graph[2] contains a non-zero element.
*/

/*The idea is from MR wang's blog:  https://www.acwing.com/solution/leetcode/content/556
He did it in C++, i just transform it to JAVA

The basic idea is the memory cache search.
There are 3 dimensions: The time t, mouse position x, and cat position y.
so at any time and any position   f(t, x, y) should have a value.
value       0       1                   2
result      draw   mouse win            cat win

There are  n nodes, so when after 2n steps if we still can't decide who wins, the chase game will continue for ever and return 0

There are totally 2n* n * n = 2n^3 status, we may not reach every position. because we only can move along the graph's edges

For all those 2*n^3 status, some position we already know who will win.
that is 1# f(*, 0,*)=1, when mouse go to position, mouse win.
        2# f(*, y,y)=2, when mouse and cat get ot the same position, then cat win.
        3# f(2*n,*,*) return 0, because after this, there will repeat for ever

initially, we set all the status to -1.
base case is above 3 plus #4 that, we already have this position or dp[t][x][y]!=-1

when mouse't turn,
we check the 4 base cases, if don't meet those 4 base cases, we need move furthre
f(t+1,i,y) i in graph[x]. if any of those next status is 1 we return 1
                          if all those next satus is 2  we return  2
                          otherwise we reutrn 0
once you understand the idea, the code is very easy
And time is also, easy to see which is o(n^3)
*/
// T:O(N^3), S:O(N^3), 19 ms
class Solution {

    int[][][] state;

    public int catMouseGame(int[][] graph) {
        int N = graph.length;
        // state (time, mouse, cat)
        state = new int[2*N][N][N];
        // initalize all state as -1
        for(int i = 0 ; i < state.length; i++){
            for(int j = 0; j < state[0].length; j++){
                Arrays.fill(state[i][j], -1);
            }
        }

        return play(graph, 0, 1, 2);
    }

    private int play(int[][] graph, int time, int mouse, int cat){
        // draw
        if(time == graph.length * 2) return 0;
        // cat win
        if(mouse == cat) {
            return state[time][mouse][cat] = 2;
        }
        // mouse win
        if(mouse == 0){
            return state[time][mouse][cat] = 1;
        }
        // return state
        if(state[time][mouse][cat] != -1) return state[time][mouse][cat];

        int turn = time % 2;
        boolean lose;
        if(turn == 0){ // mouse turn
            lose = true; // assume all state are 2, cat win
            for(int i = 0; i < graph[mouse].length ; i++){
                int nextState = play(graph, time + 1, graph[mouse][i], cat);
                if(nextState == 1){
                    return state[time][mouse][cat] = 1;
                }else if(nextState != 2){
                    lose = false;
                }
            }

            if(lose){
                return state[time][mouse][cat] = 2;
            }else{
                return state[time][mouse][cat] = 0;
            }
        }else{ // cat turn
            lose = true; // assume all state are 1, mouse win
            for(int i = 0; i < graph[cat].length ; i++){
                if(graph[cat][i] != 0){
                    int nextState = play(graph, time + 1, mouse, graph[cat][i]);
                    if(nextState == 2){
                        state[time][mouse][cat] = 2;
                        return 2;
                    }else if( nextState != 1){
                        lose = false;
                    }
                }
            }

            if(lose){
                return state[time][mouse][cat] = 1;
            }else{
                return state[time][mouse][cat] = 0;
            }
        }

    }
}