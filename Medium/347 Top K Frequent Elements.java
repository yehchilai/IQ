/*
This question is from https://leetcode.com/problems/top-k-frequent-elements/
Difficulty: medium

Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

*/
// HashMap and Heap, T:O(NlogN), M:O(N), 37ms
public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        LinkedList<Integer> result = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            if(!map.containsKey(n)){
                map.put(n, 1);
            }else{
                map.put(n, map.get(n) + 1);
            }
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(new PairComparator());
        for(Map.Entry<Integer, Integer> e : map.entrySet()){
            pq.add(new Pair(e.getKey(), e.getValue()));
        }

        for(int i = 0; i < k ; i++){
            result.add(pq.poll().number);
        }

        return result;
    }

    public class Pair{
        public int number;
        public int value;

        public Pair(int n, int v){
            number = n;
            value = v;
        }
    }

    public class PairComparator implements Comparator<Pair>{
        @Override
        public int compare(Pair a, Pair b){
            if(a.value > b.value) return -1;
            if(a.value < b.value) return 1;
            return 0;
        }
    }
}
// Bucket Sort, T:O(N), M:O(N), 30ms
public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        LinkedList<Integer> result = new LinkedList<>();
        LinkedList<Integer>[] bucket = new LinkedList[nums.length + 1];
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            if(!map.containsKey(n)){
                map.put(n, 1);
            }else{
                map.put(n, map.get(n) + 1);
            }
        }

        for(Map.Entry<Integer, Integer> e : map.entrySet()){
            if(bucket[e.getValue()] == null){
                bucket[e.getValue()] = new LinkedList<Integer>();
            }
            bucket[e.getValue()].add(e.getKey());
        }

        for(int i = nums.length; i > 0 && result.size() < k; i--){
            if(bucket[i] != null){
                result.addAll(bucket[i]);
            }
        }

        return result;
    }

    public class Pair{
        public int number;
        public int value;

        public Pair(int n, int v){
            number = n;
            value = v;
        }
    }

    public class PairComparator implements Comparator<Pair>{
        @Override
        public int compare(Pair a, Pair b){
            if(a.value > b.value) return -1;
            if(a.value < b.value) return 1;
            return 0;
        }
    }
}
