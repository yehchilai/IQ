/**
This question is from https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
Difficulty: medium

Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.



Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.


Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
*/

// backtracking, T:O(N!), S:O(N), 55 ms(18.72%)
class Solution {

    boolean[] visited;
    int[] nums;

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        this.nums = nums;
        for(int num : nums) sum += num;
        if(k <= 0 || sum % k != 0) return false;
        visited = new boolean[nums.length];
        Arrays.sort(nums);
        return backtracking(0, nums.length - 1, k, sum / k);

    }

    private boolean backtracking(int current, int start, int k, int target){
        if(k == 0) return true;

        if(current == target && backtracking(0, nums.length - 1, k - 1, target)){
            return true;
        }

        for(int i = start; i >= 0; i--){
            if(!visited[i] && current + nums[i] <= target){
                visited[i] = true;

                if(backtracking(current + nums[i], start - 1, k, target)){
                    return true;
                }
                visited[i] = false;
            }
        }

        return false;
    }
}