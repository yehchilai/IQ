/*
This question is from https://leetcode.com/problems/contains-duplicate/

Given an array of integers, find if the array contains any duplicates. 
Your function should return true if any value appears at least twice in the array, 
and it should return false if every element is distinct.


Time Complexity: O(N) , N: number of integers in the array
*/

public class Solution {
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Boolean> duplicate = new HashMap<Integer, Boolean>();
        for(int i = 0; i < nums.length(); i++){
        	if(duplicate.containsKey(nums[i])){
        		return true;
        	}else{
        		duplicate.put(nums[i],true);
        	}
        }
        return false;
    }
}

// Answer II from https://leetcode.com/discuss/74857/5-lines-simple-java-solution
// public boolean containsDuplicate(int[] nums) {
//     Set<Integer> flag = new HashSet<Integer>();
//     for(int i : nums) {
//         if(!flag.add(i)) {
//             return true;
//         }
//     }
//     return false;
// }

// Answer III from https://leetcode.com/discuss/76554/simple-java-solution
// public class Solution {
//     public boolean containsDuplicate(int[] nums) {
//         int i;
//         Arrays.sort(nums);
//         for(i=0;i<nums.length-1;i++)
//         {
//             if(nums[i]==nums[i+1])
//             return true;
//         }
//         return false;
//     }
// }

