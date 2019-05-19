/*
This question is from https://leetcode.com/problems/minimum-area-rectangle/
Difficulty: medium

Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.

If there isn't any rectangle, return 0.



Example 1:

Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
Example 2:

Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2


Note:

1 <= points.length <= 500
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
All points are distinct.
*/

// T:O(N^2), S:O(N), 229 ms
class Solution {
    public int minAreaRect(int[][] points) {

        if(points == null || points.length == 0) return 0;
        int min = Integer.MAX_VALUE;
        HashMap<Integer, HashSet<Integer>> map = new HashMap();

        for(int[] point: points){
            int x = point[0];
            int y = point[1];
            HashSet<Integer> set = map.getOrDefault(x, new HashSet<Integer>());
            set.add(y);
            map.put(x, set);
        }

        for(int i = 0; i < points.length; i++){
            for(int j = 0; j < points.length; j++){
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];

                if(x1 == x2 || y1 == y2) continue;
                if(map.get(x1).contains(y2) && map.get(x2).contains(y1)){
                    // System.out.println("x1: "+x1+", y1: "+y1+", x2: "+x2+", y2: "+y2);
                    int area = Math.abs(x1 - x2) * Math.abs(y1 - y2);
                    min = Math.min(min, area);
                }
            }
        }
        if(min == Integer.MAX_VALUE) return 0;
        return min;
    }
}