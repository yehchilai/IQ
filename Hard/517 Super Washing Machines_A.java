/*
This question is from https://leetcode.com/problems/super-washing-machines/description/
Difficulty: medium

You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.

For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass one dress of each washing machine to one of its adjacent washing machines at the same time .

Given an integer array representing the number of dresses in each washing machine from left to right on the line, you should find the minimum number of moves to make all the washing machines have the same number of dresses. If it is not possible to do it, return -1.

Example1

Input: [1,0,5]

Output: 3

Explanation:
1st move:    1     0 <-- 5    =>    1     1     4
2nd move:    1 <-- 1 <-- 4    =>    2     1     3
3rd move:    2     1 <-- 3    =>    2     2     2
Example2

Input: [0,3,0]

Output: 2

Explanation:
1st move:    0 <-- 3     0    =>    1     2     0
2nd move:    1     2 --> 0    =>    1     1     1
Example3

Input: [0,2,0]

Output: -1

Explanation:
It's impossible to make all the three washing machines have the same number of dresses.
Note:
The range of n is [1, 10000].
The range of dresses number in a super washing machine is [0, 1e5].

*/
// Does not work
class Solution {
    public int findMinMoves(int[] machines) {
        // total dresses
        int total = 0;
        for(int number: machines){
            total += number;
        }
        // situation which the machines can not be even.
        if(total%machines.length != 0) return -1;

        // total steps
        int move = 0;

        while(!isEven(machines)){
            int i = findMax(machines);
            int j = findMin(machines);
            System.out.println(i + ", "+ j);
            machines[i] = machines[i] - 1;
            machines[j] = machines[j] + 1;

            move++;
        }
        System.out.println("===========");
        return move;
    }

    /*
     check if the machines have the same dresses.
    **/
    private boolean isEven(int[] machines){
        for(int i=1; i<machines.length; i++){
            if(machines[0] != machines[i]) return false;
        }
        return true;
    }

    /*
     find the max indxe of the machines
    **/
    private int findMax(int[] m){
        int index = 0;
        int max = Integer.MIN_VALUE;
        for(int i= 0; i < m.length ; i++){
            if(m[i] > max){
                index = i;
                max = m[i];
            }
        }
        return index;
    }

    /*
     find the max indxe of the machines
    **/
    private int findMin(int[] m){
        int index = 0;
        int min = Integer.MAX_VALUE;
        for(int i= 0; i < m.length ; i++){
            if(m[i] < min){
                index = i;
                min = m[i];
            }
        }
        return index;
    }

}

// work but does not make sense to me.
// T: O(N), S: O(1), 15 ms
/*
[1,0,5]			3
[0,3,0]			2
[0,2,0]			-1
[4,0,0,4]		2
[0,0,11,5]		8
[0,3,0]			2
[9,1,8,8,9]		4
*/
class Solution{
    public int findMinMoves(int[] machines) {
        // total dresses
        int total = 0;
        for(int number: machines){
            total += number;
        }
        // situation which the machines can not be even.
        if(total%machines.length != 0) return -1;

        int average = total/machines.length;

        // moves
        int moves = 0;
        // total sub
        int sub = 0;
        for(int i = 0; i < machines.length; i++){
            int current = machines[i] - average;
            sub += current;
            int currentMax = Math.max(current, Math.abs(sub));
            moves = Math.max(moves, currentMax);
        }

        return moves;
    }
}