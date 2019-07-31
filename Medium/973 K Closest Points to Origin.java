/*
This qeustion is from https://leetcode.com/problems/k-closest-points-to-origin/
Difficulty: medium

We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)



Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)


Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000
*/

// Other solution for T:O(N)
// https://leetcode.com/problems/k-closest-points-to-origin/discuss/220235/Java-Three-solutions-to-this-classical-K-th-problem.

// T:O(NlogN), S:O(N), 27 ms (57.9%)
class Solution {
    public int[][] kClosest(int[][] points, int K) {

        if(points == null || points.length < K) return points;

        PriorityQueue<int[]> pq = new PriorityQueue(new PointComparator());

        for(int[] point: points) pq.add(point);

        LinkedList<int[]> ans = new LinkedList();

        while(K > 0){
            ans.add(pq.poll());
            K--;
        }

        return ans.toArray(new int[ans.size()][2]);
    }
}

class PointComparator implements Comparator<int[]>{
    @Override
    public int compare(int[] a, int[] b){
        double disA = Math.pow(a[0], 2) + Math.pow(a[1], 2);
        double disB = Math.pow(b[0], 2) + Math.pow(b[1], 2);

        return (int)(disA - disB);
    }
}