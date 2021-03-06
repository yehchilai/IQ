/*
This question is from https://leetcode.com/problems/find-smallest-letter-greater-than-target/
Difficulty: easy

Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.

Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

Examples:
Input:
letters = ["c", "f", "j"]
target = "a"
Output: "c"

Input:
letters = ["c", "f", "j"]
target = "c"
Output: "f"

Input:
letters = ["c", "f", "j"]
target = "d"
Output: "f"

Input:
letters = ["c", "f", "j"]
target = "g"
Output: "j"

Input:
letters = ["c", "f", "j"]
target = "j"
Output: "c"

Input:
letters = ["c", "f", "j"]
target = "k"
Output: "c"
Note:
letters has a length in range [2, 10000].
letters consists of lowercase letters, and contains at least 2 unique letters.
target is a lowercase letter.
*/

class Solution {
    public char nextGreatestLetter(char[] letters, char target) {

        int left = 0;
        int right = letters.length;
        int pivot = left;

        while(left < right){
            pivot = left + (right - left)/2;
            System.out.println(letters[pivot]);
            if(letters[pivot] <= target){
                left = pivot + 1;
            }else{
                right = pivot;
            }
             // System.out.println(left + ", " + right);
        }

        // if(left > letters.length - 1) return letters[0];

        return letters[left % letters.length];
    }
}