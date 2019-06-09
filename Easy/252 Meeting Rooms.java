/*
This question is from https://leetcode.com/problems/meeting-rooms/
Difficulty: easy

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

Example 1:

Input: [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: [[7,10],[2,4]]
Output: true
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
*/
// T:O(NlogN), S:O(1), 5 ms
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });

        for(int i = 1; i < intervals.length; i++){
            if(intervals[i - 1][1] > intervals[i][0]) return false;
        }

        return true;
    }
}

// T:O(NlogN), S:O(N), 17 ms
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap();

        for(int i = 0; i < intervals.length; i++){

            int start = intervals[i][0];
            int end = intervals[i][1];

            Integer higher = map.higherKey(start);
            Integer lower = map.lowerKey(end);

            if(higher != null){
                if(end > higher) return false;
            }

            if(lower != null){
                if(start < map.get(lower)) return false;
            }

            map.put(start, end);
        }

        return true;
    }
}

// T:O(N^2) , S:O(1), 182 ms
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {

        for(int i = 0; i < intervals.length; i++){
            for(int j = i + 1; j < intervals.length; j++){
                if(intervals[i][0] < intervals[j][1] && intervals[i][1] > intervals[j][0]){
                    return false;
                }

            }
        }
        return true;
    }
}