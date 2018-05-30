/*
This question is from https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
Difficulty: easy

Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/
// HashMap, T:O(N), M:O(N), 7ms
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // default result
        int[] result = {0,0};
        if (numbers.length == 0) return result;

        // hashmap to store the other number for current number
        // Integer: value, Integer: index
        HashMap<Integer, Integer> map = new HashMap();

        // go through teh numbers
        for(int i=0; i< numbers.length; i++){
            // get the other number to match the target
            int other = target - numbers[i];
            // check the current number has the mapping number
            if(map.containsKey(other)){
                result[0] = map.get(other) + 1;
                result[1] = i + 1;
                return result;
            }else{
                map.put(numbers[i], i);
            }
        }
        return result;
    }
}
// Two Pointer, T:O(N), M:O(1), 2ms
public int[] twoSum(int[] numbers, int target) {

        // initialize start and end index
        int start = 0, end = numbers.length - 1;
        while (start < end){
            // sum of the two number
            int sum = numbers[start] + numbers[end];
            if(sum == target){
                return new int[]{start + 1, end + 1};
            }else if(sum < target){
                // move start forward
                start = start + 1;
            }else if(sum > target){
                // move end backword
                end = end - 1;
            }
        }
        return new int[]{0,0};
    }
