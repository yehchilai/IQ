/*
This question is from https://leetcode.com/problems/next-greater-element-ii/
Difficulty: medium

Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number;
The second 1's next greater number needs to search circularly, which is also 2.
Note: The length of given array won't exceed 10000.
*/
// T:O(N), S:O(N), 45 ms
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        Stack<Integer> stack = new Stack();
        int len = nums.length - 1;
        for(int i = len; i >= 0; i--){
            while(!stack.empty()){
                if(nums[i] >= nums[stack.peek()]){
                    stack.pop();
                }else{
                    ans[i] = nums[stack.peek()];
                    break;
                }
            }
            if(stack.empty()) ans[i] = -1;

            stack.push(i);
        }

        for(int i = len; i >= 0; i--){

            while(!stack.empty()){
                if(nums[i] >= nums[stack.peek()]){
                    stack.pop();
                }else{
                    ans[i] = nums[stack.peek()];
                    break;
                }
            }

            if(stack.empty()) ans[i] = -1;

            stack.push(i);
        }


        return ans;
    }
}

// T:O(N^2), S:O(N), 268 ms
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];

        for(int i = 0; i < nums.length; i++){
            ans[i] = nextGreater(i, nums);
            System.out.println(ans[i]);
        }

        return ans;
    }

    private int nextGreater(int index, int[] nums){
        int current = index;
        current++;
        while(true){
            if(current >= nums.length) current = 0;
            if(nums[current] > nums[index]) return nums[current];
            if(current == index) break;
            current++;
        }

        return -1;
    }
}