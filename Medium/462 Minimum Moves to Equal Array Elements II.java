/*
This question is from https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/
Dificulty: medium

Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

You may assume the array's length is at most 10,000.

Example:

Input:
[1,2,3]

Output:
2

Explanation:
Only two moves are needed (remember each move increments or decrements one element):

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]

*/

// sort, median, T:O(NlongN), M:O(N), 7ms
public class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int move = 0;
        int i = 0, j = nums.length -1;
        while(i < j){
            move = move + (nums[j] - nums[i]);
            i++;
            j--;
        }

        return move;
    }
}

// QuickSelect(the k-th smallest value), T:O(N) worst-case, T:O(N^2), M:O(1), 169 ms
public class Solution {
    public int minMoves2(int[] nums) {
        int median = getMedian(nums);
        int move = 0;
        for(int i = 0; i < nums.length; i++){
            move = move + Math.abs(median - nums[i]);
        }
        return move;
    }

    public int getMedian(int[] nums){
        return quickSelect(nums.length/2 + 1, nums, 0, nums.length - 1);
    }

    public int quickSelect(int k, int[] nums, int start, int end){
        int pivot = nums[end];
        int left = start;
        int right = end;

        while(left < right){
            if(nums[left] <= pivot){
                left++;
                continue;
            }

            if(nums[right] >= pivot){
                right--;
                continue;
            }

            swap(nums, left, right);

        }

        swap(nums, left, end);

        if(k == left + 1) return pivot;
        if(k < left + 1){
            return quickSelect(k, nums, start, left - 1);
        }else{
            return quickSelect(k, nums, left + 1, end);
        }
    }

    public void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
