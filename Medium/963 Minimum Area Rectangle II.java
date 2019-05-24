/*
This question is from https://leetcode.com/problems/minimum-area-rectangle-ii/
Difficulty: medium

Given a set of points in the xy-plane, determine the minimum area of any rectangle formed from these points, with sides not necessarily parallel to the x and y axes.

If there isn't any rectangle, return 0.



Example 1:



Input: [[1,2],[2,1],[1,0],[0,1]]
Output: 2.00000
Explanation: The minimum area rectangle occurs at [1,2],[2,1],[1,0],[0,1], with an area of 2.
Example 2:



Input: [[0,1],[2,1],[1,1],[1,0],[2,0]]
Output: 1.00000
Explanation: The minimum area rectangle occurs at [1,0],[1,1],[2,1],[2,0], with an area of 1.
Example 3:



Input: [[0,3],[1,2],[3,1],[1,3],[2,1]]
Output: 0
Explanation: There is no possible rectangle to form from these points.
Example 4:



Input: [[3,1],[1,1],[0,1],[2,1],[3,3],[3,2],[0,2],[2,3]]
Output: 2.00000
Explanation: The minimum area rectangle occurs at [2,1],[2,3],[3,3],[3,1], with an area of 2.


Note:

1 <= points.length <= 50
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
All points are distinct.
Answers within 10^-5 of the actual value will be accepted as correct.
*/
// Math, T:O(N^2), S:O(N), 43 ms
class Solution {
    public double minAreaFreeRect(int[][] points) {

        if(points == null || points.length < 4) return 0;

        HashMap<String, List<Pair>> map = new HashMap();

        // generate map with key : diagonal length and middle point
        for(int i = 0; i < points.length; i++){
            for(int j = i+1; j < points.length; j++){
                Point p1 = new Point(points[i][0], points[i][1]);
                Point p2 = new Point(points[j][0], points[j][1]);

                double distance = Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2);
                double midX = (double)(p1.x + p2.x)/2;
                double midY = (double)(p1.y + p2.y)/2;

                String key = distance + ":" + midX + "," +midY;
                List<Pair> list = map.getOrDefault(key, new LinkedList<Pair>());
                list.add(new Pair(p1, p2));
                map.put(key, list);
            }
        }

        double min = Double.MAX_VALUE;
        // find minimum area
        for(List<Pair> list: map.values()){
            if(list.size() < 0) continue;

            for(int i = 0; i < list.size(); i++){
                for(int j = i + 1; j < list.size(); j++){
                    Point p1 = list.get(i).p1;
                    Point p2 = list.get(j).p1;
                    Point p3 = list.get(j).p2;
                    // System.out.println(list.get(i).toString() +"; "+list.get(j).toString());
                    double width = Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2);
                    double height = Math.pow(p1.x - p3.x, 2) + Math.pow(p1.y - p3.y, 2);
                    // System.out.println("  w: "+width+", h: "+height);
                    double area = Math.pow(width, 0.5) * Math.pow(height, 0.5);
                    // System.out.println(area);
                    min = Math.min(min, area);
                }
            }
        }

        if(min == Double.MAX_VALUE) return 0;
        return min;
    }
}

class Pair{
    Point p1;
    Point p2;

    Pair(Point a, Point b){
        p1 = new Point(a.x, a.y);
        p2 = new Point(b.x, b.y);
    }

    public String toString(){
        return "P1: ("+p1.x+", "+p1.y+"), P2: ("+p2.x+", "+p2.y+")";
    }
}