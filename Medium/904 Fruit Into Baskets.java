/*
This question is from https://leetcode.com/problems/fruit-into-baskets/
Difficulty: medium

In a row of trees, the i-th tree produces fruit with type tree[i].

You start at any tree of your choice, then repeatedly perform the following steps:

Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.

You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.

What is the total amount of fruit you can collect with this procedure?



Example 1:

Input: [1,2,1]
Output: 3
Explanation: We can collect [1,2,1].
Example 2:

Input: [0,1,2,2]
Output: 3
Explanation: We can collect [1,2,2].
If we started at the first tree, we would only collect [0, 1].
Example 3:

Input: [1,2,3,2,2]
Output: 4
Explanation: We can collect [2,3,2,2].
If we started at the first tree, we would only collect [1, 2].
Example 4:

Input: [3,3,3,1,2,1,1,2,3,3,4]
Output: 5
Explanation: We can collect [1,2,1,1,2].
If we started at the first tree or the eighth tree, we would only collect 4 fruits.


Note:

1 <= tree.length <= 40000
0 <= tree[i] < tree.length

*/
// T:O(N), S:O(1), 41 ms
class Solution {

    public int totalFruit(int[] tree) {
        int n = tree.length;
        if(n < 3) return n;

        HashMap<Integer, Integer> map = new HashMap();
        int left = 0;
        int right = 0;
        int max = 2;

        while(right < n){
            if(map.size() < 3){
                map.put(tree[right], right);
                right++;
            }

            if(map.size() == 3){
                int removeIndex = Collections.min(map.values());
                map.remove(tree[removeIndex]);
                left = removeIndex + 1;
            }

            max = Math.max(max, right - left);
        }

        return max;
    }
}
// T:O(N^2), S:O(1), Time Limit Exceeded
class Solution {
    public int totalFruit(int[] tree) {
        int max = 0;

        for(int i = 0; i < tree.length; i++){
            HashSet<Integer> set = new HashSet();
            int count = 0;
            for(int j = i; j< tree.length; j++){
                if(set.size() == 2 && !set.contains(tree[j])) break;
                count++;
                set.add(tree[j]);
            }
            max = Math.max(max, count);
        }

        return max;
    }
}