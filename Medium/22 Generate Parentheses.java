/*
This question is from https://leetcode.com/problems/generate-parentheses/
Difficulty: medium

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
*/
// backtracking, T:O(N!), M:O(N), 4ms
public class Solution {
    public List<String> generateParenthesis(int n) {
        LinkedList<String> result = new LinkedList<String>();
        generate("", result, n, n);
        return result;

    }

    public void generate(String current, List<String> result, int left, int right){
        if(left == 0 && right == 0) result.add(current);
        if(left > 0) generate(current+"(", result, left - 1, right);
        if(left < right) generate(current+")", result, left, right - 1);
    }
}
