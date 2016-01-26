/*
This question is from https://leetcode.com/problems/happy-number/
Difficulty: easy

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: 
Starting with any positive integer, replace the number by the sum of the squares of its digits, 
and repeat the process until the number equals 1 (where it will stay), 
or it loops endlessly in a cycle which does not include 1. 
Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1

Time Complexity: O()
*/
public class Solution {
    public boolean isHappy(int n) {
        if(n < 0) return false;
        if(n == 1) return true;
        int sum = 0;
        HashSet<Integer> repeat = new HashSet<Integer>();
        while(repeat.add(n)){
        	while(n >= 10){
    			sum += (int)(n%10) * (int)(n%10);
    			n = (int)(n/10);
        	}
        	n = n*n + sum;
        	if(n == 1) return true;
        	sum = 0;
        }
        return false;
    }
}