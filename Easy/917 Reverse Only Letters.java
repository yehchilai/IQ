/**
This question is from https://leetcode.com/problems/reverse-only-letters/
Difficulty: easy

Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.



Example 1:

Input: "ab-cd"
Output: "dc-ba"
Example 2:

Input: "a-bC-dEf-ghIj"
Output: "j-Ih-gfE-dCba"
Example 3:

Input: "Test1ng-Leet=code-Q!"
Output: "Qedo1ct-eeLg=ntse-T!"


Note:

S.length <= 100
33 <= S[i].ASCIIcode <= 122
S doesn't contain \ or "
*/

// T:O(N), S:O(N), 0 ms (100%)
class Solution {
    public String reverseOnlyLetters(String S) {
        if(S == null) return S;

        char[] arr = S.toCharArray();
        if(arr.length == 0) return S;
        int i = 0;
        int j = arr.length - 1;

        while(i < j){
            if(!Character.isLetter(arr[i])){
                i++;
                continue;
            }

            if(!Character.isLetter(arr[j])){
                j--;
                continue;
            }

            char c = arr[i];
            arr[i] = arr[j];
            arr[j] = c;
            i++;
            j--;
        }

        return new String(arr);
    }
}