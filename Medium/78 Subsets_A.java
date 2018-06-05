/*
This question is from https://leetcode.com/problems/subsets/description/
Difficulty: medium

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/
// T: O(n^2), S: O(n), 3ms
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        // result list
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());

        for(int i = 0; i < nums.length; i++){
            int size = result.size();
            for(int j = 0; j < size; j++){
                ArrayList<Integer> subset = new ArrayList(result.get(j));
                subset.add(nums[i]);
                result.add(subset);
            }
        }

        return result;
    }
}