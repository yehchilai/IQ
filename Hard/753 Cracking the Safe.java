/*
This question is from https://leetcode.com/problems/cracking-the-safe/
Difficulty: hard

There is a box protected by a password. The password is n digits, where each letter can be one of the first k digits 0, 1, ..., k-1.

You can keep inputting the password, the password will automatically be matched against the last n digits entered.

For example, assuming the password is "345", I can open it when I type "012345", but I enter a total of 6 digits.

Please return any string of minimum length that is guaranteed to open the box after the entire string is inputted.

Example 1:
Input: n = 1, k = 2
Output: "01"
Note: "10" will be accepted too.
Example 2:
Input: n = 2, k = 2
Output: "00110"
Note: "01100", "10011", "11001" will be accepted too.
Note:
n will be in the range [1, 4].
k will be in the range [1, 10].
k^n will be at most 4096.

*/
// T:O(k^n), S:O(k^n), 7 ms
class Solution {

    HashSet<String> visitedCombination;
    int expectVisitedNumber;

    public String crackSafe(int n, int k) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n ; i++) sb.append('0');

        visitedCombination = new HashSet();
        expectVisitedNumber = (int)Math.pow(k, n);

        visitedCombination.add(sb.toString());

        dfs(sb, n, k);

        return sb.toString();
    }

    private boolean dfs(StringBuilder sb, int n, int k){
        if(visitedCombination.size() == expectVisitedNumber) return true;

        String str = sb.substring(sb.length() - n + 1);

        for(char c = '0'; c < '0' + k ; c++){
            String newComb = str + c;
            if(!visitedCombination.contains(newComb)){
                visitedCombination.add(newComb);

                sb.append(c);
                if(dfs(sb, n, k)) return true;
                visitedCombination.remove(newComb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        return false;
    }
}