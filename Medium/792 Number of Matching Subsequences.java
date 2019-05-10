/*
This question is from https://leetcode.com/problems/number-of-matching-subsequences/
Difficulty: medium

Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.

Example :
Input:
S = "abcde"
words = ["a", "bb", "acd", "ace"]
Output: 3
Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
Note:

All words in words and S will only consists of lowercase letters.
The length of S will be in the range of [1, 50000].
The length of words will be in the range of [1, 5000].
The length of words[i] will be in the range of [1, 50].

*/

// Brute force, T:O(SN), S:O(1), 398 ms
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        int result = 0;

        for(int i = 0; i < words.length; i++){
            if(subseq(S, words[i])) result++;
        }

        return result;
    }

    private boolean subseq(String target, String source){
        int lenT = target.length();
        int lenS = source.length();
        int t = 0;
        int s = 0;

        while(t < lenT && s < lenS){
            if(target.charAt(t) == source.charAt(s)){
                t++;
                s++;
            }else{
                t++;
            }
        }

        if(s == lenS) return true;
        return false;
    }
}