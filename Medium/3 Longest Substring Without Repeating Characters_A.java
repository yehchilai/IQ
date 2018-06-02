/*
This question is from https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
Difficulty: medium

Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

*/
// T: O(2n), S: O(n), 63ms
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // set to record existing characters
        HashSet<Character> set = new HashSet();
        // indices of start and end
        int start = 0, end = 0;
        // string length
        int len = s.length();
        // max length
        int max = 0;
        // go through the string and move two indices
        while(end < len){
            if(set.contains(s.charAt(end))){
                // the new character is existed, remove the first character in the substring
                set.remove(s.charAt(start));
                // move start index forward
                start++;
            }else{
                // add character into the set
                set.add(s.charAt(end));
                // update the max length of substring
                max = Math.max(max, end - start + 1);
                // move end forward
                end++;
            }
        }

        return max;
    }
}

// Sliding Window Optimized
// T: O(n), S: O(min(m,n))
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}