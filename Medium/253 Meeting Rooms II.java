/*
This question is from https://leetcode.com/problems/meeting-rooms-ii/
Difficulty: medium

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.


*/
// T:O(NlogN), S:O(N), 9 ms
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int max = 0;
        if(intervals == null || intervals.length == 0) return max;
        Arrays.sort(intervals, new ArrayComparator());

        // System.out.println(intervals[0][0]+ ", "+intervals[0][1]);
        PriorityQueue<Integer> room = new PriorityQueue();

        room.add(intervals[0][1]);
        max = 1;

        for(int i = 1; i < intervals.length;i++){
            int starttime = intervals[i][0];
            int endtime = intervals[i][1];
            while(room.size() > 0 && starttime >= room.peek()){
                room.poll();
            }

            room.add(endtime);
            max = Math.max(max, room.size());
        }


        return max;
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