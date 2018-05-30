/*
This question is from https://leetcode.com/problems/rotate-array/
Difficulty: easy

Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

[show hint]

Related problem: Reverse Words in a String II


*/
// Additional array , T:O(N), M:O(N)

// Reverse, T:O(N), M:O(1), 1ms
public class Solution {
    public void rotate(int[] nums, int k) {
        k = k%nums.length;
        if(k == 0) return;
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    public void reverse(int[] arr, int start, int end){
        int i = start;
        int j = end;
        while(i < j){
            int swap = arr[i];
            arr[i] = arr[j];
            arr[j] = swap;
            i++;
            j--;
        }
    }
}
