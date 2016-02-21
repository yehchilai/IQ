/*
This question is from https://leetcode.com/problems/valid-parentheses/
Difficulty: easy

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

*/
public class Solution {
    public boolean isValid(String s) {
        int length = s.length();
        Stack stack = new Stack();
        for(int i = 0; i < length; i++){
        	if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{'){
        		stack.push(s.charAt(i));
        	}else{
        		char tmp = stack.pop();
        		switch (tmp){
        			case '(':
        				if(s.charAt(i) != ')') return false;
        			case '[':
        				if(s.charAt(i) != ']') return false;
        			case '{':
        				if(s.charAt(i) != '}') return false;
        		}
        	}
        }
        return true;
    }
}