/*
This question is from https://leetcode.com/problems/word-pattern/
Difficulty: easy

Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.

Time Complexity: O()
*/
public class Solution {
    public boolean wordPattern(String pattern, String str) {
        char[] pArr = pattern.toCharArray();
        String[] sArr = str.split(" ");
        Map<Character, String> map = new HashMap<Character, String>();
        if(pArr.length != sArr.length) return false;
        for(int i = 0; i< sArr.length; i++){
        	if(map.containsKey(pArr[i])){
        		String tmp = map.get(pArr[i]);
        		if(!tmp.equals(sArr[i])) return false;
        	}else{
        		if(map.containsValue(sArr[i])) return false;
        		map.put(pArr[i], sArr[i]);
        	}
        }
        return true;
    }
}
