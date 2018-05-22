/*
This question is from https://leetcode.com/problems/trapping-rain-water/description/
Difficulty: hard

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

*/
// Time Limit Exceeded
class Solution {
    public int trap(int[] height) {

        // current height
        int currentHeight = 1;

        // water unit
        int water = 0;

        if(height.length < 2) return water;

        // go throught all possible height
        while(true){
            // index for height marker
            int indexM = -1;
            // if the height marker is the first marker
            boolean first = true;
            // go through the map
            for(int i = 0; i< height.length; i++){
                if(height[i] >= currentHeight){
                    // if the index is not the first, calculate the water unit
                    if(!first){
                        water = water + (i - indexM - 1);
                    }
                    // update the indexM
                    indexM = i;
                    // not the first index
                    first = false;
                }
            }

            if(indexM == -1) break;
            currentHeight++;
        }
        return water;
    }
}

// https://leetcode.com/problems/trapping-rain-water/solution/
// T: O(N), S: O(1), 23ms
class Solution {
    public int trap(int[] height) {

        // water units
        int water = 0;

        if(height.length <= 2) return water;

        // left index
        int left = 0;
        // left max height
        int leftMax = 0;
        // right index
        int right = height.length -1;
        // right max height
        int rightMax = 0;

        while(left < right){

            if(height[left] < height[right]){
                if(height[left] > leftMax) leftMax = height[left];
                water += leftMax - height[left];
                left++;
            }else{
                if(height[right] > rightMax) rightMax = height[right];
                water += rightMax - height[right];
                right--;
            }
        }
        return water;
    }
}