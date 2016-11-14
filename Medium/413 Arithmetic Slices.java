/*
This question is from https://leetcode.com/problems/arithmetic-slices/
Difficulty: medium

A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequence:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.

1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.


Example:

A = [1, 2, 3, 4]

return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.

*/

// array manipulate, O:(N^2), M:O(1), 2ms
public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if(A.length < 3) return 0;
        int result = 0;

        for(int i = 0; i < A.length - 2; i++){
            result = result + checkSlice(A, i, A.length);
        }
        return result;
    }

    public int checkSlice(int[] arr, int start, int end){
        int interval = arr[start + 1] - arr[start];
        int count = 1;
        int slices = 0;
        for(int i = start + 1; i < end; i++){
            if(arr[i] - arr[i - 1] == interval){
                count++;
            }else {
                break;
            }
            if(count >= 3) slices++;
        }
        return slices;
    }
}

// from https://discuss.leetcode.com/topic/63302/simple-java-solution-9-lines-2ms/2
// O(N)
public class Solution {
  public int numberOfArithmeticSlices(int[] A) {
      int curr = 0, sum = 0;
      for (int i=2; i<A.length; i++)
          if (A[i]-A[i-1] == A[i-1]-A[i-2]) {
              curr += 1;
              sum += curr;
          } else {
              curr = 0;
          }
      return sum;
  }
}
