/*
This question is from https://leetcode.com/problems/count-and-say/
Difficulty: easy

The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.

Time Complexity: O()
*/
public class Solution {
    public String countAndSay(int n) {
    	String result = "1";
        if(n == 1) return result;
        for(int i = 1 ; i< n; i++){
        	char[] str = result.toCharArray();
        	result = "";
        	int count = 1;
        	char previous = str[0];
        	for(int j = 1; j < str.length; j++){
        		if(previous != str[j]){
        			result += Integer.toString(count) + Character.toString(previous);
        			previous = str[j];
        			count = 1;
        		}else{
        			count++;
        		}
        	}
        	result += Integer.toString(count) + Character.toString(previous);
        }
        return result;
    }
}