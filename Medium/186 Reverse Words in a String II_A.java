/*
This question is from https://leetcode.com/problems/reverse-words-in-a-string-ii/description/
Difficulty: medium

Given an input string , reverse the string word by word.

Example:

Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
Note:

A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces.
The words are always separated by a single space.

*/
// T:O(N), S:O(1), 5 ms
class Solution {
    public void reverseWords(char[] str) {
        // reverse the str
        reverse(str, 0, str.length - 1);
        // find a word and reverse the word
        int start = 0;
        for(int i = 0; i < str.length; i++){
            if(str[i] == ' '){
                // reverse a word
                reverse(str, start, i - 1);
                start = i+1;
                //printStr(str);
            }
        }

        // reverse the last word;
        reverse(str, start, str.length - 1);
    }

    public void reverse(char[] str, int start, int end){
        while(start < end){
            char tmp = str[start];
            str[start] = str[end];
            str[end] = tmp;
            start++;
            end--;
        }
    }
}