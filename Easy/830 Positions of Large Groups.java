/*
This question is from https://leetcode.com/problems/positions-of-large-groups/
Difficulty: easy

In a string S of lowercase letters, these letters form consecutive groups of the same character.

For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".

Call a group large if it has 3 or more characters.  We would like the starting and ending positions of every large group.

The final answer should be in lexicographic order.



Example 1:

Input: "abbxxxxzzy"
Output: [[3,6]]
Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.
Example 2:

Input: "abc"
Output: []
Explanation: We have "a","b" and "c" but no large group.
Example 3:

Input: "abcdddeeeeaabbbcd"
Output: [[3,5],[6,9],[12,14]]


Note:  1 <= S.length <= 1000
*/

// T:O(N), S:O(N), 2ms (more clean code)
class Solution {
    public List<List<Integer>> largeGroupPositions(String S) {

        List<List<Integer>> result = new LinkedList();
        if(S.length() < 3) return result;

        int start = 0;

        int length = S.length();

        for(int i = 0; i < length; i++){
            if(i == length -1 || S.charAt(i) != S.charAt(i+1)){
                if(i - start + 1 >= 3){
                    List<Integer> ans = new LinkedList();
                    ans.add(start);
                    ans.add(i);
                    result.add(ans);
                }
                start = i + 1;
            }
        }

        return result;
    }
}

// T:O(N), S:O(N), 2ms
class Solution {
    public List<List<Integer>> largeGroupPositions(String S) {

        List<List<Integer>> result = new LinkedList();
        if(S.length() < 3) return result;

        int start = 0;
        int distance = 1;

        int length = S.length();

        for(int i = 1; i < length; i++){
            if(S.charAt(i) == S.charAt(start)){
                distance++;
            }else{
                if(distance >= 3){
                    List<Integer> list = new LinkedList();
                    list.add(start);
                    list.add(start + distance - 1);
                    result.add(list);
                }
                distance = 1;
                start = i;
            }
        }

        if(distance >= 3){
            List<Integer> list = new LinkedList();
            list.add(start);
            list.add(start + distance - 1);
            result.add(list);
        }

        return result;
    }
}