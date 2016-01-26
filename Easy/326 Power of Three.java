/*
Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion?


Time complexity: O()
*/

// loop
public class Solution {
    public boolean isPowerOfThree(int n) {
        if(n <= 0 || n == 2) return false;
        if(n == 1) return true;
        while(n > 1){
        	if(n%3 != 0){
        		return false;
        	}else{
        		n = n/3;
        	}
        }
        return true;
    }
}

// recursion
public class Solution {
    public boolean isPowerOfThree(int n) {
        if(n <= 0 || n == 2) return false;
        if(n == 1) return true;
        
        if(n%3 != 0){
        	return false;
        } else{
        	return isPowerOfThree(n/3);
        }

    }
}

// wired answer
public class Solution{
	public boolean isPowerOfThree(int n) {
       return n<1 ? false : 1162261467%n == 0 ? true : false ;
	}
}