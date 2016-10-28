/*
This question is from https://leetcode.com/problems/intersection-of-two-arrays/
Difficulty: Easy

Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.

*/

// HashSet, T:O(M+N+result), M:(M+N+result), 13ms
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> nums1Set = getNumsSet(nums1);
        HashSet<Integer> nums2Set = getNumsSet(nums2);

        LinkedList<Integer> result = new LinkedList<Integer>();
        for(int i : nums1Set){
            if(nums2Set.contains(i)){
                result.add(i);
            }
        }

        int[] resultArr = new int[result.size()];
        for(int i=0 ; i< resultArr.length; i++){
            resultArr[i] = result.get(i);
        }

        return resultArr;
    }

    public HashSet<Integer> getNumsSet(int[] nums){
        HashSet<Integer> resultSet = new HashSet<>();

        for(int i=0; i < nums.length; i++){
            resultSet.add(nums[i]);
        }

        return resultSet;
    }

}

// HashSet, T:O(M+N+result), M:(M+result), 7ms
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> nums1Set = new HashSet<>();
        LinkedList<Integer> result = new LinkedList<Integer>();

        for(int i=0; i< nums1.length; i++){
            nums1Set.add(nums1[i]);
        }

        for(int i=0; i< nums2.length; i++){
            if(nums1Set.contains(nums2[i])){
                result.add(nums2[i]);
                nums1Set.remove(nums2[i]);
            }
        }

        int[] resultArr = new int[result.size()];
        for(int i=0 ; i< resultArr.length; i++){
            resultArr[i] = result.get(i);
        }

        return resultArr;
    }
}
