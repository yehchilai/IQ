/*
This qeustion is from https://leetcode.com/problems/split-array-largest-sum/
Difficulty: hard

Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
*/
// T:O(N*logN), S:O(1), 1 ms
class Solution {
    public int splitArray(int[] nums, int m) {
        long hi = 0;
        long lo = 0;

        for(int i = 0; i < nums.length; i++){
            hi = hi + nums[i];
            lo = Math.max(lo, nums[i]);
        }

        long ans = hi;

        while(lo <= hi){
            long mid = lo + (hi - lo)/2;

            long sum = 0;
            long count = 1;
            for(int i = 0; i < nums.length; i++){
                if(sum + nums[i] > mid){
                    count++;
                    sum = nums[i];
                }else{
                    sum = sum + nums[i];
                }
            }

            if(count <= m){
                // System.out.println("mid: "+mid+", ans: "+ans);
                ans = Math.min(ans, mid);
                hi = mid - 1;
            }else{
                // System.out.println("mid: "+mid);
                lo = mid + 1;
            }
        }

        return (int)ans;
    }
}