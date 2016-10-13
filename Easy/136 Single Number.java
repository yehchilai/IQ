/*
This question is from https://leetcode.com/problems/single-number/
Difficulty: easy

Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

*/

// Hashtable / hashmap : 28ms
public class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer,Integer> myMap = new HashMap<Integer,Integer>();
        for(int i = 0; i < nums.length; i++){
            if(myMap.containsKey(nums[i])){
                int value = myMap.get(nums[i]);
                myMap.put(nums[i],value + 1);
            }else{
                myMap.put(nums[i],1);
            }
        }
        Iterator it = myMap.entrySet().iterator();
        int result = 0;
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            int key = (int)pair.getKey();
            int value = (int)pair.getValue();
            if(value == 1) result = key;   
        }
        
        return result;
        // foreach(Map.Entry<Integer, Integer> entry : myMap.entrySet()){
        //     int key = entry.getKey();
        //     int value = entry.getValue();
        //     if(value == 1) return key;
        // }
    }
}

// bit manilupate XOR: 1ms
public class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int i = 0; i < nums.length; i++){
            result = result ^ nums[i];
        }
        return result;
    }
}