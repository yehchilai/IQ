/**
This question is from https://leetcode.com/problems/subarrays-with-k-different-integers/
Difficulty: hard

Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of A.



Example 1:

Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
Example 2:

Input: A = [1,2,1,3,4], K = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].


Note:

1 <= A.length <= 20000
1 <= A[i] <= A.length
1 <= K <= A.length
*/
// https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/235235/C%2B%2BJava-with-picture-prefixed-sliding-window

// T:O(N), S:O(N), 61 ms (62.88% )
class Solution{
    public int subarraysWithKDistinct(int[] A, int K) {
        if(A == null || A.length == 0) return 0;
        int start = 0;
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap();
        int prefix = 0;

        for(int end = 0; end < A.length; end++){
            if(map.getOrDefault(A[end], 0) == 0) K--;

            map.put(A[end], map.getOrDefault(A[end], 0) + 1);

            if(K < 0){
                map.put(A[start], map.get(A[start]) - 1);
                start++;
                prefix = 0;
                K++;
            }

            while(map.getOrDefault(A[start], 0) > 1){
                map.put(A[start], map.get(A[start]) - 1);
                start++;
                prefix++;
            }

            if(K == 0) ans += prefix + 1;
        }

        return ans;
    }
}

// T:O(N), S:O(N), 80 ms (19.64%)
class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        if(A == null || A.length == 0) return 0;
        // To count the number of subarrays, which contains no more than K distinct elements and end with index start.
        return noMoreThanK(A, K) - noMoreThanK(A, K - 1);
    }

    private int noMoreThanK(int[] A, int K){
        int ans = 0;
        int start = 0;
        HashMap<Integer, Integer> map = new HashMap();

        for(int end = 0; end < A.length; end++){
            if(map.getOrDefault(A[end], 0) == 0) K--;
            map.put(A[end], map.getOrDefault(A[end], 0) + 1);
            while(K < 0){
                map.put(A[start], map.get(A[start]) - 1);
                if(map.get(A[start]) == 0) K++;
                start++;
            }
            // To count the number of subarrays, which contains no more than K distinct elements and end with index start.
            ans += end - start + 1;
        }

        return ans;
    }
}
// T:O(N^2), S:O(N), Time Limit Exceeded
class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        if(A == null || A.length == 0) return 0;

        int ans = 0;

        for(int i = 0; i < A.length; i++){
            HashSet<Integer> set = new HashSet();
            set.add(A[i]);
            if(set.size() == K) ans++;
            for(int j = i + 1; j < A.length; j++){
                set.add(A[j]);
                if(set.size() == K){
                    ans++;
                }else if(set.size() > K){
                    break;
                }
            }
        }

        return ans;
    }
}