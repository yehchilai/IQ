/*
This question is from https://leetcode.com/problems/strobogrammatic-number-ii/
Difficulty: medium

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

Example:

Input:  n = 2
Output: ["11","69","88","96"]
*/

// T:O(N^2)?, S:O(?), 181 ms
// T:O(5^(n/2), S:O(5 ^ (n / 2)), 181 ms
class Solution {
    public List<String> findStrobogrammatic(int n) {
       return recursive(n, n);
    }

    private List<String> recursive(int n, int m){
        if(n == 0) return new LinkedList<String>(Arrays.asList(new String[]{""}));
        if(n == 1) return new LinkedList<String>(Arrays.asList(new String[]{"0", "1", "8"}));

        List<String> list = recursive(n - 2, m);

        List<String> ans = new LinkedList();

        for(int i = 0; i < list.size(); i++){
            String s = list.get(i);

            if(n != m) ans.add("0"+s+"0");

            ans.add("1"+s+"1");
            ans.add("6"+s+"9");
            ans.add("8"+s+"8");
            ans.add("9"+s+"6");
        }

        return ans;
    }
}