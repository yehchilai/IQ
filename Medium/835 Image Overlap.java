/*
This question is from https://leetcode.com/problems/image-overlap/
Difficulty: medium

Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)

We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.

(Note also that a translation does not include any kind of rotation.)

What is the largest possible overlap?

Example 1:

Input: A = [[1,1,0],
            [0,1,0],
            [0,1,0]]
       B = [[0,0,0],
            [0,1,1],
            [0,0,1]]
Output: 3
Explanation: We slide A to right by 1 unit and down by 1 unit.
Notes:

1 <= A.length = A[0].length = B.length = B[0].length <= 30
0 <= A[i][j], B[i][j] <= 1

*/
// Use padding on left and top of the matrix
// T:O(N^4), N is the length of the matrix. S:O((2N)^2), 33ms
class Solution {
    public int largestOverlap(int[][] A, int[][] B) {
        int max = Integer.MIN_VALUE;
        int N = B.length;
        int[][] B2 = new int[2*N][2*N];

        for(int i = N; i < 2*N; i++){
            for(int j = N; j < 2*N; j++){
                B2[i][j] = B[i - N][j - N];
            }
        }
        // System.out.println("B2: "+B2.length);

        for(int c = 1; c < B2.length; c++){
            for(int r = 1; r < B2[c].length; r++){
                max = Integer.max(max, overlap(A, B2, c, r));
            }
        }

        return max;

    }

    public int overlap(int[][] A, int[][] B2, int c, int r){

        int sum = 0;

        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A.length; j++){
                if((i+c) >= B2.length || (j+r) >= B2.length) break;
                // System.out.println("c: "+c+", r: "+r+ ", i: "+i+", j: "+j);
                if(B2[i + c][j + r] == 1 && A[i][j] == 1){
                    sum++;
                }
            }
        }

        return sum;
    }


}