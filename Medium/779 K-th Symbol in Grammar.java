/*
This question is from https://leetcode.com/problems/k-th-symbol-in-grammar/
Difficulty: medium

On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.

Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).

Examples:
Input: N = 1, K = 1
Output: 0

Input: N = 2, K = 1
Output: 0

Input: N = 2, K = 2
Output: 1

Input: N = 4, K = 5
Output: 1

Explanation:
row 1: 0
row 2: 01
row 3: 0110
row 4: 01101001
Note:

N will be an integer in the range [1, 30].
K will be an integer in the range [1, 2^(N-1)].


*/
// Math problem
class Solution {
    public int kthGrammar(int N, int K) {

        return Integer.bitCount(K - 1) & 1;
	}
}
class Solution {
    public int kthGrammar(int N, int K) {
        int ones = Integer.bitCount(K - 1);
        // even number
        if(ones % 2 == 0) return 0;
        // odd number
        return 1;
        // OR return ones % 2
	}
}

// brute force, Time Limit Exceeded
class Solution {
    public int kthGrammar(int N, int K) {
        LinkedList<Integer> list = new LinkedList();

        list.add(0);
        for(int i = 2; i <= N; i++){
            LinkedList<Integer> newList = new LinkedList();
            int len = Math.min(list.size(), K);
            for(int j = 0; j < len ; j++){
                int current = list.poll();
                if(current == 1){
                    newList.add(1);
                    newList.add(0);
                }else{
                    newList.add(0);
                    newList.add(1);
                }
            }
            list = newList;
        }

        return list.get(K - 1);
    }
}

// brute force, Time Limit Exceeded
class Solution {
    public int kthGrammar(int N, int K) {
        LinkedList<Integer> list = new LinkedList();

        list.add(0);
        for(int i = 2; i <= N; i++){
            LinkedList<Integer> newList = new LinkedList();
            while(list.size() > 0){
                int current = list.poll();
                if(current == 1){
                    newList.add(1);
                    newList.add(0);
                }else{
                    newList.add(0);
                    newList.add(1);
                }
            }
            list = newList;
        }

        return list.get(K - 1);
    }
}