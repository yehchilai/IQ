/*
This question is from https://leetcode.com/problems/partition-labels/description/
Difficulty: midium
Tag: A

A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
Note:

S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.

*/
// T: O(N), M: O(26), 15ms
class Solution {
    public List<Integer> partitionLabels(String S) {
        // store the index of each letter which appears last.
        int[] indices = new int[26];
        // string length
        int len = S.length();
        // go though the string to store the last index of each letter
        for(int i=0; i< len; i++){
            indices[S.charAt(i) - 'a'] = i;
        }

        // result list
        List<Integer> result = new ArrayList();
        // partition start point
        int start = 0;
        // max partition index
        int maxIndex = 0;
        // go through the string again to decide the partition position
        for(int end=0; end < len; end++){
            int currentCharIndex = S.charAt(end) - 'a';
            // update the max index
            maxIndex = Math.max(maxIndex, indices[currentCharIndex]);
            // if the current character is equal to the last index, get one partition
            if(end == maxIndex){
                // add to the partition including the current letter (+1).
                result.add(end - start + 1);
                // reset start point
                start = end + 1;
            }

        }
        return result;
    }
}

// Solution
// Time Complexity: O(N), where N is the length of S.
// Space Complexity: O(N).
class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;

        int j = 0, anchor = 0;
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < S.length(); ++i) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }
}