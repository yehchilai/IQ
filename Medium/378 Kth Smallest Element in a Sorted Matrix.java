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

// T:O(NlogN), S:O(N), 22 ms
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        if(matrix == null || matrix.length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;

        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());

        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                if(pq.size() == k){
                    int top = pq.poll();
                    int min = Math.min(top, matrix[r][c]);
                    pq.add(min);
                }else{
                    pq.add(matrix[r][c]);
                }
            }
        }

        return pq.peek();
//         PriorityQueue<Integer> pq = new PriorityQueue();
//         for(int r = 0; r < row; r++){
//             for(int c = 0; c < col; c++){
//                 pq.add(matrix[r][c]);
//             }
//         }

//         for(int i = 0; i < k - 1; i++){
//             pq.poll();
//         }
//         return pq.peek();
    }
}

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
