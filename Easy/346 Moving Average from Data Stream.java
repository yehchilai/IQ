/*
This question is from https://leetcode.com/problems/moving-average-from-data-stream/
Difficulty: easy

Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Example:

MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
*/
// T:O(1), S:O(W), 69 ms
class MovingAverage {

    LinkedList<Integer> q;
    double sum;
    int window;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        q = new LinkedList();
        window = size;
    }

    public double next(int val) {
        int head = 0;
        if(q.size() >= window) head = q.poll();

        sum = sum - head + val;
        q.add(val);

        return sum/q.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */