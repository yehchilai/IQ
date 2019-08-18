/**
This question is from https://leetcode.com/problems/max-points-on-a-line/
Difficulty: hard

Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o
+------------->
0  1  2  3  4
Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.


*/

// T:O(N^2), S:O(N), 13 ms (57.94%)
class Solution {
    public int maxPoints(int[][] points) {
        if(points == null || points.length == 0) return 0;
        int max = 0;
        for(int i = 0; i < points.length - 1; i++){
            // use y = mx + b
            // m : frequency
            HashMap<String, Integer> map = new HashMap();
            int duplicate = 0;
            int sameLineMax = 0;
            for(int j = i+1; j < points.length; j++){
                if(i == j) continue;

                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];

                if(x == 0 && y == 0){
                    duplicate++;
                    continue;
                }

                int gcd = getGcd(x, y);
                // calculate slope 'a'
                String m = String.valueOf(x/gcd) + "/" + String.valueOf(y/gcd);
                int count = map.getOrDefault(m, 0);
                count++;
                map.put(m, count);
                sameLineMax = Math.max(sameLineMax, count);
            }
            max = Math.max(max, duplicate + sameLineMax + 1);
        }

        if(max == 0) return 1;
        return max;
    }

    private int getGcd(int x, int y){
        if(y == 0) return x;
        return getGcd(y, x%y);
    }
}

// T:O(N^2), S:O(N), 24 ms (20.55%)
class Solution {
    public int maxPoints(int[][] points) {
        if(points == null || points.length == 0) return 0;
        int max = 0;
        for(int i = 0; i < points.length; i++){
            // use y = mx + b
            // m : frequency
            HashMap<String, Integer> map = new HashMap();
            int duplicate = 0;
            int sameLineMax = 0;
            for(int j = 0; j < points.length; j++){
                if(i == j) continue;

                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];

                if(x == 0 && y == 0){
                    duplicate++;
                    continue;
                }

                int gcd = getGcd(x, y);
                // calculate slope 'a'
                String m = String.valueOf(x/gcd) + "/" + String.valueOf(y/gcd);
                int count = map.getOrDefault(m, 0);
                count++;
                map.put(m, count);
                sameLineMax = Math.max(sameLineMax, count);
            }
            max = Math.max(max, duplicate + sameLineMax + 1);
        }

        if(max == 0) return 1;
        return max;
    }

    private int getGcd(int x, int y){
        if(y == 0) return x;
        return getGcd(y, x%y);
    }
}