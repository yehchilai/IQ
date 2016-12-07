/*
This question is from https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
Difficulty: medium

Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note:
You may assume k is always valid, 1 ≤ k ≤ n2.
*/

// heap, T:O(nlogn), M:O(n), 47ms
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int row = matrix.length;
        int column = matrix[0].length;
        int result = -1;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                pq.add(matrix[i][j]);
            }
        }

        for(int i = 0; i < k; i++){
            result = pq.poll();
        }

        return result;
    }
}
