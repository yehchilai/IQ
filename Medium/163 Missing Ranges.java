/*
This question is from https://leetcode.com/problems/missing-ranges/
Difficulty: medium

Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Example:

Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
Output: ["2", "4->49", "51->74", "76->99"]
*/

// T:O(N), S:O(1), 0 ms
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new LinkedList();

        int low = lower;

        // 0. nums.length == 0
        if(nums.length == 0){
            list.add(getPath(lower, upper));
            return list;
        }

        // 1. nums[0] > lower;
        if(nums[0] > lower) list.add(getPath(lower, nums[0] - 1));

        // 2. between lower and upper
        for(int i = 0; i < nums.length - 1; i++){
           if(nums[i] != nums[i+1] && nums[i] + 1 < nums[i+1]){
               list.add(getPath(nums[i] + 1, nums[i+1] - 1));
           }
        }

        // 3. nums[nums.length - 1] < upper
        if(nums[nums.length - 1] < upper) list.add(getPath(nums[nums.length - 1] + 1 , upper));

        return list;
    }

    private String getPath(int a, int b){
        if(a == b) return Integer.toString(a);
        return a+"->"+b;
    }
}