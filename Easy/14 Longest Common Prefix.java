/*
This Question is from https://leetcode.com/problems/longest-common-prefix/
Difficulty: easy

Write a function to find the longest common prefix string amongst an array of strings.

Time Complexity: O()
*/
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0 || strs[0].length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < strs[0].length(); i++){
        	for(int j=1; j< strs.length; j++){
        	    if(strs[j].length() - 1 < i) return sb.toString();
        		if(strs[0].charAt(i) !=  strs[j].charAt(i)) return sb.toString();
        	}
        	sb.append(strs[0].charAt(i));
        }
        return sb.toString();
    }
}