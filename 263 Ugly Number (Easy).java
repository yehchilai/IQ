/*
This question is from https://leetcode.com/problems/ugly-number/
Difficulty: easy

Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.

Note that 1 is typically treated as an ugly number.


Time Complexity: O(3N) = O(N) ?not sure
*/
public class Solution {
    public boolean isUgly(int num) {
        if(num == 1) return true; // 1 is treated as an ugly number
        if(num <= 0) return false; // num must be positive number, and 0 is not an ugly number
        while(num > 1){
        	if(num%2 == 0){
        		num = num/2;
        		continue;
        	} else if(num%3 == 0){
        		num = num/3;
        		continue;
        	} else if(num%5 == 0){
        		num = num/5;
        		continue;
        	} 
        	return false;
        }
        return true;
    }
}

// Answer II is from https://leetcode.com/discuss/78188/java-solution-greatest-divide-by-2-3-5
// public class Solution {
//     public static boolean isUgly(int num) {
//         if (num <= 0) {
//             return false;
//         }
//         int[] divisors = {2, 3, 5};
//         for(int d : divisors) {
//             while (num % d == 0) {
//                 num /= d;
//             }
//         }
//         return num == 1;
//     }
// }