/*
This question is from https://leetcode.com/problems/excel-sheet-column-number/
Difficulty: Easy

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 


Time Complexity: O(N) N: chars in the string
*/

public class Solution {
    public int titleToNumber(String s) {
    	int result = 0;
    	for(char c : s.toCharArray()){
    		result = result * 26 + (c - 'A') + 1;
    	}
    	return result;
    }
}