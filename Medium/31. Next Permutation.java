/*
This question is from https://leetcode.com/problems/next-permutation/
Difficulty: medium

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/
// T:O(N), S:O(1), 1 ms (92.55%)
class Solution {
    public void nextPermutation(int[] nums) {
        int index = 0;

        for(int i = nums.length - 1; i > 0 ; i--){
            if(nums[i - 1] < nums[i]){
                index = i - 1;
                break;
            }
        }

        // System.out.println(Arrays.toString(nums)+": "+index);

        int target = index;

        for(int i = index + 1; i < nums.length; i++){
            // System.out.println("  - "+i);
            if(nums[i] > nums[index]){
                target = i;
            }else{
                break;
            }
        }

        int tmp = nums[target];
        nums[target] = nums[index];
        nums[index] = tmp;
        // System.out.println("  "+Arrays.toString(nums)+": "+target);
        // reverse(nums, index + 1, nums.length - 1);
        // System.out.println("  "+Arrays.toString(nums));
        if(index == 0 && index == target){
            reverse(nums, index, nums.length - 1);
        }else{
            reverse(nums, index + 1, nums.length - 1);
        }
    }

    private void reverse(int[] nums, int start, int end){
        while(start < end){
            int tmp = nums[end];
            nums[end] = nums[start];
            nums[start] = tmp;
            start++;
            end--;
        }
    }
}