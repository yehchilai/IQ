/*
This question is from https://leetcode.com/problems/lexicographical-numbers/
Difficulty: medium

Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.

*/
// T:O(N), S:O(1), 2 ms
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new LinkedList();

        for(int i = 1; i <= 9 ;i++){
            dfs(i, ans, n);
        }

        return ans;
    }

    private void dfs(int current, List<Integer> ans, int n){
        if(current > n) return;

        ans.add(current);

        for(int i = 0; i <= 9; i++){
            int next = 10 * current + i;
            if(next <= n){
                dfs(next, ans, n);
            }
        }
    }
}
