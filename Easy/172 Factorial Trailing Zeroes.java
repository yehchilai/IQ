/*
This question is from https://leetcode.com/problems/factorial-trailing-zeroes/
Difficulty: easy

Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.
*/
// O(log(N)
public class Solution {
    public int trailingZeroes(int n) {
    	int result = 0;
    	while(n > 5){
    		result = result + n/5;
    		n = n/5;
    	}
    	return result;
    }
}

// O(N)
public class Solution {
    public int trailingZeroes(int n) {
    	int twos = 0;
    	int fives = 0;
    	for(int i = 1 ; i <= n; i++){
    		int tmp = i;
    		while((tmp%5 == 0) || (tmp%2 == 0)){
    			if(tmp%5 == 0){
    				fives++;
    				tmp = tmp/5;
    			}

    			if(tmp%2 == 0){
    				twos++;
    				tmp = tmp/2;
    			}
    		}
    	}  
    	return Math.min(twos, fives);
    }
}