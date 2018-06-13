/*
This question is from https://leetcode.com/problems/global-and-local-inversions/description/
Difficulty: medium

We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.

The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].

The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].

Return true if and only if the number of global inversions is equal to the number of local inversions.

Example 1:

Input: A = [1,0,2]
Output: true
Explanation: There is 1 global inversion, and 1 local inversion.
Example 2:

Input: A = [1,2,0]
Output: false
Explanation: There are 2 global inversions, and 1 local inversion.
Note:

A will be a permutation of [0, 1, ..., A.length - 1].
A will have length in range [1, 5000].
The time limit for this problem has been reduced.
*/

// T:O(N), S:O(1), 21ms
class Solution {
    public boolean isIdealPermutation(int[] A) {
        for(int i = 0; i < A.length; i++){
            if(Math.abs(A[i] - i) >=2) return false;
        }

        return true;
    }
}

// T:O(N^2), S:O(1), Time Limit Exceeded
class Solution {
    public boolean isIdealPermutation(int[] A) {
        for(int start = 0; start < A.length ; start++){
            for(int i = start + 1; i< A.length; i++){
                if(i - start == 1) continue;
                if(A[start] > A[i]) return false;
            }
        }

        return true;
    }
}