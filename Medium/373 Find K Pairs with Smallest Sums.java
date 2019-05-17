/*
This question is from https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
Difficulty: medium

You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:

Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence:
             [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:

Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [1,1],[1,1]
Explanation: The first 2 pairs are returned from the sequence:
             [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:

Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [1,3],[2,3]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
*/

// T:O(NlogN), S:O(N), 16 ms
class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        PriorityQueue<Pair> pq = new PriorityQueue(new PairComparator());
        List<int[]> ans = new LinkedList();
        if(nums1.length == 0 || nums2.length == 0) return ans;
        for(int i = 0; i < nums1.length; i++){
            for(int j = 0; j < nums2.length; j++){
                pq.add(new Pair(nums1[i], nums2[j]));
            }
        }

        int min = Math.min(k, nums1.length * nums2.length);

        while(min > 0){
            Pair p = pq.poll();
            ans.add(new int[]{p.x, p.y});
            min--;
        }

        return ans;
    }

}

class Pair{
    int x;
    int y;

    Pair(int a, int b){
        this.x = a;
        this.y = b;
    }
}

class PairComparator implements Comparator<Pair>{
    @Override
    public int compare(Pair a, Pair b){
        int sumA = a.x + a.y;
        int sumB = b.x + b.y;

        if(sumA != sumB){
            return sumA - sumB;
        }else if(a.x != b.x){
            return a.x - b.x;
        }else{
            return a.y - b.y;
        }
    }
}