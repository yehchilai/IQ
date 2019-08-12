/**
This question is from https://leetcode.com/problems/concatenated-words/
Difficulty: hard

Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
Note:
The number of elements of the given array will not exceed 10,000
The length sum of elements in the given array will not exceed 600,000.
All the input string will only include lower case letters.
The returned elements order does not matter.
*/

class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new LinkedList();
        if(words == null || words.length == 0) return ans;
        HashSet<String> dict = new HashSet();

        Arrays.sort(words, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                return s1.length() - s2.length();
            }
        });

        for(int i = 0 ; i < words.length; i++){
            if(isConcatenated(words[i], dict)){
                ans.add(words[i]);
            }
            dict.add(words[i]);
        }

        return ans;
    }

    private boolean isConcatenated(String word, HashSet<String> dict){
        int len = word.length();
        if(len == 0) return false;
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for(int i = 1; i <= len; i++){
            for(int j = 0; j < i; j++){
                if(dp[j] && dict.contains(word.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }
}