/*
This question is from https://leetcode.com/problems/add-digits/
Difficulty: easy 

Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?
*/

public class solution{
//1234, 1+2+3+4 = 10, 1+0 = 1
//567, 5+6+7 = 18, 1+8 = 9
//999, 9+9+9 = 27, 2+7 = 9
//1111, 1+1+1+1 = 4
// 38 = 100110
// 39, 3+9 = 12, 1+2 = 3 ; 39%9 = 3
// 40, 4+0 = 4 ; 40%9 = 4
// 41, 4+1 = 5 ; 40%9 = 5
// 42, 4+2 = 6
// 43, 4+3 = 7
// 44, 4+4 = 8
// 45, 4+5 = 9
// 46, 4+6 = 10, 1+0=1
// 47, 4+7 = 11, 1+1=2 
 public int addDigits(int num){
 	if(num !=0){
 		int ans = num%9;
 		if(ans == 0){
 		 return 9;
 		} else{
 		return ans;
 		}
 	}else{
 		return 0;
 	}
 }

}