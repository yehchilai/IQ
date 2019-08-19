/**
This question is from https://leetcode.com/problems/next-greater-element-i/
Difficulty: easy

You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
Note:
All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.

*/

// T:O(M+N), S:O(N), 3 ms (79.53%)
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack();
        HashMap<Integer, Integer> map = new HashMap();

        for(int n : nums2){
            while(!stack.empty() && n > stack.peek()){
                map.put(stack.pop(), n);
            }
            stack.add(n);
        }

        int[] ans = new int[nums1.length];

        for(int i = 0; i < ans.length; i++){
            ans[i] = map.getOrDefault(nums1[i], -1);
        }

        return ans;
    }
}
}

// T:O(M*N), S:O(M), 3 ms (79.53%)
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans  = new int[nums1.length];
        Arrays.fill(ans, -1);
        // num1: index
        HashMap<Integer, Integer> map = new HashMap();
        for(int n : nums1) map.put(n, -1);
        for(int i = 0; i < nums2.length; i++){
            if(map.containsKey(nums2[i])) map.put(nums2[i], i);
        }

        for(int i = 0; i < nums1.length; i++){
            int index = map.get(nums1[i]);
            for(int j = index; j < nums2.length; j++){
                if(nums2[j] > nums1[i]){
                    ans[i] = nums2[j];
                    break;
                }
            }
        }

        return ans;
    }
}