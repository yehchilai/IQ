/*
This question is from https://leetcode.com/problems/largest-triangle-area/
Difficulty: easy

You have a list of points in the plane. Return the area of the largest triangle that can be formed by any 3 of the points.

Example:
Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
Output: 2
Explanation:
The five points are show in the figure below. The red triangle is the largest.
*/

// T:O(N^3), S:O(1), 3ms
// Shoelace formula (https://en.wikipedia.org/wiki/Shoelace_formula)
class Solution {
    public double largestTriangleArea(int[][] points) {
        double max = 0;
        int len = points.length;
        if(len <= 0) return 0;

        for(int i = 0; i < len; i++){
            for(int j = i+1; j < len; j++){
                for(int k = j+1; k < len; k++){
                    max = Math.max(max, area(points[i], points[j], points[k]));
                }
            }
        }

        return max;
    }

    public double area(int[] p, int[] q, int[] r){
        return 0.5 * (Math.abs( q[0]*r[1] + r[0]*p[1] + p[0]*q[1] - r[0]*q[1] - p[0]*r[1] - q[0]*p[1]));
    }
}