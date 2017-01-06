/*
This question is from https://leetcode.com/problems/permutations/
Difficulty: medium

Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

*/
// backtrack, T:O(N!), M:O(N!), 9ms
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backstrack(result, new ArrayList<Integer>(), nums);
        return result;
    }

    public void backstrack(List<List<Integer>> result, List<Integer> tmpList, int[] nums){
        if(tmpList.size() == nums.length){
            result.add(new ArrayList<>(tmpList));
        }else{
            for(int i = 0 ; i < nums.length; i++){
                if(tmpList.contains(nums[i])) continue;
                tmpList.add(nums[i]);
                backstrack(result, tmpList, nums);
                tmpList.remove(tmpList.size() - 1);
            }
        }
    }
}
