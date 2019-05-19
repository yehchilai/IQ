/*
This question is from https://leetcode.com/problems/backspace-string-compare/
Difficulty: easy

Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
Follow up:

Can you solve it in O(N) time and O(1) space?

*/
// T:O(N), S:O(1). 0 ms
class Solution {
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1;
        int j = T.length() - 1;
        int skipS = 0;
        int skipT = 0;


        while(i >= 0 || j >= 0){
            // skip S #
            while(i >= 0){
                if(S.charAt(i) == '#'){ skipS++; i--;}
                else if(skipS > 0){ skipS--; i--;}
                else{ break;}
            }
            // skip T #
            while(j >= 0){
                if(T.charAt(j) == '#'){ skipT++;j--;}
                else if(skipT > 0){skipT--; j--;}
                else{ break; }
            }

            if(i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j)) return false;
            if((i >= 0) != (j >= 0)) return false;
            i--;
            j--;
        }

        return true;
    }
}

// Stack, T:(N), S:O(N), 2 ms
class Solution {
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> stackS = new Stack();
        Stack<Character> stackT = new Stack();

        for(char c: S.toCharArray()){
            if(c == '#'){
                if(!stackS.empty()) stackS.pop();
            }else{
                stackS.push(c);
            }
        }

        for(char c: T.toCharArray()){
            if(c == '#'){
                if(!stackT.empty())stackT.pop();
            }else{
                stackT.push(c);
            }
        }

        if(stackS.size() != stackT.size()) return false;

        while(!stackS.empty()){
            char cs = stackS.pop();
            char ct = stackT.pop();
            if(cs != ct) return false;
        }

        return true;
    }
}