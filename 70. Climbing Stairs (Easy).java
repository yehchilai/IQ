/*
This question is from https://leetcode.com/problems/climbing-stairs/
Difficulty: easy

You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?


Time Complexity: O()
*/
/* 
1 => 1 (1)
2 => 1,1
  => 2 (2) 
3 => 1,1,1
  => 1,2
  => 2,1 (3)
4 => 1,1,1,1
  => 2,1,1
  => 1,2,1
  => 1,1,2
  => 2,2 (5)

Fibonacci question

*/
public class Solution {
    public int climbStairs(int n) {
        if(n == 0 || n == 1 || n == 2) return n;

        int tmp;
        int last = 1;
        int current = 2;
        for(int i = 3; i < n + 1; i++){
        	tmp = current;
        	current = current + last;
        	last = tmp;
        }

        return current;
    }
}