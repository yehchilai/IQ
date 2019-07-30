/**
This question is from https://leetcode.com/problems/valid-triangle-number/
Difficulty: medium

Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are:
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].

*/
// T:O(N^2), S:O(1 or logN) depending on the sort, 4 ms (99.60%)
class Solution {
    public int triangleNumber(int[] nums) {
        if(nums == null || nums.length < 3) return 0;

        Arrays.sort(nums);

        int ans = 0;

        for(int i = nums.length - 1; i >= 2; i--){
            int left = 0;
            int right = i - 1;
            while(left < right){
                if(nums[left] + nums[right] > nums[i]){
                    ans += right - left;
                    right--;
                }else{
                    left++;
                }
            }
        }

        return ans;
    }
}


// T:O(N^2 + N^2), S:O(logN), 5 ms(58.50%)
class Solution {
    public int triangleNumber(int[] nums) {
        if(nums == null || nums.length < 3) return 0;

        Arrays.sort(nums);

        int ans = 0;

        for(int i = 0; i < nums.length - 2; i++){
            int k = i + 2;
            if(nums[i] != 0){
                for(int j = i + 1; j < nums.length - 1; j++){
                    while(k < nums.length && nums[i] + nums[j] > nums[k]) k++;

                    ans += k - j - 1;
                }
            }
        }

        return ans;
    }
}


// T:O(N^3), S:O(1), 849 ms (5.01%)
class Solution {
    public int triangleNumber(int[] nums) {
        if(nums == null || nums.length < 3) return 0;
        int ans = 0;

        for(int i = 0; i < nums.length - 2; i++){
            for(int j = i + 1; j < nums.length - 1; j++){
                for(int k = j + 1; k < nums.length; k++){

                    if(valid(nums[i], nums[j], nums[k])) ans++;
                }
            }
        }

        return ans;
    }

    private boolean valid(int a, int b, int c){
        if(a + b <= c) return false;
        if(a + c <= b) return false;
        if(b + c <= a) return false;
        return true;
    }
}