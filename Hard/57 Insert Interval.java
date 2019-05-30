/*
This question is from https://leetcode.com/problems/insert-interval/
Difficulty: hard

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
*/
// T:O(N), S:O(N), 2 ms
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> ans = new LinkedList();
        int start = newInterval[0];
        int end = newInterval[1];
        int index = 0;
        // System.out.println(index +","+ intervals.length);
        // less than newInterval
        while(index < intervals.length){
            int[] current = intervals[index];
            if(current[0] < start){
                 ans.add(current);
                index++;
            }else{
                break;
            }

        }

        // check if the last one is overlap
        if(ans.size() == 0 || ans.getLast()[1] < start){
            ans.add(newInterval);
        }else{
            // get last
            int[] last = ans.pollLast();
            last[1] = Math.max(end, last[1]);
            ans.add(last);
        }

        // check the rest of intervals
        while(index < intervals.length){
            int[] current = intervals[index];

            if(current[0] > ans.getLast()[1]){
                ans.add(current);
            }else{
                int[] last = ans.removeLast();
                last[1] = Math.max(current[1], last[1]);
                ans.add(last);
            }
            index++;
        }

        return ans.toArray(new int[ans.size()][2]);
    }
}