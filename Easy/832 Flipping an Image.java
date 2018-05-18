/*
This question is from https://leetcode.com/problems/flipping-an-image/description/
Difficulty: easy

Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.

To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0] horizontally results in [0, 1, 1].

To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0. For example, inverting [0, 1, 1] results in [1, 0, 0].

Example 1:

Input: [[1,1,0],[1,0,1],[0,0,0]]
Output: [[1,0,0],[0,1,0],[1,1,1]]
Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
Example 2:

Input: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
Notes:

1 <= A.length = A[0].length <= 20
0 <= A[i][j] <= 1

*/
class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        for(int i=0; i< A.length; i++){
            for(int j=0; j<A[i].length/2; j++){
                // flip pixel
                int length = A[i].length - 1;
                int swap = A[i][j];
                A[i][j] = A[i][length - j];
                A[i][length - j] = swap;
                // invert pixel
                A[i][j] = invert(A[i][j]);
                A[i][length - j] = invert(A[i][length - j]);
            }
            // invert center piexl if the length is odd.
            if(A[i].length%2 == 1){
                A[i][A[i].length/2] = invert(A[i][A[i].length/2]);
            }
        }
        return A;
    }

    public int invert(int pixel){
        if(pixel == 1){
            return 0;
        }else{
            return 1;
        }
    }
}

// https://leetcode.com/problems/flipping-an-image/discuss/131883/JavaScript-ES6-solution-O(n)
class Solution{
	public int[][] flipAndInvertImage(int[][] A) {
	    for (let i = 0; i < A.length; i++) {
	        A[i] = A[i].reverse().map(num => num = Math.abs(num - 1));
	    }
	    return A;
	}
}
