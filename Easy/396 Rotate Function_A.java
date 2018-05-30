/*
This question is from https://leetcode.com/problems/rotate-function/
Difficulty: easy

Given an array of integers A and let n to be its length.

Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:

F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].

Calculate the maximum value of F(0), F(1), ..., F(n-1).

Note:
n is guaranteed to be less than 105.

Example:

A = [4, 3, 2, 6]

F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26

So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
*/

// T:O(N^2), M:O(0), 983 ms
public class Solution {
    public int maxRotateFunction(int[] A) {
        int n =  A.length;
        int max = Integer.MIN_VALUE;
        if(n == 0) return 0;
        for(int i = 0; i < n; i++){
            int current = 0;
            for(int j = 0; j < n ; j++){
                current = current + j*bk(j, A, i);
            }
            max = Math.max(max, current);
        }
        return max;
    }

    public int bk(int index, int[] A, int k){
        if(index + k > A.length - 1){
            return A[Math.abs(A.length - (index + k))];
        }else{
            return A[index + k];
        }
    }
}

// Math, T:O(N), M:O(1), 5ms
public class Solution {
    public int maxRotateFunction(int[] A) {
        int n =  A.length;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int currentF = 0;
        for(int i = 0; i < n ; i++){
            sum = sum + A[i];
            currentF = currentF + i*A[i];
        }

        max = Math.max(max, currentF);
        for(int i = n - 1; i > 0 ; i--){
            currentF = sum - n*A[i] + currentF;
            max = Math.max(max, currentF);
        }

        return max;
    }
}
