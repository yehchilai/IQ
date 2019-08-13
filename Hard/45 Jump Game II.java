/**
This question is from https://leetcode.com/problems/jump-game-ii/
Difficulty: hard

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
Note:

You can assume that you can always reach the last index.

*/

// BFS, T:O(N), S:O(1), 1 ms (100.00%)
class Solution {
    public int jump(int[] nums) {

        if(nums == null || nums.length < 2) return 0;

        int rightMost = 0;
        int index = 0;
        int step = 0;
        while(index <= rightMost){
            int currentRightMost = rightMost;
            for( ; index <= rightMost; index++){
                currentRightMost = Math.max(currentRightMost, index + nums[index]);
                if(index + nums[index] >= nums.length - 1) return step+1;

            }
            step++;
            rightMost = currentRightMost;
        }

        return -1;
    }
}


// T:O(N^2), S:O(N), 274 ms (14.08%)
class Solution {
    public int jump(int[] nums) {
        if(nums == null || nums.length <= 1) return 0;

        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0 ; i < nums.length; i++){
            int index = 1;
            while(index <= nums[i] && i + index < nums.length){
                dp[i + index] = Math.min(dp[i + index], i);
                index++;
            }
        }
        System.out.println(Arrays.toString(dp));
        int end = nums.length - 1;
        int ans = 0;
        while(end > 0){
            end = dp[end];
            ans++;
        }

        return ans;
    }
}