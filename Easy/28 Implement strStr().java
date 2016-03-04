/*
This question is from https://leetcode.com/problems/implement-strstr/
DIfficulty: easy

Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*/
public class Solution {
    public int strStr(String haystack, String needle) {
        char[] haystackArr = haystack.toCharArray();
        char[] needleArr = needle.toCharArray();
        if(haystackArr.length == 0){
        	if(needleArr.length == 0){
        		return 0;
        	}else{
        		return -1;
        	}
        }
        for(int i = 0; i< haystackArr.length; i++){
        	if(haystackArr[i] == needleArr[0]) return i;
        }

        return -1;
    }
}