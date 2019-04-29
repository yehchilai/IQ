/*
This question is from https://leetcode.com/problems/number-of-boomerangs/
Difficulty: easy

Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).

Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).

Example:
Input:
[[0,0],[1,0],[2,0]]

Output:
2

Explanation:
The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]


*/
// T:O(N^2), S:O(N), 97 ms
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        if(points == null || points.length == 0) return 0;

        HashMap<Double, Integer> map = new HashMap();

        int count = 0;

        for(int i = 0; i < points.length; i++){
            int[] current = points[i];

            for(int j = 0; j < points.length; j++){
                if(i != j){
                    double distance = Math.pow(current[0] - points[j][0], 2) +
                        Math.pow(current[1] - points[j][1], 2);

                    int val = map.getOrDefault(distance, 0);
                    count = count + val*2;
                    map.put(distance, val + 1);
                }
            }
            map = new HashMap();
        }

        return count;
    }
}