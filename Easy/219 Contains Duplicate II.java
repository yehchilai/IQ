/*
The question is from https://leetcode.com/problems/contains-duplicate-ii/
Difficulty: easy

Given an array of integers and an integer k, 
find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] 
and the difference between i and j is at most k.

*/
//  Time Limit Exceeded error
// public class Solution {
//     public boolean containsNearbyDuplicate(int[] nums, int k) {
//         Map<Integer, ArrayList> duplicate = new HashMap<Integer, ArrayList>();
//         for(int i = 0; i < nums.length; i++){
//             if(duplicate.containsKey(nums[i])){
//                 ArrayList<Integer> dup = duplicate.get(nums[i]);
//                 int difference = i - dup.get(0);
//                 dup.add(1, difference);
//                 duplicate.add(nums[i], dup);
//             }else{
//                 ArrayList<Integer> dup = new ArrayList<Interger>();
//                 dup.add(i);
//                 duplicate.put(nums[i], dup);
//             }
//         }
        
//         for(Map.Entry<Integer, Integer> entry: duplicate.entrySet()){
//             ArrayList<Integer> dup = entry.getValue();
//             if(dup.get(1) != null && dup.get(1) > K){
//                 return false;
//             }
//         }
        
//         return true;
//     }
// }
public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> duplicate = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            if(duplicate.containsKey(nums[i])){
                int difference = i - duplicate.get(nums[i]);
                if(difference <= k){
                    return true;
                } else{
                    duplicate.put(nums[i], i);
                }
            }else{
                duplicate.put(nums[i], i);
            }
        }
        return false;
    }
}
// Other answer is from https://leetcode.com/discuss/64369/my-simple-solution-in-java-using-hashmap 
public class Solution{
	public boolean containsNearbyDuplicate(int[] nums, int k) {

    Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    for (int i = 0; i < nums.length; i++) {
        if (map.containsKey(nums[i]) && (i - map.get(nums[i])) <= k) {
            return true;
        }

        map.put(nums[i], i);
    }

    return false;

}
}