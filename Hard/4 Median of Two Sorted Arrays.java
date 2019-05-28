/*
This question is from https://leetcode.com/problems/median-of-two-sorted-arrays/
Difficulty: hard

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
*/
// https://www.youtube.com/watch?v=LPFhl65R7ww&t=1013s
// T:O(log(m+n)), S:O(1), 3 ms
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int x = nums1.length;
        int y = nums2.length;

        if(x > y) return findMedianSortedArrays(nums2, nums1);

        int lo = 0;
        int hi = x;

        while(lo <= hi){
            int splitX = (lo + hi)/ 2;
            int splitY = (x + y + 1) / 2 - splitX;

            int xLeftMax = Integer.MIN_VALUE;
            int xRightMin = Integer.MAX_VALUE;
            if(splitX != 0) xLeftMax = nums1[splitX - 1];
            if(splitX != x) xRightMin = nums1[splitX];

            int yLeftMax = Integer.MIN_VALUE;
            int yRightMin = Integer.MAX_VALUE;
            if(splitY != 0) yLeftMax = nums2[splitY - 1];
            if(splitY != y) yRightMin = nums2[splitY];


            if(xLeftMax <= yRightMin && yLeftMax <= xRightMin){
                if((x + y) % 2 == 0){
                    return (double)(Math.max(xLeftMax, yLeftMax) + Math.min(xRightMin, yRightMin)) / 2;
                }else{
                    return (double)Math.max(xLeftMax, yLeftMax);
                }
            }else if(xLeftMax > yRightMin){
                hi = splitX - 1;
            }else{
                lo = splitX + 1;
            }
        }

        throw new Error();
    }
}