/**
This question is from https://leetcode.com/problems/squares-of-a-sorted-array/
Difficulty: easy

Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.



Example 1:

Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Example 2:

Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]


Note:

1 <= A.length <= 10000
-10000 <= A[i] <= 10000
A is sorted in non-decreasing order.
*/

// T:O(N), S:O(N), 1 ms (100%)
class Solution {
    public int[] sortedSquares(int[] A) {
        int left = 0;
        int right = A.length - 1;

        int[] result = new int[A.length];

        for(int i = A.length - 1; i >= 0; i--){
            if(Math.abs(A[left]) > Math.abs(A[right])){
                result[i] = A[left] * A[left];
                left++;
            }else{
                result[i] = A[right] * A[right];
                right--;
            }
        }

        return result;
    }

}

// T:O(NlogN), S:O(1), 3 ms(33.98%)
class Solution {
    public int[] sortedSquares(int[] A) {
        for(int i = 0; i < A.length; i++){
            A[i] = A[i] * A[i];
        }

        Arrays.sort(A);

        return A;
    }
}