/*
This question is from https://leetcode.com/problems/reverse-string-ii/
Difficulty: easy

Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"
Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]

*/
// T:O(N), S:O(1), 0ms
class Solution {
    public String reverseStr(String s, int k) {
        char[] sArr = s.toCharArray();

        for(int i = 0; i < sArr.length; i= i + 2*k){
            reverse(sArr, i , i+k - 1);
        }
        // System.out.println(new String(sArr));
        return new String(sArr);
    }

    public void reverse(char[] sArr, int left, int right){
        while( left < right){
            if(right >= sArr.length){
                right--;
                continue;
            }
            char c = sArr[right];
            sArr[right] = sArr[left];
            sArr[left] = c;
            left++;
            right--;
        }
    }
}