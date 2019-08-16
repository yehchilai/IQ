/**
This question is from https://leetcode.com/problems/design-hit-counter/
Difficulty: medium

Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

Example:

HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301);
Follow up:
What if the number of hits per second could be very large? Does your design scale?
*/

// Try to solve the very large hits per second
// try to solve scale
// T:O(1), constant means the length of period, S:O(N) 44 ms (33.69%)
class HitCounter {

    int[] hits;
    int[] times;
    /** Initialize your data structure here. */
    public HitCounter() {
        hits = new int[300];
        times = new int[300];
    }

    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int index = timestamp % 300;
        if(times[index] != timestamp){
            hits[index] = 1;
            times[index] = timestamp;
        }else{
            hits[index]++;
        }
    }

    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int sum = 0;
        for(int i = 0; i < 300; i++){
            if(timestamp - times[i] < 300){
                sum += hits[i];
            }
        }

        return sum;
    }
}

// Try to solve the very large hits per second
// T:O(1), constant means the length of period, S:O(1) 43 ms (72.93%)
class HitCounter {

    Deque<Integer> q;
    HashMap<Integer, Integer> map;
    int PERIOD;
    int hits;
    /** Initialize your data structure here. */
    public HitCounter() {
        q = new ArrayDeque();
        map = new HashMap();
        PERIOD = 300;
        hits = 0;
    }

    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        if(map.containsKey(timestamp)){
            map.put(timestamp, map.get(timestamp) + 1);
        }else{
            map.put(timestamp, 1);
            q.add(timestamp);
        }
        hits++;
    }

    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while(q.size() > 0 && timestamp - q.peek() >= PERIOD){
            int key = q.poll();
            hits = hits - map.get(key);
            map.remove(key);
        }

        return hits;
    }
}

// T:O(1), S:O(N), 41 ms (99.92%)
class HitCounter {

    Deque<Integer> q;
    int PERIOD;
    /** Initialize your data structure here. */
    public HitCounter() {
        q = new ArrayDeque();
        PERIOD = 300;
    }

    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        q.add(timestamp);
    }

    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while(q.size() > 0 && timestamp - q.peek() >= PERIOD){
            q.poll();
        }

        return q.size();
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */