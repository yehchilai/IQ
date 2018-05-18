/*
This question is from https://leetcode.com/problems/valid-parentheses/
Difficulty: easy

Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

*/
class Solution {
    public boolean isValid(String s) {
        // spacial case
        if(s == null) return false;
        // string length
        char[] cArr = s.toCharArray();
        if(cArr.length == 0) return true;

        // parentheses stack
        Stack<Character> stack = new Stack();
        // go through the string
        for(int i = 0; i < cArr.length; i++){
            switch(cArr[i]){
                case '}':
                    if(stack.empty() || stack.peek() != '{') return false;
                    stack.pop();
                    break;
                case ']':
                    if(stack.empty() || stack.peek() != '[') return false;
                    stack.pop();
                    break;
                case ')':
                    if(stack.empty() || stack.peek() != '(') return false;
                    stack.pop();
                    break;
                default:
                    stack.push(cArr[i]);
                    break;
            }
        }

        if (stack.empty()){
            return true;
        }else{
            return false;
        }
    }
}


public class Solution {
    public boolean isValid(String s) {
       	char[] str = s.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < str.length; i++){
        	if(str[i] == '(' || str[i] == '[' || str[i] == '{'){
        		stack.push(str[i]);
        	}else{
        	    if(stack.isEmpty()) return false; // need to ckeck
        		switch (stack.pop()){
        			case '(':
        				if(str[i] != ')') return false;
        				break; // need to break
        			case '[':
        				if(str[i] != ']') return false;
        				break;
        			case '{':
        				if(str[i] != '}') return false;
        				break;
        		}
        	}
        }
        if(!stack.isEmpty()) return false;
        return true;
    }
}
// Alternative is from https://leetcode.com/discuss/68262/java-0ms-stack-solution
public boolean isValid(String s) {
    char[] cs = s.toCharArray();
    if (cs.length % 2 != 0)
        return false;
    Stack<Character> stack = new Stack<Character>();
    for(int i=0;i<cs.length;i++){
        if(cs[i]=='[' || cs[i] == '(' || cs[i] == '{'){
            stack.push(cs[i]);
        }else{
            if(stack.isEmpty()) return false;
            switch (stack.pop()){
            case '(':
                if(cs[i]!=')') return false;
                break;
            case '[':
                if(cs[i]!=']') return false;
                break;
            case '{':
                if(cs[i]!='}') return false;
                break;
            }
        }
    }
    if(!stack.isEmpty()) return false;
    return true;
}