/*
This question is from https://leetcode.com/problems/find-k-closest-elements/
Difficulty: medium

Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104
UPDATE (2017/9/19):
The arr parameter had been changed to an array of integers (instead of a list of integers). Please reload the code definition to get the latest changes.

*/
// T:O(lgN + k), S:O(1), 7ms
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        LinkedList<Integer> ans = new LinkedList();
        int index = Arrays.binarySearch(arr, x);
        int n = arr.length;
        System.out.println("index: "+index);
        if(index < 0){
            if(x <= arr[0]){

                for(int i= 0; i < k; i++) ans.add(arr[i]);
                return ans;
            }else if(x >= arr[n - 1]){
                for(int i = n - k; i < n; i++) ans.add(arr[i]);
                return ans;
            }
            index = -index - 1;
        }

        int low = Math.max(index - k, 0);
        int high = Math.min(n - 1, index + k);
        System.out.println("index: "+index+" Before, low: "+low+", high: "+high);
        while(high - low > k - 1){
            if(low < 0 || x - arr[low] <= arr[high] - x){
                high--;
            }else if(high > n - 1 || x - arr[low] > arr[high] - x){
                low++;
            }
        }
        System.out.println("After, low: "+low+", high: "+high);
        for(int i = low; i <= high; i++){
            ans.add(arr[i]);
        }

        return ans;
    }
}