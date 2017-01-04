/*
This question is from https://leetcode.com/problems/different-ways-to-add-parentheses/
Difficulty: medium

Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.


Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]
*/

// Divide and conqure, T:O(N^3), M:O(N), 8ms
public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        LinkedList<Integer> result = new LinkedList();
        int inputLength = input.length();
        for(int i = 0; i < inputLength; i++){
            char c = input.charAt(i);
            if(c == '+' || c =='-' || c=='*'){
                List<Integer> subStrA = diffWaysToCompute(input.substring(0,i));
                List<Integer> subStrB = diffWaysToCompute(input.substring(i+1));
                for(int retA: subStrA){
                    for(int retB: subStrB){
                        switch(c){
                            case '+':
                                result.add(retA + retB);
                                break;
                            case '-':
                                result.add(retA - retB);
                                break;
                            case '*':
                                result.add(retA * retB);
                                break;
                        }
                    }
                }
            }
        }
        if(result.size() == 0) result.add(Integer.valueOf(input));
        return result;
    }
}
