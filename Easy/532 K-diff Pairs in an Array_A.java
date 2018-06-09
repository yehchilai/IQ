/*
This question is from https://leetcode.com/problems/k-diff-pairs-in-an-array/description/
Difficulty: easy

Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference[https://en.wikipedia.org/wiki/Absolute_difference] is k.

Example 1:
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.
Example 2:
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
Example 3:
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).
Note:
The pairs (i, j) and (j, i) count as the same pair.
The length of the array won't exceed 10,000.
All the integers in the given input belong to the range: [-1e7, 1e7].
*/

// T:O(N), S:O(N), 30ms
class Solution {
    public int findPairs(int[] nums, int k) {
        // edge case
        if(k < 0) return 0;

        // recored the smaller number in the pair
        Set<Integer> set = new HashSet();
        // unique set
        Set<Integer> uniqueSet = new HashSet();

        for(int i = 0; i < nums.length; i++){
            if(uniqueSet.contains(nums[i] - k)) set.add(nums[i] - k);
            if(uniqueSet.contains(nums[i] + k)) set.add(nums[i]);
            uniqueSet.add(nums[i]);
        }

        return set.size();
    }
}


// encode to a set, Time Limit Exceeded
class Solution {
    public int findPairs(int[] nums, int k) {
        // pair set
        Set<String> set = new HashSet();

        // sort nums
        Arrays.sort(nums);

        for(int start = 0; start < nums.length -1; start++){
            for(int end = start + 1; end < nums.length; end++){
                if(Math.abs(nums[start] - nums[end]) == k){
                    set.add(Integer.toString(nums[start])+","+Integer.toString(nums[end]));
                }
            }
        }

        return set.size();
    }
}