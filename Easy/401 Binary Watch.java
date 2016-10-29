/*
This question is from https://leetcode.com/problems/binary-watch/
Difficulty: easy

A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.


For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]

Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
*/

// permutation and combination
// C(n,m) = n!/(m!*(n-m)!)
// P(n,m) = n!/(n-m)!
// dfs, T:(N!), M:(N!), 2ms
public class Solution {
    int[] nums = new int[]{8,4,2,1,32,16,8,4,2,1};

    public List<String> readBinaryWatch(int num) {
        List<String> list = new LinkedList<String>();
        if(num > 10) return list;
        dfs(list, new boolean[10], 0, num);
        return list;
    }

    public void dfs(List<String> result,boolean[] checked, int start, int k){
        if(k > 8) return;
        if(k == 0){
            int hour = 0, min = 0;

            for(int i = 0; i < checked.length; i++){
                if(checked[i]){
                     if(i < 4){
                         hour = hour + nums[i];
                     }else{
                         min = min + nums[i];
                     }
                }

            }

            if(hour < 12 && min < 60){
                if(min < 10){
                    result.add(String.valueOf(hour) + ":0" + String.valueOf(min));
                }else{
                    result.add(String.valueOf(hour) + ":" + String.valueOf(min));
                }
            }

        }else{
            for(int i = start; i < checked.length-k + 1; i++){
                checked[i] = true;
                dfs(result, checked, i+1, k-1);
                checked[i] = false;
            }
        }
    }

}

// From https://discuss.leetcode.com/topic/59494/3ms-java-solution-using-backtracking-and-idea-of-permutation-and-combination
// using the idea of "Permutation and Combination"
public class Solution {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int[] nums1 = new int[]{8, 4, 2, 1}, nums2 = new int[]{32, 16, 8, 4, 2, 1};
        for(int i = 0; i <= num; i++) {
            List<Integer> list1 = generateDigit(nums1, i);
            List<Integer> list2 = generateDigit(nums2, num - i);
            for(int num1: list1) {
                if(num1 >= 12) continue;
                for(int num2: list2) {
                    if(num2 >= 60) continue;
                    res.add(num1 + ":" + (num2 < 10 ? "0" + num2 : num2));
                }
            }
        }
        return res;
    }

    private List<Integer> generateDigit(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        generateDigitHelper(nums, count, 0, 0, res);
        return res;
    }

    private void generateDigitHelper(int[] nums, int count, int pos, int sum, List<Integer> res) {
        if(count == 0) {
            res.add(sum);
            return;
        }

        for(int i = pos; i < nums.length; i++) {
            generateDigitHelper(nums, count - 1, i + 1, sum + nums[i], res);
        }
    }
}
