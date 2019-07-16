/*
This question is from https://leetcode.com/problems/merge-sorted-array/
Difficulty: easy

Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
The number of elements initialized in nums1 and nums2 are m and n respectively.

Time complexity: O()
*/
// T:O(n+m), S:O(n+m), 0 ms
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = Arrays.copyOf(nums1, m);

        int i = 0;
        int j = 0;

        while(i + j < m + n){
            if(i < m && j < n){
                if(tmp[i] < nums2[j]){
                    nums1[i + j] = tmp[i];
                    i++;
                }else{
                    nums1[i + j] = nums2[j];
                    j++;
                }
            }else if(i < m){
                nums1[i + j] = tmp[i];
                i++;
            }else if(j < n){
                nums1[i + j] = nums2[j];
                j++;
            }
        }
    }
}

// T:O(n), S:O(1), 0 ms
// Better
public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        while(n > 0){
            if(m > 0 && nums1[m - 1] > nums2[n - 1]){
                nums1[m + n - 1] = nums1[m - 1];
                m--;
            }else{
                nums1[m + n - 1] = nums2[n - 1];
                n--;
            }
        }
    }
}

// Alternative answer : https://leetcode.com/discuss/71809/my-2-lines-0ms-java-solution
public class Solution{
	public void merge(int[] nums1, int m, int[] nums2, int n) {
    for (int i = m + n - 1; i >= 0; i--)
        nums1[i] = m != 0 && (n == 0 || nums1[m - 1] > nums2[n - 1]) ? nums1[--m] : nums2[--n];
}
}