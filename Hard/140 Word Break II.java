/**
This question is from https://leetcode.com/problems/word-break-ii/
Difficulty: hard

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
*/

// Dynamic programming, T:O(n^2), S:O(N), 7ms ( 52.97% )
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() == 0) return new LinkedList();

        HashSet<String> set = new HashSet(wordDict);

        // check if a result is existed.
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for(int i = 1; i <= len; i++){
            for(int j = 0; j < i; j++){
                if(dp[j] && set.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }

        if(dp[len] == false) return new LinkedList();

        // generate answer
        List<String>[] dpList = new LinkedList[len+1];
        List<String> list = new LinkedList();
        list.add("");
        dpList[0] = list;

        for(int i = 1; i <= len; i++){
            List<String> tmp = new LinkedList();
            for(int j = 0; j < i; j++){
                String subStr = s.substring(j, i);
                if(dpList[j].size() > 0 && set.contains(subStr)){
                    // geerate cadidate answers
                    for(String str: dpList[j]){
                        String space = str.equals("")? "" : " ";
                        tmp.add(str + space + subStr);
                    }
                }
            }
            dpList[i] = tmp;
        }


        return dpList[len];
    }
}