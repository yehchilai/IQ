/*
This question is from https://leetcode.com/problems/kth-largest-element-in-a-stream/
Difficulty: easy

Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.

Example:

int k = 3;
int[] arr = [4,5,8,2];
KthLargest kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4
kthLargest.add(5);   // returns 5
kthLargest.add(10);  // returns 5
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8
Note:
You may assume that nums' length ≥ k-1 and k ≥ 1.
*/

// T:O(N), S:O(k), 71 ms
class KthLargest {

    PriorityQueue<Integer> pq = new PriorityQueue();
    int kth;
    public KthLargest(int k, int[] nums) {
        kth = k;
        for(int i=0; i< nums.length; i++){
            pq.add(nums[i]);
            if(pq.size() > k){
                pq.poll();
            }
        }

    }

    public int add(int val) {
        pq.add(val);

        if(pq.size() > kth){
            pq.poll();
        }

        return pq.peek();
    }

}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

// T:O(N), S:O(N), 171 ms
class KthLargest {

    PriorityQueue<Integer> maxHeap = new PriorityQueue(Collections.reverseOrder());
    PriorityQueue<Integer> minHeap = new PriorityQueue();

    int kth;

    public KthLargest(int k, int[] nums) {
        kth = k;
        for(int i= 0; i < nums.length; i++){
            addIntoHeap(kth, nums[i]);
        }
    }

    public int add(int val) {
        addIntoHeap(kth, val);

        return minHeap.peek();
    }

    public void addIntoHeap(int k, int val){
        if(minHeap.peek() == null || minHeap.size() < k){
            minHeap.add(val);
            return;
        }


        if(maxHeap.peek() == null){
            int min = minHeap.poll();
            if(val > min){
                minHeap.add(val);
                maxHeap.add(min);
            }else{
                minHeap.add(min);
                maxHeap.add(val);
            }
            return;
        }

        int hi = minHeap.poll();
        int lo = maxHeap.poll();

        // System.out.println("min: " +hi+", max: "+lo+", val: "+val);

        if(val > hi){
            minHeap.add(val);
            maxHeap.add(hi);
            maxHeap.add(lo);
        }else{
            minHeap.add(hi);
            maxHeap.add(val);
            maxHeap.add(lo);
        }

    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */