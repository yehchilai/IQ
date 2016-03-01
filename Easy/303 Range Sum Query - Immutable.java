/*
This question is from https://leetcode.com/problems/range-sum-query-immutable/

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.


Time Complexity: O(N)
*/
// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);

// runtime error??????
public class NumArray {

	int[] tmp = null;
    public NumArray(int[] nums) {
        tmp = new int[nums.length - 1];
        System.arraycopy(nums, 0, tmp, 0, nums.length);
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        for(int index = i; index <= j; inexd++){
        	sum += tmp[index];
        }
        return sum;
    }
}

// 3ms
public class NumArray {

	int[] tmp = null;
    public NumArray(int[] nums) {
        tmp = nums;
        for(int i = 1; i< tmp.length; i++){
        	tmp[i] = tmp[i] + tmp[i-1];
        }
    }

    public int sumRange(int i, int j) {
        if(i == 0){
        	return tmp[j];
        }else{
        	return tmp[j] - tmp[i-1];
        }
    }
}