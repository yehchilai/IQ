/*
This question is from https://leetcode.com/problems/daily-temperatures/
Difficulty: medium

Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
*/
// T:O(N), S:O(N), 57 ms
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];

        Stack<Integer> stack = new Stack();

        for(int i = T.length - 1 ; i >= 0 ; i--){

            while(!stack.empty()){
                if(T[i] >= T[stack.peek()]){
                    stack.pop();
                }else{
                    // System.out.println(stack.peek() +", "+ i);
                    ans[i] = stack.peek() - i;
                    stack.push(i);
                    break;
                }
            }

            if(stack.empty()){
                ans[i] = 0;
                stack.push(i);
            }
        }

        return ans;
    }
}