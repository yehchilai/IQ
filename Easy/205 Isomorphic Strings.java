/*
This question is from https://leetcode.com/problems/isomorphic-strings/
Difficulty: easy

Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. 
No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.

Time Complexity: O()
*/
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Integer> stringS = new HashMap<Character, Integer>();
        Map<Character, Integer> stringT = new HashMap<Character, Integer>();
        for(int i = 0; i < s.length(); i++){
        	if(stringS.containsKey(s.charAt(i)) ^ stringT.containsKey(t.charAt(i))){
        		return false;
        	}else if (stringS.containsKey(.charAt(i)) && stringT.containsKey(t.charAt(i))) {
        		if(stringS.get(s.charAt(i)) != stringT.get(t.charAt(i)) return false;
        	}else{
        		stringS.put(s.charAt(i));
    			stringT.put(t.charAt(i));
        	}
        }
        return true;
    }
}