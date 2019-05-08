/*
This question is from https://leetcode.com/problems/relative-ranks/
Difficulty: easy

Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".

Example 1:
Input: [5, 4, 3, 2, 1]
Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal".
For the left two athletes, you just need to output their relative ranks according to their scores.
Note:
N is a positive integer and won't exceed 10,000.
All the scores of athletes are guaranteed to be unique.
*/
// T:O(N+lgN), S:O(N), 7 ms
class Solution {
    public String[] findRelativeRanks(int[] nums) {
        String[] strs = new String[nums.length];
        if(nums.length == 0) return strs;

        PriorityQueue<Player> pq = new PriorityQueue(new PlayerComparator());

        for(int i = 0; i < nums.length; i++){
            pq.add(new Player(i, nums[i]));
        }

        System.out.println(pq.peek().score);
        int count = 0;
        String[] medal = {"Gold Medal", "Silver Medal", "Bronze Medal"};

        while(pq.size() > 0){
            Player p = pq.poll();
            if(count < 3){
                strs[p.index] = medal[count];
            }else{
                strs[p.index] = Integer.toString(count + 1);
            }

            count++;
        }

        return strs;
    }

}

class Player{
    int index;
    int score;

    Player(int index, int score){
        this.index = index;
        this.score = score;
    }
}

class PlayerComparator implements Comparator<Player>{
    @Override
    public int compare(Player a, Player b){
        // for descending order
        if(a.score > b.score) return -1;
        if(a.score < b.score) return 1;
        return 0;
    }
}