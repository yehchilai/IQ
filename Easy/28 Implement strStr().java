/*
This question is from https://leetcode.com/problems/implement-strstr/
DIfficulty: easy

Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*/
// (10ms) from https://leetcode.com/discuss/46047/elegant-java-solution
public class Solution {
    public int strStr(String haystack, String needle) {
        for(int i = 0 ; ; i++){
        	for(int j = 0; ; j++){
        		if(j >= needle.length()) return i;
        		if((i+j) >= haystack.length()) return -1;
        		if(haystack.charAt(i+j) != needle.charAt(j)) break;
        	}
        }
    }
}

// wrong answer
public class Solution {
    public int strStr(String haystack, String needle) {
        if(haystack.length() < needle.length()) return -1;
        if(needle.length() == 0) return 0;
        
        int i = 0;
        int j = 0;
        int index = -1;
        while(i < haystack.length()){
        	if(haystack.charAt(i) == needle.charAt(j)){
        		if(j == 0) index = i;
        		j++;
        		if(j >= needle.length()) return index;
        	}else{
        		j = 0;
        		index = -1;
        	}

        	i++;
        }
        return index;
    }
}