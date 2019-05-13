/*
This question is from https://leetcode.com/problems/beautiful-arrangement/
Difficulty: medium

Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:

The number at the ith position is divisible by i.
i is divisible by the number at the ith position.


Now given N, how many beautiful arrangements can you construct?

Example 1:

Input: 2
Output: 2
Explanation:

The first beautiful arrangement is [1, 2]:

Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).

Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).

The second beautiful arrangement is [2, 1]:

Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).

Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.


Note:

N is a positive integer and will not exceed 15.


*/
// T:O(K), kk refers to the number of valid permutations. S:O(N), 31 ms
class Solution {

    int count = 0;

    public int countArrangement(int N) {
        int[] arr = new int[N];

        for(int i = 0; i < N; i++){
            arr[i] = i + 1;
        }

        permute(arr, 0);

        return count;
    }

    private void permute(int[] arr, int pivot){
        if(pivot == arr.length){
            count++;
        }

        for(int i = pivot; i < arr.length; i++){
            swap(arr, i, pivot);
            if(arr[pivot] % (pivot+1) == 0 || (pivot+1) % arr[pivot] == 0){
                permute(arr, pivot + 1);
            }
            swap(arr, i, pivot);
        }
    }

    private void swap(int[] arr, int x, int y){
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}