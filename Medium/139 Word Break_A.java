/*
This question is from https://leetcode.com/problems/word-break/description/
Difficulty: Medium

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false

*/

// dynamic programming. T:O(N^2), S:O(N), 5 ms (55.74% )
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}

// T: O(n^2), S: O(n)
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        // create a hash set with the word dictionary
        HashSet<String> set = new HashSet(wordDict);
        // a queue to record possible words
        Deque<Integer> q = new LinkedList();
        // start index
        int start = 0;
        // initailize q
        q.add(start);
        // string length
        int len = s.length();
        // visited
        int[] visited = new int[len + 1];

        while(q.size() > 0){
            // pop the q
            start = q.poll();

            // stop contindion when the start index is the last index in the given string.
            if(start == len) return true;

            // go throuhg all possible segment in the rest of the string
            for(int end = start + 1; end <= len; end++){
                // get a segment of the string
                String str = s.substring(start, end);
                // System.out.println(str);
                // check the segment
                if(set.contains(str)){
                    // System.out.println("+ "+str);
                    // check the if the index is visited
                    if(visited[end] == 0){
                        q.add(end);
                        visited[end] = 1;
                    }
                }
            }
            // System.out.println("========");
        }

        return false;

    }
}

// DFS
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (visited[start] == 0) {
                for (int end = start + 1; end <= s.length(); end++) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        queue.add(end);
                        if (end == s.length()) {
                            return true;
                        }
                    }
                }
                visited[start] = 1;
            }
        }
        return false;
    }
}

//"aaaaaaa"
//["aaaa","aaa"]
// get worng on this case
// class Solution {
//     public boolean wordBreak(String s, List<String> wordDict) {

//         // create a hash set with the word dictionary
//         HashSet<String> dict = new HashSet(wordDict);
//         int start = 0;
//         int len = s.length();
//         while(start < len){
//             for(int end = start; end < len; end++){
//                 String newS = s.substring(start, end + 1);
//                 System.out.println(newS);
//                 if(dict.contains(newS)){
//                     start = end;
//                     if(end == len - 1) return true;
//                     break;
//                 }else{
//                     if(end == len - 1) return false;
//                 }
//             }
//             start++;
//         }

//         return false;

//     }
// }