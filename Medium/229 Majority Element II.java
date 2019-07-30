/**
This question is from https://leetcode.com/problems/majority-element-ii/
Difficulty: medium

Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]
Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]
*/

// https://gregable.com/2013/10/majority-vote-algorithm-find-majority.html
// Boyer-Moore Majority Vote algorithm
// T:O(N), S:O(1), 2 ms (75.99%)
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new LinkedList();

        if(nums == null || nums.length == 0) return ans;

        int count1 = 0;
        int count2 = 0;
        int major1 = 0;
        int major2 = 1;

        for(int num: nums){
            if(num == major1){
                count1++;
            }else if(num == major2){
                count2++;
            }else if(count1 == 0){
                major1 = num;
                count1 = 1;
            }else if(count2 == 0){
                major2 = num;
                count2 = 1;
            }else{
                count1--;
                count2--;
            }

        }

        count1 = 0;
        count2 = 0;

        for(int num: nums){
            if(num == major1){
                count1++;
            }else if(num == major2){
                count2++;
            }
        }

        if(count1 > nums.length/3) ans.add(major1);
        if(count2 > nums.length/3) ans.add(major2);

        return ans;
    }
}


// T:O(N), S:O(N), 8 ms (27.91%)
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new LinkedList();

        HashMap<Integer, Integer> map = new HashMap();

        for(int num: nums){
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }

        int times = nums.length/3;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            if(entry.getValue() > times) ans.add(entry.getKey());
        }

        return ans;
    }
}