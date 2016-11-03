/*
This question is from https://leetcode.com/problems/excel-sheet-column-title/
Difficulty: easy

Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB

*/
// Math , T:O(N), M:O(1), 0ms
public class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            char c = (char)((n - 1)%26 +'A');
            sb.append(c);
            n = (n-1)/26;

        }

        return sb.reverse().toString();
    }
}
