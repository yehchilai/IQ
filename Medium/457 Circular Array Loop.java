/*
This question is from https://leetcode.com/problems/circular-array-loop/
Difficulty: medium

You are given a circular array nums of positive and negative integers. If a number k at an index is positive, then move forward k steps. Conversely, if it's negative (-k), move backward k steps. Since the array is circular, you may assume that the last element's next element is the first element, and the first element's previous element is the last element.

Determine if there is a loop (or a cycle) in nums. A cycle must start and end at the same index and the cycle's length > 1. Furthermore, movements in a cycle must all follow a single direction. In other words, a cycle must not consist of both forward and backward movements.



Example 1:

Input: [2,-1,1,2,2]
Output: true
Explanation: There is a cycle, from index 0 -> 2 -> 3 -> 0. The cycle's length is 3.
Example 2:

Input: [-1,2]
Output: false
Explanation: The movement from index 1 -> 1 -> 1 ... is not a cycle, because the cycle's length is 1. By definition the cycle's length must be greater than 1.
Example 3:

Input: [-2,1,-1,-2,-2]
Output: false
Explanation: The movement from index 1 -> 2 -> 1 -> ... is not a cycle, because movement from index 1 -> 2 is a forward movement, but movement from index 2 -> 1 is a backward movement. All movements in a cycle must follow a single direction.


Note:

-1000 ≤ nums[i] ≤ 1000
nums[i] ≠ 0
1 ≤ nums.length ≤ 5000


Follow up:

Could you solve it in O(n) time complexity and O(1) extra space complexity?
*/
// T:O(N), S:O(1), 0 ms
class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        if(n <= 1) return false;
        for(int i = 0; i < n; i++){

            if(nums[i] == 0) continue;

            int j = i;
            int k = getIndex(j, nums);

            while(nums[k] * nums[i] > 0 && nums[getIndex(k, nums)] * nums[i] > 0){
                // System.out.println("j: "+ j +", k: "+k);
                if(j == k){
                    // System.out.println("check j==k");
                    if(j == getIndex(j, nums)) break;
                    return true;
                }
                // System.out.println("update j");
                j = getIndex(j, nums);
                // System.out.println("update k");
                k = getIndex(getIndex(k, nums), nums);
            }

            j = i;
            int direction = nums[j];
            while(nums[j] * direction > 0){
                // System.out.println("next: "+ next);
                int next = getIndex(j, nums);
                nums[j] = 0;
                j = next;
            }
        }

        return false;
    }

    private int getIndex(int index, int[] nums){
        // System.out.println("    index: "+ index);
        int n = nums.length;
        int next = index + nums[index];
        return (next >= 0) ? next % n : n + (next % n);
    }
}