/*
This question is from https://leetcode.com/problems/combination-sum-iv/
Difficulty: medium

Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?
*/
// DP, T:O(N*M), S:O(M), 2 ms
class Solution {
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);

        int[] ans = new int[target + 1];

        for(int i = 0; i <= target; i++){
            for(int n : nums){
                if(n > i){
                    break;
                }else if(n == i){
                    ans[i]++;
                }else{
                    ans[i] = ans[i] + ans[i - n];
                }
            }
        }

        return ans[target];
    }
}

// combination, T:O(2^N), S:O(1), Time Limit Exceeded
class Solution {

    int ans;

    public int combinationSum4(int[] nums, int target) {

        sum(0, nums, target);

        return ans;
    }

    private void sum(int current, int[] nums, int target){
        for(int i = 0 ; i < nums.length; i++){
            int sum = current + nums[i];

            if(sum == target) ans++;

            if(sum < target) sum(sum, nums, target);
        }
    }
}

// Dynamic programming, T:O(NM), M:O(N), 5ms
public class Solution {
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        int[] result = new int[target+1];
        for(int i = 0; i < result.length;i++){
            for(int num: nums){
                if(num > i){
                    break;
                }else if(num == i){
                    result[i] = result[i] + 1;
                }else{
                    result[i] = result[i] + result[i - num];
                }
            }
        }

        return result[target];
    }
}
