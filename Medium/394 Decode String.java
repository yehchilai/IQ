/*
This question is from https://leetcode.com/problems/decode-string/
Difficulty: medium

Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
*/
// T:O(N), S:O(N), 1 ms
class Solution {
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        int index = 0;
        Stack<Integer> numStack = new Stack();
        Stack<String> stack = new Stack();

        while(index < len){
            char c = s.charAt(index);

            if(Character.isDigit(c)){
                int count = 0;

                while(Character.isDigit(s.charAt(index))){
                    int num = s.charAt(index) - '0';
                    count = count * 10 + num;
                    index++;
                }
                numStack.push(count);
            }else if(c == '['){
                stack.push(sb.toString());
                sb = new StringBuilder();
                index++;
            }else if(c == ']'){
                StringBuilder tmp = new StringBuilder(stack.pop());
                int count = numStack.pop();
                for(int i = 0; i < count; i++){
                    tmp.append(sb);
                }
                sb = new StringBuilder(tmp);
                index++;
            }else{
                sb.append(c);
                index++;
            }
        }

        return sb.toString();
    }
}