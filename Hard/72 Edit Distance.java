/**
This question is from https://leetcode.com/problems/edit-distance/
Difficulty: hard

Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
*/

// https://leetcode.com/problems/edit-distance/discuss/25849/Java-DP-solution-O(nm)
// T:O(M*N), S:O(M*N),  6 ms (57.92%)
class Solution {
    public int minDistance(String word1, String word2) {
        if(word1 == null || word2 == null) return 0;
        int m = word1.length();
        int n = word2.length();

        int[][] cost = new int[m+1][n+1];

        // insert base
        for(int i = 0; i <= m; i++){
            cost[i][0] = i;
        }

        for(int j = 1; j <= n; j++){
            cost[0][j] = j;
        }

        // dynamic programming
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(word1.charAt(i) == word2.charAt(j)){
                    cost[i + 1][j + 1] = cost[i][j];
                }else{
                    int current = cost[i][j];
                    int right = cost[i+1][j];
                    int down = cost[i][j+1];

                    cost[i + 1][j + 1] = Math.min(current, Math.min(right, down)) + 1;
                }
            }
        }

        for(int[] arr: cost){
            System.out.println(Arrays.toString(arr));
        }

        return cost[m][n];
    }
}