/*
This question is from https://leetcode.com/problems/candy/
Difficulty: hard

There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Example 1:

Input: [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
Example 2:

Input: [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
             The third child gets 1 candy because it satisfies the above two conditions.
*/
// T:O(N), S:O(N), 3 ms
class Solution {
    public int candy(int[] ratings) {
        int N = ratings.length;
        int[] checkLeft = new int[N];
        int[] checkRight = new int[N];

        Arrays.fill(checkLeft, 1);
        Arrays.fill(checkRight, 1);

        for(int i = 1; i < N; i++){
            if(ratings[i-1] < ratings[i]) checkLeft[i] = checkLeft[i - 1] + 1;
        }

        for(int i = N-2; i >= 0; i--){
            if(ratings[i + 1] < ratings[i]) checkRight[i] = checkRight[i + 1] + 1;
        }

        int ans = 0;

        for(int i = 0 ; i < N; i++){
            ans+= Math.max(checkLeft[i], checkRight[i]);
        }

        return ans;
    }
}