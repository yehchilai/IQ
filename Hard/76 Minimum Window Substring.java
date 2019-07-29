/**
This question is from https://leetcode.com/problems/minimum-window-substring/
Difficulty: medium

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
*/

// T:O(S+T), S:O(T), 19ms (51.24%)
class Solution {
    public String minWindow(String s, String t) {
        if(s == null || t == null) return "";

        int sLen = s.length();
        int tLen = t.length();

        if(tLen > sLen) return "";

        // generate target dictionary
        HashMap<Character, Integer> targetMap = new HashMap();

        for(int i = 0 ; i < tLen; i++){
            char c = t.charAt(i);
            int count = targetMap.getOrDefault(c, 0);
            targetMap.put(c, count + 1);
        }

        int targetLetters = targetMap.size();

        // initiate window dictionary
        HashMap<Character, Integer> windowMap = new HashMap();
        int windowLetters = 0;

        // two pointer
        int left = 0;
        int right = 0;

        int[] ans = new int[]{-1, 0, 0};

        // go through the s string
        while(right < sLen){

            char c = s.charAt(right);
            int count = windowMap.getOrDefault(c, 0);
            count++;
            windowMap.put(c, count);

            // update the match letters
            if(targetMap.containsKey(c) && targetMap.get(c) == count){
                windowLetters++;
            }

            // if the number of letters are the same, move left point.
            while(windowLetters == targetLetters && left <= right){
                char leftChar = s.charAt(left);
                int countChar = windowMap.get(leftChar);
                countChar--;
                windowMap.put(leftChar, countChar);

                if(targetMap.containsKey(leftChar) && targetMap.get(leftChar) > countChar){
                    windowLetters--;
                }

                if(ans[0] == -1 || (right - left + 1) < ans[0]){
                    ans[0] =  right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }
                left++;
            }
            right++;

        }

        if(ans[0] == -1) return "";
        return s.substring(ans[1], ans[2] + 1);
    }
}