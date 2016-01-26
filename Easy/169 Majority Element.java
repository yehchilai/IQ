/*
This question is from https://leetcode.com/problems/majority-element/

Given an array of size n, find the majority element. 

The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.


Time Complexity: Answer I O(N+M) , Answer II O(N)
*/
public class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> elements = new HashMap<Integer, Integer>();
        for(int num: nums){
        	if(elements.containsKey(num)){
        		elements.put(num, elements.get(num) + 1);
        	}else{
        		elements.put(num, 1);
        	}
        }
        int majorityElement = 0;
        int count = 0;
        Iterator it = elements.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
        	if((Integer)pair.getValue() > count){
        		majorityElement = (Integer)pair.getKey();
        		count = (Integer)pair.getValue();
        	} 
        }
        return majorityElement;
    }

 //    // Answer II from https://leetcode.com/discuss/71105/my-two-line-java-solution 
 //    public int majorityElement(int[] nums) {
 //    	Arrays.sort(nums);
 //    	return nums[nums.length/2];
 //    }
}