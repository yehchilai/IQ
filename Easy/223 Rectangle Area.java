/*
This question is from https://leetcode.com/problems/rectangle-area/

Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area
Assume that the total area is never beyond the maximum possible value of int.


Time Complexity: O(1)
*/
public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int leftBottomX = Math.max(A,E);
        int leftBottomY = Math.max(B,F);
        int rightTopX = Math.min(C,G);
        int rightTopY = Math.min(D,H);
        
        int unionArea = 0;
        
        if(leftBottomX < rightTopX && leftBottomY < rightTopY){
            unionArea = (rightTopY - leftBottomY) * (rightTopX - leftBottomX);
        }
        
        return (C-A)*(D - B) + (G - E)*(H - F) - unionArea;
    }
}