/*
This question is from https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
Difficulty: Medium

Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.

*/
// T:O(N), M:O(N) , 104 ms, from https://discuss.leetcode.com/topic/63213/java-o-n-solution-using-bit-manipulation-and-hashmap/8
public class Solution {
    public int findMaximumXOR(int[] nums) {
        // int mask = 0, max = 0;
        // for(int i = 31; i >=0; i--){
        //     mask = mask | (1 << i);
        //     HashSet<Integer> set = new HashSet<>();
        //     for(int num : nums){
        //         set.add(num & mask);
        //     }

        //     int tmp = max | (1 << i);
        //     for(int setNum : set){
        //         if(set.contains(setNum ^ tmp)){
        //             max = tmp;
        //         }
        //     }
        // }

        // return max;
        int max = 0, mask = 0;
        for (int i = 31; i >= 0; i--) {
            mask |= (1 << i);
            HashSet<Integer> set = new HashSet<Integer>();
            for (int num : nums) {
                set.add(num & mask); // reserve Left bits and ignore Right bits
            }

            /* Use 0 to keep the bit, 1 to find XOR
             * 0 ^ 0 = 0
             * 0 ^ 1 = 1
             * 1 ^ 0 = 1
             * 1 ^ 1 = 0
             */
            int tmp = max | (1 << i); // in each iteration, there are pair(s) whoes Left bits can XOR to max
            for (int prefix : set) {
                if (set.contains(tmp ^ prefix)) {
                    max = tmp;
                }
            }
        }
        return max;

    }
}
