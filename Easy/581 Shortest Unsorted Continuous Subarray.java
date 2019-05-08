/*
This question is from https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
Difficulty: easy

Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.
*/
// T:O(N), S:O(N), 50 ms
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        Stack<Integer> stack = new Stack();
        int indexLeft = nums.length - 1;
        int indexRight = 0;

        for(int i = 0; i < nums.length; i++){
            while(!stack.empty() && (nums[i] < nums[stack.peek()])){
                // System.out.println("i: "+stack.peek());
                indexLeft = Math.min(indexLeft, stack.pop());
            }

            stack.push(i);
        }

        stack.clear();

        for(int j = nums.length - 1; j >= 0; j--){
            while(!stack.empty() && (nums[j] > nums[stack.peek()])){
                indexRight = Math.max(indexRight, stack.pop());
            }
            stack.push(j);
        }

        // System.out.println(indexLeft+", "+indexRight);
        if(indexRight - indexLeft > 0){
            return indexRight - indexLeft + 1;
        }else{
            return 0;
        }
    }
}

// T:O(NlogN), S:O(N), 7ms
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] old = nums.clone();
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        while(i < j){
            if(old[i] != nums[i] && old[j] != nums[j]){
                return j - i + 1;
            }else if(old[i] == nums[i]){
                i++;
            }else if(old[j] == nums[j]){
                j--;
            }
        }

        return 0;
    }
}