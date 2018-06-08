/*
This question is from https://leetcode.com/problems/maximum-length-of-pair-chain/description/
Difficulty: medium

You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

Example 1:
Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]
Note:
The number of given pairs will be in the range [1, 1000].
*/
// T:O(N^2), S:O(N), 136ms
class Solution {
    public int findLongestChain(int[][] pairs) {
        // sort pairs
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);

        // dp array
        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);

        // go through each pair and its possible chainning element
        for(int end = 0; end < pairs.length; end++){
            // alll chainning element in this subset
            for(int start = 0; start< end; start++){
                if(pairs[start][1] < pairs[end][0]){
                    dp[end] = Math.max(dp[end], dp[start] + 1);
                }
            }
        }

        int max = 0;
        for(int len : dp){
            max = Math.max(max, len);
        }

        return max;
    }
}