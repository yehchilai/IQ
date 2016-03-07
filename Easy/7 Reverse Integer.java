/*
This quesiton is from https://leetcode.com/problems/reverse-integer/

Example1: x = 123, return 321
Example2: x = -123, return -321


Time Complexity: O()
*/


/* click to show spoilers.!!!!!!!!!!
Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, 
then the reverse of 1000000003 overflows. How should you handle such cases?

For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
*/

public class Solution {
    public int reverse(int x) {
        double result = 0;
        while(x != 0){
        	result = result*10 + x%10;
        	x = x/10;
        }

        if(result > Integer.Max || result < Integer.Min) return 0;
        return (int)result;
    }
}

// Alternative : https://leetcode.com/discuss/69938/2ms-easy-to-understand-java-solution
public class Solution {
	public int reverse(int x) {
	    boolean isNegative = x < 0;
	    x = Math.abs(x);
	    int result = 0;
	    int max_diff = Integer.MAX_VALUE / 10;
	    while(x > 0) {
	        if(result > max_diff) return 0;
	        result = 10*result + x % 10;
	        x /= 10;
	    }
	    return isNegative ? -result : result;
	}
}
