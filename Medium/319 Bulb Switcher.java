/*
This question is from https://leetcode.com/problems/bulb-switcher/
Difficulty: medium

There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.

Example:

Given n = 3.

At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off].

So you should return 1, because there is only one bulb is on.
*/

// T:O(N^2), M:O(N), Time Limit Exceeded
public class Solution {
    public int bulbSwitch(int n) {
        int result = 0;
        boolean[] bulbs = new boolean[n + 1];
        int interval = 2;
        for(int i = 1; i < bulbs.length; i++){
            bulbs[i] = true;
        }

        while(interval <= bulbs.length){
            for(int i = interval; i < bulbs.length; i= i + interval){
                if(bulbs[i] == true){
                    bulbs[i] = false;
                }else{
                    bulbs[i] = true;
                }
            }
            interval++;
        }

        for(int i = 1; i < bulbs.length; i++){
            if(bulbs[i] == true) result++;
        }
        return result;
    }
}

// Math , T:O(1), M:O(1), 0ms
/*
The bulb setting ON only happens when a number is a square number.
e.g.
4= 1X4, 2X2, we toggle the bulb at 1, 2, 4
16 = 1X16, 2X8, 4X4,  we toggle the bulb at 1, 2, 4, 8, 16
The bulb will be ON only when we toggle it in an odd number
*/
public class Solution {
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
}
