/*
This question is from https://leetcode.com/problems/merge-intervals/
Difficulty: medium

Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

*/
// T:O(NlogN), S:O(N), 7 ms
class Solution {
    public int[][] merge(int[][] intervals) {

        if(intervals == null || intervals.length == 0) return intervals;
        LinkedList<int[]> ans = new LinkedList();

        PriorityQueue<int[]> pq = new PriorityQueue(new ArrayComparator());

        for(int[] interval: intervals){
            pq.add(interval);
        }

        // System.out.println(pq.peek()[0]+", "+pq.peek()[1]);

        while(pq.size() > 0){
            int[] current = pq.poll();

            while(pq.size() > 0){
                int[] next = pq.poll();
                if(current[1] >= next[0]){
                    if(current[1] < next[1]) current[1] = next[1];
                }else{
                    pq.add(next);
                    break;
                }

            }
            ans.add(current);
        }

        return ans.toArray(new int[ans.size()][2]);
    }
}

class ArrayComparator implements Comparator<int[]>{
    @Override
    public int compare(int[] a, int[] b){
        if(a[0] != b[0]){
            return a[0] - b[0];
        }else{
            return a[1] - b[1];
        }
    }
}

