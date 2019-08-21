/**
This question is from https://leetcode.com/problems/permutation-sequence/
Difficulty: medium

The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"
*/

// https://leetcode.com/problems/permutation-sequence/discuss/22507/%22Explain-like-I'm-five%22-Java-Solution-in-O(n)
// T:O(n * n), S:O(n), 1 ms (99.28%)
class Solution {
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n + 1];
        List<Integer> list = new LinkedList();
        // generate factorial lookup
        // [1, 1, 2, 6, 24, .....]
        factorial[0] = 1;
        for(int i = 1; i < factorial.length; i++){
            factorial[i] = i * factorial[i - 1];
        }

        // insert numbers to the list
        for(int i = 1; i <= n ; i++){
            list.add(i);
        }

        k--;

        StringBuilder sb = new StringBuilder();
        int index = 0;

        for(int i = 1; i <= n; i++){
            index = k / factorial[n - i];
            sb.append(list.get(index));
            list.remove(index);
            k = k - index * factorial[n - i];
        }

        return sb.toString();
    }
}