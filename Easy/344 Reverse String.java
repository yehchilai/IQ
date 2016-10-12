/*
This question is from https://leetcode.com/problems/reverse-string/
Difficulty: easy

Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".

*/

public class Solution {
    public String reverseString(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }
}