/*
This question is from https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
Difficulty: medium

Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/
// HashMap, T:O(N), M:O(N), 5ms
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] result = {0,0};
        if(numbers.length < 0) return result;

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0 ; i< numbers.length; i++){
            if(!map.containsKey(target - numbers[i])){
                map.put(numbers[i], i + 1);
            }else{
                result[0] = map.get(target - numbers[i]);
                result[1] = i+1;
                break;
            }
        }

        return result;
    }
}
// Two Pointer
