/*
This question is from https://leetcode.com/problems/find-median-from-data-stream/
Difficulty: hard

Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.


*/
// T:O(NlogN), S:O(N), 118 ms
class MedianFinder {

    PriorityQueue<Integer> minHeap; // numbers larger than median
    PriorityQueue<Integer> maxHeap; // numbers less than median

    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue();
        maxHeap = new PriorityQueue(Collections.reverseOrder());
    }

    public void addNum(int num) {
        maxHeap.add(num);
        minHeap.add(maxHeap.poll());

        if(maxHeap.size() < minHeap.size()){
            maxHeap.add(minHeap.poll());
        }

    }

    public double findMedian() {

        if(maxHeap.size() == 0 && minHeap.size() == 0) return 0;

        if(maxHeap.size() + minHeap.size() == 1){
            if(maxHeap.size() > 0) return maxHeap.peek();
            return minHeap.peek();
        }


        if(maxHeap.size() > minHeap.size()) return maxHeap.peek();

        if(minHeap.size() > maxHeap.size()) return minHeap.peek();

        return (double)(maxHeap.peek() + minHeap.peek())/2;
    }
}

// T:O(NlogN), S:O(N), 147 ms
class MedianFinder {

    PriorityQueue<Integer> minHeap; // numbers larger than median
    PriorityQueue<Integer> maxHeap; // numbers less than median

    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue();
        maxHeap = new PriorityQueue(Collections.reverseOrder());
    }

    public void addNum(int num) {
        Integer min = minHeap.size() > 0 ? minHeap.poll() : null;
        Integer max = maxHeap.size() > 0 ? maxHeap.poll() : null;

        if(min == null && max == null){
            maxHeap.add(num);
        }else if(min == null){
            if(max <= num){
                maxHeap.add(max);
                minHeap.add(num);
            }else{
                maxHeap.add(num);
                minHeap.add(max);
            }
        }else if(max == null){
            if(min <= num){
                maxHeap.add(min);
                minHeap.add(num);
            }else{
                maxHeap.add(num);
                minHeap.add(min);
            }
        }else{
            if( num > min){
                maxHeap.add(max);
                minHeap.add(num);
                if(maxHeap.size() > minHeap.size()){
                    minHeap.add(min);
                }else{
                    maxHeap.add(min);
                }
            }else if( num < max){
                maxHeap.add(num);
                minHeap.add(min);
                if(maxHeap.size() > minHeap.size()){
                    minHeap.add(max);
                }else{
                    maxHeap.add(max);
                }
            }else{
                maxHeap.add(max);
                minHeap.add(min);
                if(maxHeap.size() > minHeap.size()){
                    minHeap.add(num);
                }else{
                    maxHeap.add(num);
                }
            }
        }



    }

    public double findMedian() {

        if(maxHeap.size() == 0 && minHeap.size() == 0) return 0;

        if(maxHeap.size() + minHeap.size() == 1){
            if(maxHeap.size() > 0) return maxHeap.peek();
            return minHeap.peek();
        }


        if(maxHeap.size() > minHeap.size()) return maxHeap.peek();

        if(minHeap.size() > maxHeap.size()) return minHeap.peek();

        return (double)(maxHeap.peek() + minHeap.peek())/2;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */