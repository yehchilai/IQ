/*
This question is from https://leetcode.com/problems/student-attendance-record-i/
Difficulty: easy

You are given a string representing an attendance record for a student. The record only contains the following three characters:
'A' : Absent.
'L' : Late.
'P' : Present.
A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True
Example 2:
Input: "PPALLL"
Output: False

*/
// T:O(N), S:O(1), 0ms
class Solution {
    public boolean checkRecord(String s) {
        boolean visitedA = false;
        int countL = 0;
        int len = s.length();
        for(int i=0; i < len; i++){
            char c = s.charAt(i);
            if(c == 'A'){
                if(visitedA) return false;
                visitedA = true;
            }

            if(c == 'L'){
                countL++;
                if(countL > 2) return false;
            }else{
                countL = 0;
            }
        }

        return true;
    }
}