/**
This question is from https://leetcode.com/problems/two-sum-less-than-k/
Difficulty: easy

Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S and S < K. If no i, j exist satisfying this equation, return -1.



Example 1:

Input: A = [34,23,1,24,75,33,54,8], K = 60
Output: 58
Explanation:
We can use 34 and 24 to sum 58 which is less than 60.
Example 2:

Input: A = [10,20,30], K = 15
Output: -1
Explanation:
In this case it's not possible to get a pair sum less that 15.


Note:

1 <= A.length <= 100
1 <= A[i] <= 1000
1 <= K <= 2000

*/

// T:O(NlogN), S:O(1), 1 ms (100.00%)
class Solution {
    public int twoSumLessThanK(int[] A, int K) {
        Arrays.sort(A);
        int lo = 0;
        int hi = A.length - 1;
        int max = -1;
        while(lo < hi){
            int sum = A[lo] + A[hi];

            if(sum < K){
                max = Math.max(max, sum);
                lo++;
            }else{
                hi--;
            }
        }
        return max;
    }
}

// T:O(N^2), S:O(1), 2 ms (33.59%)
class Solution {
    public int twoSumLessThanK(int[] A, int K) {
        if(A == null || A.length == 0) return -1;
        int max = -1;
        for(int i = 0; i < A.length - 1; i++){
            for(int j = i + 1; j < A.length; j++){
                int sum = A[i] + A[j];
                if(sum < K) max = Math.max(max, sum);
            }
        }

        return max;
    }
}

// T:O(NlogN), S:O(N), 4 ms (11.04%)
class Solution {
    public int twoSumLessThanK(int[] A, int K) {
        int max = -1;
        TreeSet<Integer> set = new TreeSet();

        for(int n: A){
            Integer num = set.lower(K - n);
            if(num != null){
                max = Math.max(max, n + num);
            }
            set.add(n);
        }

        return max;
    }
}