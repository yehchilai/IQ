/*
This question is from https://leetcode.com/problems/sliding-window-maximum/description/
Difficulty: hard

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7]
Explanation:

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Note:
You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?
*/

// T: O(nums x k), S: O(1), Time Limit Exceeded
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        if(nums.length == 0) return nums;

        // output
        int[] output = new int[nums.length - k + 1];
        // queue save window's index
        Deque<Integer> q = new LinkedList();

        for(int i = 0; i < nums.length; i++){

            // if the index in the q is lower than window index, poll the last one
            while(q.size() > 0 && q.peek() < i - k + 1){
                q.poll();
            }

            // if the last element is the less than the current one, poll the last element
            while(q.size() > 0 && nums[q.getLast()] < nums[i]){
                q.pollLast();
            }

            // put index to queue
            q.add(i);
            // setup output
            if(i >= k - 1) output[i - k + 1] = nums[q.peek()];
        }

        return output;
    }
}