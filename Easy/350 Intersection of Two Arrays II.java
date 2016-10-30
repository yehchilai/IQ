/*
This question is from https://leetcode.com/problems/intersection-of-two-arrays-ii/
Difficulty: easy

Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

*/
// two for loop, T:O(m*n)
// sorted (quick sort) T:O( mlogm + nlogn + m/n)
// 3.HashMap, T:O(m+n), M:(m+n), 10ms
public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map1 = getMap(nums1);
        HashMap<Integer, Integer> map2 = getMap(nums2);

        LinkedList<Integer> list = new LinkedList<Integer>();

        for(Map.Entry<Integer, Integer> e : map1.entrySet()){
            if(map2.containsKey(e.getKey())){
                int val = Math.min(map2.get(e.getKey()), e.getValue());
                for(int i = 0; i< val ; i++){
                    list.add(e.getKey());
                }
            }
        }

        int[] result = new int[list.size()];
        for(int i = 0; i< result.length; i++){
            result[i] = list.get(i);
        }

        return result;
    }

    public HashMap<Integer, Integer> getMap(int[] nums){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i < nums.length; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i], 1);
            }else{
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }

        return map;
    }
}
