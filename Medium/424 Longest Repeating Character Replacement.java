/*
This question is from https://leetcode.com/problems/longest-repeating-character-replacement/
Difficulty: medium

Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
*/
// HashMap, does not work
class Solution {
    public int characterReplacement(String s, int k) {
        // using sliding window solution
        int[] count = new int[26];
        int length = s.length();
        int start = 0, max = 0;
        for(int end = 0; end < length; end++){
            // add 1 count to the current character
            int current = s.charAt(end) - 'A';
            count[current]++;
            // update the max length of a character
            max = Math.max(max, count[current]);
            // if relpacing all avalibale character, the length is still equal to the window. Then, move window.
            if(max + k <= end - start){
                count[s.charAt(start) - 'A']--;
                start++;
            }
        }

        return length - start;
    }
}