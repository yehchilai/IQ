/**
This question is from https://leetcode.com/problems/the-skyline-problem/
Difficulty: hard

A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

Buildings  Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
*/

// https://www.youtube.com/watch?v=GSBLe8cKu0s
// T:O(NlogN), S:O(N), 17ms (87.36%)
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new LinkedList();
        if(buildings == null || buildings.length == 0) return result;

        BuildingPoint[] points = new BuildingPoint[buildings.length*2];
        TreeMap<Integer, Integer> map = new TreeMap();
        int index = 0;
        for(int[] building: buildings){
            points[index] = new BuildingPoint(building[0], building[2], true);
            points[index+1] = new BuildingPoint(building[1], building[2], false);

            index += 2;
        }

        Arrays.sort(points);
        map.put(0, 1);

        int previousMaxHeight = 0;
        for(BuildingPoint point: points){

            if(point.start){
                int count = map.getOrDefault(point.y, 0);
                if(count == 0){
                    map.put(point.y, 1);
                }else{
                    map.put(point.y, count + 1);
                }

            }else{
                int count = map.getOrDefault(point.y, 0);
                if(count > 0){
                    count--;
                    if(count == 0) {
                        map.remove(point.y);
                    }else{
                        map.put(point.y, count);
                    }
                }
            }
            int currentMaxHeight = map.lastKey();
            if(currentMaxHeight != previousMaxHeight){
                List<Integer> ans = new LinkedList();
                ans.add(point.x);
                ans.add(currentMaxHeight);
                result.add(ans);
                previousMaxHeight = currentMaxHeight;
            }
        }

        return result;
    }
}

class BuildingPoint implements Comparable<BuildingPoint>{
    int x;
    int y;
    boolean start;

    public BuildingPoint(int x, int y, boolean s){
        this.x = x;
        this.y = y;
        this.start = s;
    }

    @Override
    public int compareTo(BuildingPoint b){

        if(this.x != b.x){
            return this.x - b.x;
        }else{
            int aHeight = this.start? 0 - this.y: this.y;
            int bHeight = b.start? 0 - b.y: b.y;

            return aHeight - bHeight;
            // return (this.start? 0 - this.y: this.y) - (b.start? 0 - b.y: b.y);
        }
    }
}