/*
This question is from https://leetcode.com/problems/valid-anagram/
Difficulty: easy


Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Time Complexity: O(N)
*/

public class Solution {
    public boolean isAnagram(String s, String t) {
		int count1[] = new int[26];
		int count2[] = new int[26];

		if(s.length()!=t.length()) return false;

		for(int i=0;i<s.length();i++){
			count1[(s.charAt(i) - 'a')]++;
			count2[(t.charAt(i) - 'a')]++;
		}

		if(Arrays.equals(count1,count2)){
		    return true;
		}else{
		    return false;
		} 
	}
}