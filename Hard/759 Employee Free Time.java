/**
This question is from https://leetcode.com/problems/employee-free-time/
Difficulty: hard

We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation:
There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.


Example 2:

Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]


(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)

Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

Note:

schedule and schedule[i] are lists with lengths in range [1, 50].
0 <= schedule[i].start < schedule[i].end <= 10^8.
NOTE: input types have been changed on June 17, 2019. Please reset to default code definition to get new method signature.
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start,int _end) {
        start = _start;
        end = _end;
    }
};
*/

// T:O(N*logN), N: all interval, S:O(N), 11 ms (89.29%)
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> ans = new LinkedList();

        if(schedule == null || schedule.size() == 0) return ans;

        PriorityQueue<Interval> pq = new PriorityQueue(new IntervalComparator());

        for(List<Interval> list: schedule){
            for(Interval interval: list){
                pq.add(interval);
            }
        }

        Interval current = pq.poll();

        while(pq.size() > 0){
            if(current.end < pq.peek().start){
                ans.add(new Interval(current.end, pq.peek().start));
                current = pq.poll();
            }else{

                if(current.end > pq.peek().end){
                    pq.poll();
                }else{
                    current = pq.poll();
                }
            }
        }

        return ans;
    }
}

class IntervalComparator implements Comparator<Interval>{
    @Override
    public int compare(Interval a, Interval b){
        if(a.start == b.start){
            return a.end - b.end;
        }else{
            return a.start - b.start;
        }
    }
}
