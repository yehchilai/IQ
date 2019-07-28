/**
This question is from https://leetcode.com/problems/24-game/
Difficulty: hard

You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.

Example 1:
Input: [4, 1, 8, 7]
Output: True
Explanation: (8-4) * (7-1) = 24
Example 2:
Input: [1, 2, 1, 2]
Output: False
Note:
The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.

*/

// backtracking, T:O(n!), 13 ms(31.47%)
class Solution {
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new LinkedList();

        for(int n : nums) list.add((double) n);

        return backtracking(list);
    }

    private boolean backtracking(List<Double> list){
        // check the final number
        if(list.size() == 1 ){
            if(Math.abs(list.get(0) - 24.0) < 0.001){
                return true;
            }else{
                return false;
            }
        }

        for(int i = 0; i < list.size(); i++){
            for(int j = i + 1 ; j < list.size(); j++){
                List<Double> next = new LinkedList();
                double x = list.get(i);
                double y = list.get(j);

                next.addAll(Arrays.asList(x+y, x-y, y-x, x*y));
                if(Math.abs(x) > 0.001) next.add(y/x);
                if(Math.abs(y) > 0.001) next.add(x/y);

                list.remove(j);
                list.remove(i);

                for(double d: next){
                    list.add(d);
                    if(backtracking(list)){
                        return true;
                    }
                    list.remove(list.size() - 1);
                }

                list.add(i, x);
                list.add(j, y);
            }
        }
        return false;
    }
}