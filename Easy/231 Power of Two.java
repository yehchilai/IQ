/*
This question is from https://leetcode.com/problems/power-of-two/

Given an integer, write a function to determine if it is a power of two.


Time Complexity:O(N)
*/

// T:O(1), S:O(1), 1ms
class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n <= 0) return false;
        if(n == 1) return true;

        return (n & (n - 1)) == 0 ? true : false;
    }
}

public class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n == 1) return true;
        if(n%2 != 0 || n < 1) return false;
        return isPowerOfTwo(n/2);
    }
}

// Answer II : Time Complexity: O(1)
public class Solution {
	public boolean isPowerOfTwo(int n) { //if n is power  of  2 ,n just has one bit is 1
	    return n>0 && (n&(n-1))==0;
	}
}