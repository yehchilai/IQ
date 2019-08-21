/**
This question is from https://leetcode.com/problems/frog-jump/
Difficulty: hard

A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.

Note:

The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.
Example 1:

[0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. The frog can jump to the last stone by jumping
1 unit to the 2nd stone, then 2 units to the 3rd stone, then
2 units to the 4th stone, then 3 units to the 6th stone,
4 units to the 7th stone, and 5 units to the 8th stone.
Example 2:

[0,1,2,3,4,8,9,11]

Return false. There is no way to jump to the last stone as
the gap between the 5th and 6th stone is too large.

*/

// T:O(N*M), M: length of set, S:O(N), 63 ms (32.36%)
class Solution {
    public boolean canCross(int[] stones) {
        if(stones.length <= 1) return true;

        // stone: set of previous jumps to the current stone
        HashMap<Integer, HashSet<Integer>> map = new HashMap();

        for(int i = 0; i < stones.length; i++){
            map.put(stones[i], new HashSet());
        }
        map.get(stones[0]).add(0);

        for(int i = 0; i < stones.length; i++){
            for(int k : map.get(stones[i])){
                for(int jump = k - 1; jump <= k + 1; jump++){
                    if(jump > 0 && map.containsKey(stones[i] + jump)){
                        map.get(stones[i] + jump).add(jump);
                    }
                }
            }
        }
        return map.get(stones[stones.length - 1]).size() > 0;
    }

}

// T:O(3^N), S:O(N), Time Limit Exceeded
class Solution {
    public boolean canCross(int[] stones) {
        return jump(stones, 0, 0);
    }

    private boolean jump(int[] stones, int index, int size){
        for(int i = index + 1; i < stones.length; i++){
            int gap = stones[i] - stones[index];

            if(Math.abs(gap - size) <= 1){
                if(jump(stones, i, gap)){
                    return true;
                }
            }
        }

        return index == stones.length - 1;
    }
}