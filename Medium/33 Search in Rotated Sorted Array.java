/*
This question is from https://leetcode.com/problems/search-in-rotated-sorted-array/
DIfficulty: medium

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

*/
// T:O(logN), S:O(1), 3 ms
class Solution {

    int[] arr;

    public int search(int[] nums, int target) {

        if(nums == null || nums.length == 0) return -1;
        int n = nums.length;

        if(n == 1) return nums[0] == target ? 0: -1;
        arr = nums;
        int pivot = findPivot(0, n - 1);
        // System.out.println(pivot);
        if(pivot == 0) return search(0, n-1, target);

        if(target > nums[n - 1]) return search(0 , pivot, target);
        return search(pivot, n - 1, target);
    }

    private int findPivot(int left, int right){
        if(arr[left] < arr[right]) return 0;

        while(left <= right){
            int mid = (left + right) /2;
            // System.out.println(left+","+right);
            if(arr[mid] > arr[mid + 1]){
                return mid + 1;
            }else if(arr[mid] < arr[left] ){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return 0;
    }

    private int search(int left, int right, int target){

        while(left <= right){
            // System.out.println(left+","+right);
            int mid = (left + right)/2;
            if(arr[mid] == target){
                return mid;
            } else if(arr[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        return -1;
    }
}