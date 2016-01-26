/*
This question is from https://leetcode.com/problems/number-of-1-bits/

Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.


Time Complexity: O()
*/

public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0){  // cannot use this solution because Java really treats the integer as a negative number With "2147483648(10000000000000000000000000000000)" as input
        				// the Integer range is [-2147483648, 2147483647]. It will cause compile error......
        	if(n % 2 == 1) count++;
        	n = n/2;
        }
        return count;
    }
}

// // Answer II
// public int hammingWeight(int n) {
//     int count = 0;
//     for(int i = 0; i < 32; i++){
//         if ((n & 1) == 1) count++;
//         n >>= 1; // or n = n >>> 1;
//     }
//     return count;
// }